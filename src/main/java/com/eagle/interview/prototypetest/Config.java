package com.eagle.interview.prototypetest;

import com.eagle.interview.configurationtest.A;
import com.eagle.interview.configurationtest.EagleSvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//不配置的Configuration的话，EagleSvc创建被创建了两次
@Configuration
@ComponentScan("com.eagle.interview.prototypetest")
public class Config {

}



