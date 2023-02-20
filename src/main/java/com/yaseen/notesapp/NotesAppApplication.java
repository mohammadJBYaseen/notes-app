package com.yaseen.notesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class NotesAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesAppApplication.class, args);
	}

}
