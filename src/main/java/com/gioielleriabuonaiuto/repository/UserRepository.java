package com.gioielleriabuonaiuto.repository;

import com.gioielleriabuonaiuto.entity.Order;
import com.gioielleriabuonaiuto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByEmail(String email);

    User getByEmail(String email);


}
