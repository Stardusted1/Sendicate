package com.stardusted1.Sendicate.app.core.users.repositories;



import com.stardusted1.Sendicate.app.core.users.User1;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
@Transactional
public interface User1Repository extends CrudRepository<User1, Integer> {

}