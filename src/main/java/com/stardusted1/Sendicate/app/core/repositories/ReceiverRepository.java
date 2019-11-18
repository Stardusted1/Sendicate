package com.stardusted1.Sendicate.app.core.repositories;

import com.stardusted1.Sendicate.app.core.users.Receiver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiverRepository extends CrudRepository<Receiver, Long> {
}
