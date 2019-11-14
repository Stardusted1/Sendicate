package com.stardusted1.Sendicate.app.core.users.repositories;

import com.stardusted1.Sendicate.app.core.users.Receiver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReceiverRepository extends CrudRepository<Receiver, Integer> {
}
