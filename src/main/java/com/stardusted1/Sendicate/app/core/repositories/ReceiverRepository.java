package com.stardusted1.Sendicate.app.core.repositories;

import com.stardusted1.Sendicate.app.core.users.Receiver;
import com.stardusted1.Sendicate.app.core.users.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceiverRepository extends CrudRepository<Receiver, Long> {
	public Optional<Receiver> findFirstByName(String name);
}
