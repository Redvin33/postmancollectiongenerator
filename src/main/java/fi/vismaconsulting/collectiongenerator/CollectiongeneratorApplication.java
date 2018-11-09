package fi.vismaconsulting.collectiongenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CollectiongeneratorApplication {

	@Autowired
	static Requesthandler handler;

	public static void main(String[] args) {
		SpringApplication.run(CollectiongeneratorApplication.class, args);

	}
}
