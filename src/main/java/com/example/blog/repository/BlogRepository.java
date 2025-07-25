package com.example.blog.repository;

import com.example.blog.model.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blogs, Long> {
    List<Blogs> findByDeletedAtIsNull();
}
