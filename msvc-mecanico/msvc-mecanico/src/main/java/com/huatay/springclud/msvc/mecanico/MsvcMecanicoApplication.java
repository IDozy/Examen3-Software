package com.huatay.springclud.msvc.mecanico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcMecanicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcMecanicoApplication.class, args);
	}

}
