package com.distributed_system.term_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
public class TermProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TermProjectApplication.class, args);
	}



}
