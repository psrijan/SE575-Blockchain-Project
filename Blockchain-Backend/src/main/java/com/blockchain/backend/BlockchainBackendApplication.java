package com.blockchain.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlockchainBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BlockchainBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
