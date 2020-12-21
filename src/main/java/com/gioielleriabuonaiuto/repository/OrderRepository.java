package com.gioielleriabuonaiuto.repository;

import com.gioielleriabuonaiuto.entity.Order;
import com.gioielleriabuonaiuto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    boolean existsByIdo(String ido);

    Order getByIdo(String ido);

    @Modifying
    @Query("FROM Order ORDER BY datePurchase DESC")
    List<Order> findAll();

    @Modifying
    @Query("FROM Order WHERE usero = ?1 ORDER BY datePurchase DESC")
    List<Order> getAllByUsero(User user);
}
