package com.huatay.springclud.msvc.auto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcAutoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcAutoApplication.class, args);
	}

}
