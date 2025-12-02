package com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartupApplication implements CommandLineRunner {

    private final ClientRepository clientRepository;

    public StartupApplication(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(StartupApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        var client = new Client("george", 12);
        clientRepository.save(client);

        var client1 = clientRepository.findById(1L).get();
        System.out.println(client1);
    }
}
