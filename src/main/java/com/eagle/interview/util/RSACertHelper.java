package com.eagle.interview.util;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * 1 openssl生成一个私钥
 * 		openssl genrsa -out rsa_private_key.key 1024
 * 2 根据私钥生成公钥（这个没什么用,主要是对照看下里面的公钥是什么）
 * 		openssl rsa -in rsa_private_key.key -pubout -out rsa_public_key.key
 * 3 生成一个证书（用上面生成的密钥rsa_private_key.key生成一个数字证书mycer.cer
 *		openssl req -new -x509 -key rsa_private_key.key -out mycer.cer -days 1095
 *
 * getPublicKey()就是从mycer.cer获取到公钥，然后和第二步对比
 */
public class RSACertHelper {
	public static void main(String[] args) throws Exception {
		getPublicKey();
	}

	private static void getPublicKey() throws Exception {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		X509Certificate cert = (X509Certificate)cf.generateCertificate(new FileInputStream("D:\\cert\\mycer.cer"));
		PublicKey publicKey = cert.getPublicKey();
		BASE64Encoder base64Encoder=new BASE64Encoder();
		String publicKeyString = base64Encoder.encode(publicKey.getEncoded());
		System.out.println("-----------------公钥--------------------");
		System.out.println(publicKeyString);
		System.out.println("-----------------公钥--------------------");
	}


}
