package com.stardusted1.Sendicate.app.core.repositories;

import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.cargo.SupplyStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface SupplyRepository extends CrudRepository<Supply, Long> {

	public Iterable<Supply> findAllByStatusEquals(SupplyStatus supplyStatus);

	public Iterable<Supply> findAllByStatusInAndReceiverIdEquals(Collection<SupplyStatus> statuses, String Id);

	public Iterable<Supply> findAllByStatusInAndProviderIdEquals(Collection<SupplyStatus> statuses, String Id);

	public Iterable<Supply> findAllByStatusInAndDeliverymanIdEquals(Collection<SupplyStatus> statuses, String Id);

	public Iterable<Supply> findAllByStatusEqualsAndDeliverymanIdEquals(SupplyStatus status, String id);

	public Iterable<Supply> findAllByStatusEqualsAndReceiverIdEquals(SupplyStatus status, String id);

	public Iterable<Supply> findAllByStatusEqualsAndProviderIdEquals(SupplyStatus status, String id);

	public Iterable<Supply> findAllByDeliverymanIdEquals(String Id);

	public Iterable<Supply> findAllByProviderIdEquals(String Id);

	public Iterable<Supply> findAllByReceiverIdEquals(String Id);
}
