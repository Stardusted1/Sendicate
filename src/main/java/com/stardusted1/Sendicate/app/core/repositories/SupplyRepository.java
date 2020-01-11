package com.stardusted1.Sendicate.app.core.repositories;

import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.cargo.SupplyStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface SupplyRepository extends CrudRepository<Supply, Long> {

	public Iterable<Supply> findAllByStatusEquals(SupplyStatus supplyStatus);
	public Iterable<Supply> findAllByStatusIsInAndReceiverIdEquals(Collection<SupplyStatus> statuses, String Id);
	public Iterable<Supply> findAllByStatusIsInAndProviderIdEquals(Collection<SupplyStatus> statuses, String Id);
	public Iterable<Supply> findAllByStatusIsInAndDeliverymanIdEquals(Collection<SupplyStatus> statuses, String Id);
	 public Iterable<Supply> findAllByDeliverymanIdEquals(String Id);
	public Iterable<Supply> findAllByProviderIdEquals(String Id);
	public Iterable<Supply> findAllByReceiverIdEquals(String Id);
}
