package com.aditi.cognify_journal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CognifyJournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CognifyJournalApplication.class, args);
	}

}
