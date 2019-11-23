package com.stardusted1.Sendicate.app.core.repositories;

import com.stardusted1.Sendicate.app.core.users.NewUser;
import org.springframework.data.repository.CrudRepository;

public interface NewUserRepository extends CrudRepository<NewUser, String> {

}
