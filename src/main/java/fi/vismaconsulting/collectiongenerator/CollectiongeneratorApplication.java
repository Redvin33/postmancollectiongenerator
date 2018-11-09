package fi.vismaconsulting.collectiongenerator;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class CollectiongeneratorApplication implements CommandLineRunner {

	Requesthandler handler;

	public CollectiongeneratorApplication(Requesthandler handler) {
		this.handler = handler;
	}



	public static void main(String[] args) {
		SpringApplication.run(CollectiongeneratorApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		//  /Users/tmcf/Desktop/Moist.postman_collection.json

		String s = handler.getFileContentAsString("/Users/tmcf/Desktop/moist.json");
		JSONObject j = new JSONObject(s);
		handler.convertJSONObjectToFile(j, "/Users/tmcf/Desktop/");

	}
}
