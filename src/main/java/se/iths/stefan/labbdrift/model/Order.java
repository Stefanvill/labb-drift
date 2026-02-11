package se.iths.stefan.labbdrift.model;


import jakarta.persistence.*;


import java.time.LocalDateTime;


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double totalAmount;


    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now();


    public Order() {
    }


    public LocalDateTime getOrderDate() {
        return orderDate;
    }


    public double getTotalAmount() {
        return totalAmount;
    }


    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }
}
