package com.gioielleriabuonaiuto.repository;

import com.gioielleriabuonaiuto.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Modifying
    @Query("FROM Product ORDER BY code")
    List<Product> findAll();

    @Modifying
    @Query("FROM Product WHERE quantity > 0")
    List<Product> findAllAvailable();

    @Modifying
    @Query("FROM Product WHERE quantity > 0 ORDER BY price ASC  ")
    List<Product> findAllOrderByPriceIncr();


    @Modifying
    @Query("FROM Product WHERE quantity > 0 ORDER BY price DESC")
    List<Product> findAllOrderByPriceDecr();

    boolean existsByCode(String code);

    Product getByCode(String code);


    @Modifying
    @Query("SELECT DISTINCT p.brand FROM Product p")
    List<String> getNameAllBrand();

    @Modifying
    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> getAllCategories();

    @Modifying
    @Query("FROM Product WHERE category = ?1 AND quantity > 0")
    List<Product> findAllByCategory(String category);

    @Modifying
    @Query("FROM Product WHERE brand = ?1 AND quantity > 0")
    List<Product> findAllByBrand(String brand);
}
