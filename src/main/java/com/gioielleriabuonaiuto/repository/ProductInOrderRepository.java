package com.gioielleriabuonaiuto.repository;

import com.gioielleriabuonaiuto.entity.Order;
import com.gioielleriabuonaiuto.entity.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInOrderRepository extends JpaRepository<ProductInOrder,Integer> {

    boolean existsByIdo(int id);

    ProductInOrder getByIdo(int id);

    @Modifying
    @Query(value = "SELECT p FROM ProductInOrder p WHERE p.ordero = ?1")
    List<ProductInOrder> getAllByOrder(Order order);

}
