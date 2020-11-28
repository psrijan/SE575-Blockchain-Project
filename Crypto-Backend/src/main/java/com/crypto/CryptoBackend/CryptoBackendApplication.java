package com.crypto.CryptoBackend;

import com.crypto.CryptoBackend.core.module.BlockChainCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CryptoBackendApplication implements CommandLineRunner {

	@Autowired
	private BlockChainCore blockChainCore;

	public static void main(String[] args) {
		SpringApplication.run(CryptoBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		blockChainCore.testSha();
	}
}
