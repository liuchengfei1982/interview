package com.eagle.interview.svcscan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
@ComponentScan("com.eagle.interview.svcscan")
public class ScanConfig {
}
