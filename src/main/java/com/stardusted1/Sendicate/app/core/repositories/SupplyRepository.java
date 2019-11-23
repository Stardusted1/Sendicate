package com.stardusted1.Sendicate.app.core.repositories;

import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.cargo.SupplyStatus;
import org.springframework.data.repository.CrudRepository;

public interface SupplyRepository extends CrudRepository<Supply, Long> {

	public Iterable<Supply> findAllByStatusEquals(SupplyStatus supplyStatus);
	public Iterable<Supply> findAllByDeliverymanIdEquals(String Id);
	public Iterable<Supply> findAllByProviderIdEquals(String Id);
	public Iterable<Supply> findAllByReceiverIdEquals(String Id);
}
