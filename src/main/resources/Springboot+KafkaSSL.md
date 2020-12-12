### 环境

VM: 192.168.56.200

​        hostname:k8s-master

​        kafka_2.12-2.3.1

Windowns

​         SpringBoot



### 服务器端SSL证书签发（Kafka配置SSL认证）

在装有Kafka的VM上，执行证书创建.

keystore文件其实就是个密钥库。和KeyVault一个道理

ds1994 是指定密钥库的密码，需要SpringBoot中一致

mkdir -p /usr/ca/{root,server,client,trust}



#### 第一步:生成server.keystore.jks文件

```shell
keytool -keystore /usr/ca/server/server.keystore.jks -alias ds-k8s-master -validity 365 -genkey -keypass ds1994 -keyalg RSA -dname "CN=k8s-master,OU=aspire,O=aspire,L=guangdong,S=guangzhou,C=cn" -storepass ds1994 -ext SAN=DNS:k8s-master
```

#### 第二步:生成CA认证证书

```shell
#生成两份文件，ca-key是私钥，ca-cert是证书
openssl req -new -x509 -keyout /usr/ca/root/ca-key -out /usr/ca/root/ca-cert -days 365 -passout pass:ds1994 -subj "/C=cn/ST=guangzhou/L=guangdong/O=aspire/OU=aspire/CN=k8s-master"
```

#### 第三步:通过CA证书创建一个客户端信任证书

```shell
#任何链接Kafka服务的，都是客户端，这个证书其实和服务器端信任证书是一样的，只是名字不一样
keytool -keystore /usr/ca/trust/client.truststore.jks -alias CARoot -import -file /usr/ca/root/ca-cert -storepass ds1994
```

#### 第四步:通过CA证书创建一个服务端器端信任证书

```shell
keytool -keystore /usr/ca/trust/server.truststore.jks -alias CARoot -import -file /usr/ca/root/ca-cert -storepass ds1994
```

#### 第五步:服务器证书的签名处理

```shell
#第一小步：从keystore中导出服务器端证书server.cert-file
keytool -keystore /usr/ca/server/server.keystore.jks -alias ds-k8s-master -certreq -file /usr/ca/server/server.cert-file -storepass ds1994
#第二小步：用CA给服务器端证书进行签名处理
openssl x509 -req -CA /usr/ca/root/ca-cert -CAkey /usr/ca/root/ca-key -in /usr/ca/server/server.cert-file -out /usr/ca/server/server.cert-signed -days 365 -CAcreateserial -passin pass:ds1994
#第三小步：将CA证书导入到服务器端keystore
keytool -keystore /usr/ca/server/server.keystore.jks -alias CARoot -import -file /usr/ca/root/ca-cert -storepass ds1994
#第四小步：将已签名的服务器证书导入到服务器keystore
keytool -keystore /usr/ca/server/server.keystore.jks -alias ds-k8s-master -import -file /usr/ca/server/server.cert-signed -storepass ds1994
```



### 客户端SSL证书签发（跟Kafka配置SSL认证无关）

#### 第一步：生成client.keystore.jks文件

```shell
keytool -keystore /usr/ca/client/client.keystore.jks -alias ds-k8s-master -validity 365 -genkey -keypass ds1994 -dname "CN=k8s-master,OU=aspire,O=aspire,L=guangdong,S=guangzhou,C=cn" -ext SAN=DNS:k8s-master -storepass ds1994
```

#### 第二步：从client.keystore中导出客户端证书（client.cert-file）

```shell
keytool -keystore /usr/ca/client/client.keystore.jks -alias ds-k8s-master -certreq -file /usr/ca/client/client.cert-file -storepass ds1994
```

#### **第三步：**用CA给客户端证书进行签名处理

```shell
openssl x509 -req -CA /usr/ca/root/ca-cert -CAkey /usr/ca/root/ca-key -in /usr/ca/client/client.cert-file -out /usr/ca/client/client.cert-signed -days 365 -CAcreateserial -passin pass:ds1994
```

#### **第四步：**将CA证书导入到客户端keystore

```shell
keytool -keystore /usr/ca/client/client.keystore.jks -alias CARoot -import -file /usr/ca/root/ca-cert -storepass ds1994
```

#### **第五步：**将已签名的证书导入到客户端keystore

```shell
keytool -keystore /usr/ca/client/client.keystore.jks -alias ds-k8s-master -import -file /usr/ca/client/client.cert-signed -storepass ds1994
```





### Kafka配置SSL认证

#### 第一步:修改kafka安装目录下config目录下的server.properties文件

