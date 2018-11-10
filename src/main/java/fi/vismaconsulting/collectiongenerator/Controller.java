package fi.vismaconsulting.collectiongenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController(value="rest")
public class Controller {

    @Autowired
    Requesthandler handler;

    @GetMapping()
    public void generateCollections() {
        Params params = new Params();
        List<String> attributes = new ArrayList<>();
        attributes.add("https://stackoverflow.com/questions/11443928/how-to-modify-values-of-jsonobject-jsonarray-directly?test=23&uusi=eere");
        attributes.add("https://google.com");
        params.setAttributes(attributes);
        params.setFolderPath("C:\\CollectionGenerator2\\src\\main\\resources\\testikokoelma.postman_collection.json");
        params.setTargetPath("C:\\Bizagi\\");
        handler.generateCollections(params);

    }

    @PostMapping(value="generate")
    public void generateCollections2(@RequestBody Params params) {
        handler.generateCollections(params);
    }

}
