import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.bson.conversions.Bson;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class DbHandler {

    int port;
    String[] ips;
    Document document;

    MongoClient mongoClient;
    MongoDatabase mongoDatabase;
    MongoCollection<Document> mongoCollection;

    public DbHandler(int port, String[] ips) {
        this.port = port;
        this.ips = ips;
    }

    public void connection(String database) {
        mongoClient = new MongoClient(Arrays.asList(
                new ServerAddress(ips[0], port),
                new ServerAddress(ips[1], port),
                new ServerAddress(ips[2], port)));
        mongoDatabase = mongoClient.getDatabase(database);
        System.out.println("Established connection with -> " + mongoDatabase.getName());
    }

    public void insertOne(String collection, Document document) {
        mongoCollection = mongoDatabase.getCollection(collection);
        mongoCollection.insertOne(document);
        System.out.println("Successful Insertion");
    }

    public void insertMany(String collection, Document[] docs) {
        mongoCollection = mongoDatabase.getCollection(collection);
        List<Document> documents = new ArrayList<Document>(Arrays.asList(docs).subList(0, docs.length-1));
        mongoCollection.insertMany(documents);
        System.out.println("Successful Insertion");
    }

    public void find(String collection, Bson bsonFilter) {
        mongoCollection = mongoDatabase.getCollection(collection);
        document = mongoCollection.find(bsonFilter).first();
        assert document != null;
        System.out.println(document.toJson());
    }

    public void find(String collection) {
        mongoCollection = mongoDatabase.getCollection(collection);
        try (MongoCursor<Document> cursor = mongoCollection.find().iterator()) {
            while (cursor.hasNext()) { System.out.println(cursor.next().toJson()); }
        } catch (Exception e) { e.printStackTrace(); }
    }

}