```properties
############################# Server Basics #############################
# SSL认证配置
# 如果配置了SSL认证，那么原来的port和advertised.listeners可以注释掉了
listeners=SSL://kafka-single:9095
advertised.listeners=SSL://kafka-single:9095
ssl.keystore.location=/usr/ca/server/server.keystore.jks
ssl.keystore.password=ds1994
ssl.key.password=ds1994
ssl.truststore.location=/usr/ca/trust/server.truststore.jks
ssl.truststore.password=ds1994
ssl.client.auth=required
ssl.enabled.protocols=TLSv1.2,TLSv1.1,TLSv1
ssl.keystore.type=JKS 
ssl.truststore.type=JKS 
# kafka2.0.x开始，将ssl.endpoint.identification.algorithm设置为了HTTPS，即:需要验证主机名
# 如果不需要验证主机名，那么可以这么设置 ssl.endpoint.identification.algorithm=即可
ssl.endpoint.identification.algorithm=HTTPS
# 设置内部访问也用SSL，默认值为security.inter.broker.protocol=PLAINTEXT
security.inter.broker.protocol=SSL
broker.id=0
############################# Socket Server Settings #############################
num.network.threads=3
num.io.threads=8
socket.send.buffer.bytes=102400
socket.receive.buffer.bytes=102400
socket.request.max.bytes=104857600
############################# Log Basics #############################
log.dirs=/usr/data/kafka
num.partitions=1
num.recovery.threads.per.data.dir=1
############################# Internal Topic Settings  #############################
offsets.topic.replication.factor=1
transaction.state.log.replication.factor=1
transaction.state.log.min.isr=1
############################# Log Retention Policy #############################
log.retention.hours=168
log.segment.bytes=1073741824
log.retention.check.interval.ms=300000
############################# Zookeeper #############################
zookeeper.connect=localhost:2181
zookeeper.connection.timeout.ms=6000
############################# Group Coordinator Settings #############################
group.initial.rebalance.delay.ms=0
```

#### 第二步（可选）:如果配置SSL之前，存在Kafka数据，那么建议重新换一个位置来 存放数据；如果确保之前的数据已经没什么用了，也可以直接删除之前的数据

#### 第三步:重启kafka

```shell
/myTools/kafka_2.12-2.3.1/bin/zookeeper-server-start.sh /myTools/kafka_2.12-2.3.1/config/zookeeper.properties
/myTools/kafka_2.12-2.3.1/bin/kafka-server-start.sh /myTools/kafka_2.12-2.3.1/config/server.properties
```

#### 第四步:检查

```shell
#验证配置的ssl有效
openssl s_client -debug -connect k8s-master:9095 -tls1

#kafka自带的消费者生产者测试

#先创建一个主题
/myTools/kafka_2.12-2.3.1/bin/kafka-topics.sh --create --zookeeper 192.168.56.200:2181 --replication-factor 1 --partitions 1 --topic topicOne
#查看主题
/myTools/kafka_2.12-2.3.1/bin/kafka-topics.sh --list --zookeeper 192.168.56.200:2181

#创建一个SSL下的消费者配置文件c.properties
security.protocol=SSL
group.id=test-group
ssl.truststore.location=/usr/ca/trust/server.truststore.jks
ssl.truststore.password=ds1994
ssl.keystore.password=ds1994
ssl.keystore.location=/usr/ca/server/server.keystore.jks

#创建一个SSL下的消费者配置文件p.properties
bootstrap.servers=k8s-master:9095
security.protocol=SSL
ssl.truststore.location=/usr/ca/trust/server.truststore.jks
ssl.truststore.password=ds1994   
ssl.keystore.password=ds1994
ssl.keystore.location=/usr/ca/server/server.keystore.jks

#启动消费端
/myTools/kafka_2.12-2.3.1/bin/kafka-console-consumer.sh --bootstrap-server k8s-master:9095 --topic topicOne --from-beginning --consumer.config /myTools/kafka_2.12-2.3.1/config/c.properties

#启动生产端
/myTools/kafka_2.12-2.3.1/bin/kafka-console-producer.sh --broker-list k8s-master:9095 --topic topicOne --producer.config /myTools/kafka_2.12-2.3.1/config/p.properties


```

### SpringBoot集成Kafka SSL

#### 消费端和服务端都加入SSL相关配置

```java
private static final String brokerList = "k8s-master:9095";
Properties properties = new Properties();
properties.put("bootstrap.servers", brokerList);
properties.put("security.protocol", "SSL");
properties.put("ssl.truststore.location","D:\\project-idea\\aia\\src\\main\\resources\\jks\\client.truststore.jks");
properties.put("ssl.truststore.password","ds1994");
properties.put("ssl.keystore.password","ds1994");
properties.put("ssl.keystore.location","D:\\project-idea\\aia\\src\\main\\resources\\jks\\server.keystore.jks");
//省略无关配置
```

#### 配置域名

```java
C:\Windows\System32\drivers\etc
192.168.56.200 k8s-master
```

