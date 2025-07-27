package data_access;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    public static String getAPIToken() throws IOException {
        Path path = Paths.get("token.txt");
        return Files.readAllLines(path).get(0);
    }

    public static final MongoClient mongoClient;
    public static final MongoDatabase pixPaintDatabase;
    public static final MongoCollection<Document> users;

    static {
        try {
            mongoClient = MongoClients.create(getAPIToken());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        pixPaintDatabase = mongoClient.getDatabase("PixPaint");
        users = pixPaintDatabase.getCollection("users");
    }

}
