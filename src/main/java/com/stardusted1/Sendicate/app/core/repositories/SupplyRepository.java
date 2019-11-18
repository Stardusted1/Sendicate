package com.stardusted1.Sendicate.app.core.repositories;

import com.stardusted1.Sendicate.app.core.cargo.Supply;
import org.springframework.data.repository.CrudRepository;

public interface SupplyRepository extends CrudRepository<Supply, Long> {


	public Iterable<Supply> findAllByDeliverymanIdEquals(long Id);
	public Iterable<Supply> findAllByProviderIdEquals(long Id);
	public Iterable<Supply> findAllByReceiverIdEquals(long Id);
}
