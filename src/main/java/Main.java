import org.bson.conversions.Bson;
import com.mongodb.client.model.Filters;

public class Main {

    private static final int port = 27017; // Port for client mongoDB
    private static final String[] ips = {"host-1","host-2","host-n"}; // Nodes mongoDB

    public static void main(String[] args) {

        // region connection

        DbHandler dbHandler = new DbHandler(port,ips); // For connection to mongoDB
        dbHandler.connection("FCTADDIA"); // Establish the connection with the database

        // endregion

        // region insert

        Document document = new Document("Prova", "Note Prova", true, 20); // For insertOne
        dbHandler.insertOne("coll", document.create()); // Insert a document into the database

        org.bson.Document[] documents = new org.bson.Document[5]; // For insertMany
        for (int i = 0; i < documents.length; i++) { // Replenish the document array to make a multiple insert
            documents[i] = new Document("Prova " + i, "Note Prova", true, 20).create();
        }
        dbHandler.insertMany("coll", documents); // Insert multiple documents into the database

        // endregion

        // region find

        dbHandler.find("coll"); // Find all documents

        Bson bsonFilter = Filters.eq("oggetto", "Prova"); // Create query filter
        dbHandler.find("coll", bsonFilter); // Find specific document

        // endregion

    }

}
