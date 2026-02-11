package se.iths.stefan.labbdrift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.stefan.labbdrift.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
