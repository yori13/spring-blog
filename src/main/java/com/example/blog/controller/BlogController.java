package com.example.blog.controller;

import com.example.blog.model.Blogs;
import com.example.blog.repository.BlogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/blog")
    public String showAllBlogs(Model model) {
        List<Blogs> blogs = blogRepository.findAll();
        model.addAttribute("blogs", blogs);
        return "blogs";
    }

    @GetMapping("/")
    public String getMethodName(Model model) {
        List<Blogs> blogs = blogRepository.findByDeletedAtIsNull();
        model.addAttribute("blogs", blogs);
        return "top";
    }

    @GetMapping("/blogs/{id}")
    public String showBlogDetail(@PathVariable Long id, Model model) {
        Blogs blog = blogRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ブログが見つかりません"));
        model.addAttribute("blog", blog);
        return "detail";
    }

    @GetMapping("/addBlog")
    public String showAddPage() {
        return "addBlog";
    }
    
    @PostMapping("/addBlog")
    public String postMethodName(@ModelAttribute Blogs blog) {
        //TODO: process POST request
        blogRepository.save(blog);
        return "redirect:/";
    }
    
}
