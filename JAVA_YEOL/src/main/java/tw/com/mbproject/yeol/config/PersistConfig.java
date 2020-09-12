package tw.com.mbproject.yeol.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
public class PersistConfig {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create();
    }

}
