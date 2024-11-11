package com.example.msblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {


@Autowired
private BlogService blogService;

    @Autowired
    private UserClient userClient;
    @RequestMapping("/all")
    public List<User> getAllUsers() {
        return userClient.getAllUsers();
    }

    @RequestMapping("/one/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userClient.getUserById(id);
    }
    @GetMapping("/hello")
    public String home() {
        return "Hello, Blog!";
    }
    @GetMapping("/getBlog/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Integer id) {
        Blog blog = blogService.getBlogById(id);
        return ResponseEntity.ok(blog);
    }

    @GetMapping("/getBlogs")
//    @RolesAllowed("admin")
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @PutMapping("/updateblogs/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Integer id, @RequestBody Blog blogDetails) {
        Blog updatedBlog = blogService.updateBlog(id, blogDetails);
        return ResponseEntity.ok(updatedBlog);
    }

    @DeleteMapping("/deleteblogs/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Integer id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/admin")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        Blog createdBlog = blogService.createBlog(blog);
        return ResponseEntity.ok(createdBlog);
    }
}
