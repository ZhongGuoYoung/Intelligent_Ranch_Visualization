package com.mlm.mlmserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.mlm.mlmserver.dao")
@SpringBootApplication
public class MlmserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MlmserverApplication.class, args);
	}

}
