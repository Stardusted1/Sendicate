package com.stardusted1.Sendicate.app.core.repositories;

import com.stardusted1.Sendicate.app.core.users.Administrator;
import com.stardusted1.Sendicate.app.core.users.Provider;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdministratorRepository extends CrudRepository<Administrator,Long> {
	public Optional<Administrator> findFirstByName(String name);
}
