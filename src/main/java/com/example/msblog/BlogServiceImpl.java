package com.example.msblog;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserClient jobServiceClient;

    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public Blog getBlogById(Integer id) {
        return blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
    }
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Blog updateBlog(Integer id, Blog blogDetails) {
        Blog blog = getBlogById(id);
        blog.setTitle(blogDetails.getTitle());
        blog.setContent(blogDetails.getContent());
        return blogRepository.save(blog);
    }

    public void deleteBlog(Integer id) {
        blogRepository.deleteById(id);
    }


    public List<User> getAllUsers() {
        return jobServiceClient.getAllUsers();
    }

    public User getUserById(Integer id) {
        return jobServiceClient.getUserById(id);
    }
}


