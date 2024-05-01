package com.berkhayta.springbootblogrestapi.repository;

import com.berkhayta.springbootblogrestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
