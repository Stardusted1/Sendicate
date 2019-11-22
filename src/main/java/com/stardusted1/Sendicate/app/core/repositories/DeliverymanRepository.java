package com.stardusted1.Sendicate.app.core.repositories;

import com.stardusted1.Sendicate.app.core.users.Deliveryman;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeliverymanRepository extends CrudRepository<Deliveryman, Long> {
	public Optional<Deliveryman> findFirstByName(String name);
}
