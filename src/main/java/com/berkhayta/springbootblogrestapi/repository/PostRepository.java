package com.berkhayta.springbootblogrestapi.repository;

import com.berkhayta.springbootblogrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    //findByAuthorId için JPA query metodu
    List<Post> findByAuthorId(Long userId);
    //findByCategoryId için JPA query metodu
    List<Post> findByCategoryId(Long categoryId);



}
