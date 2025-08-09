package dataaccess;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Config {
    public static final MongoClient MONGO_CLIENT;
    public static final MongoDatabase PIX_PAINT_DATABASE;
    public static final MongoCollection<Document> USERS;
    public static final MongoCollection<Document> PROJECTS;

    /**
     * Retrieves the connection string to access the cluster.
     * @return the connection string.
     * @throws IOException for method readAllLines.
     */
    public static String getApiToken() throws IOException {
        final Path path = Paths.get("token.txt");
        return Files.readAllLines(path).get(0);
    }

    static {
        try {
            MONGO_CLIENT = MongoClients.create(getApiToken());
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        PIX_PAINT_DATABASE = MONGO_CLIENT.getDatabase("PixPaint");
        USERS = PIX_PAINT_DATABASE.getCollection("users");
        PROJECTS = PIX_PAINT_DATABASE.getCollection("projects");
    }

}
