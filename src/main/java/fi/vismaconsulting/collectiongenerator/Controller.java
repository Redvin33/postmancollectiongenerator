package fi.vismaconsulting.collectiongenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="rest")
public class Controller {

    @Autowired
    Requesthandler handler;

    @GetMapping()
    public void generateCollections() {
        Params params = new Params();
        params.setAttributes("doijso");
        params.setAttributeToBeChanged("iofdwoijfe");
        params.setFolderPath("C:\\CollectionGenerator2\\src\\main\\resources\\testikokoelma.postman_collection.json");
        handler.generateCollections(params);

    }
}
