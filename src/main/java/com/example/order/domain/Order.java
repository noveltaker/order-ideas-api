package com.example.order.domain;

import com.example.order.utils.RandomUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Table(name = "user_order")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @Column(name = "order_number", nullable = false , unique = true , length = 12)
    private String number;

    @Column(nullable = false)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date orderDate;

    @BatchSize(size = 20)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @PostLoad
    @PrePersist
    void insert() {
        number = RandomUtils.getRandomValue();
        orderDate = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(o) != Hibernate.getClass(this)) return false;
        Order order = (Order) o;
        return Objects.equals(order.getNumber(), this.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Builder
    public Order(String name, User user) {
        this.name = name;
        this.user = user;
    }

}
