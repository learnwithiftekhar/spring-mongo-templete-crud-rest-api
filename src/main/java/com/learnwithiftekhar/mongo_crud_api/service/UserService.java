package com.learnwithiftekhar.mongo_crud_api.service;

import com.learnwithiftekhar.mongo_crud_api.model.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final MongoTemplate mongoTemplate;

    public UserService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public User createUser(User user) {
        return mongoTemplate.save(user);
    }

    public List<User> getAllUsers() {
        return mongoTemplate.findAll(User.class);
    }

    public Optional<User> getUserById(String id) {
        var user = mongoTemplate.findById(id, User.class);
        return Optional.ofNullable(user);
    }

    public User updateUser(String id, User user) {
        var query = new Query(Criteria.where("id").is(id));

        Update update = new Update()
                .set("name", user.getName())
                .set("email", user.getEmail())
                .set("age", user.getAge())
                .set("city", user.getCity());

        mongoTemplate.updateFirst(query, update, User.class);
        return getUserById(id).orElse(null);
    }

    public boolean deleteUser(String id) {
        var query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.remove(query, User.class).getDeletedCount() > 0;
    }

    public boolean existsByEmail(String email) {
        var query = new Query(Criteria.where("email").is(email));
        return mongoTemplate.exists(query, User.class);
    }
}
