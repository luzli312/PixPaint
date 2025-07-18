package api;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    public static String getAPIToken() throws IOException {
        Path path = Paths.get("token.txt");
        return Files.readAllLines(path).get(0);
    }

    public static MongoClient mongoClient;

    static {
        try {
            mongoClient = MongoClients.create(getAPIToken());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
