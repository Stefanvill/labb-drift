package se.iths.stefan.labbdrift.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.stefan.labbdrift.model.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {


}
