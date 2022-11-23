package com.niit.userProductServicepc1.repository;

import com.niit.userProductServicepc1.domain.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProductRepo extends MongoRepository<UserModel,String> {
            UserModel findByEmail(String email);
}
