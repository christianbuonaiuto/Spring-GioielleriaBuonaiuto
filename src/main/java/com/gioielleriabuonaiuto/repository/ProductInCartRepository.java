package com.gioielleriabuonaiuto.repository;

import com.gioielleriabuonaiuto.entity.ProductInCart;
import com.gioielleriabuonaiuto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInCartRepository extends JpaRepository<ProductInCart, Integer> {

    boolean existsByIdo(int ido);

    ProductInCart getByIdo(int ido);

    @Modifying
    @Query(value = "SELECT p FROM ProductInCart p WHERE p.usero = ?1")
    List<ProductInCart> getAllByUsero(User emailUser);

    void deleteByIdo(int ido);

    void deleteAllByUsero(User user);
}
