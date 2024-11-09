package tn.esprit.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.enities.ERole;
import tn.esprit.spring.enities.User;
import tn.esprit.spring.service.IUserServices;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static tn.esprit.spring.controller.AuthenticationController.uploadDirectory;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserServices userServices;

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userServices.addUser(user);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userServices.updateUser(user);
    }

    @PostMapping("/modify-role/")
    public ResponseEntity<?> modifyUserRole(@RequestParam Long userId, @RequestParam ERole oldRoleName , @RequestParam ERole NewRole ) {
        try {


            userServices.modifyRoleInUser(userId,oldRoleName,NewRole);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/add-role/{userId}")
    public ResponseEntity<?> AddRoleToUser(@PathVariable Long userId, @RequestParam ERole NewRole ) {
        try {


            userServices.AddRoleToUser(userId,NewRole);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Adding role");
        }
    }
    //@PreAuthorize("@roleUtils.hasRole(authentication, 'ADMIN')")
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userServices.getAllUsers();
    }

    @GetMapping("/{idUser}")
    public User getUserById(@PathVariable Long idUser) {
        return userServices.getUserById(idUser);
    }

    @DeleteMapping("/delete/{idUser}")
    public void deleteUser(@PathVariable Long idUser) {
        userServices.deleteUser(idUser);
    }

    @GetMapping("/count")
    public int countUsers() {
        return userServices.countUsers();
    }



    @PostMapping("/addRole/{demandId}")
    public ResponseEntity<String> addRoleToUserAndApproveAccess(@PathVariable Long demandId) {
        try {
            userServices.AddRoleToUserRemoveDemand(demandId);
            return ResponseEntity.ok("Role added to user and access approved successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/updateImage/{idUser}")
    public ResponseEntity<?> updateImage(@PathVariable Long idUser, @RequestParam("image") MultipartFile file) {
        try {
            User updatedUser = userServices.updateImage(idUser, file);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating image: " + e.getMessage());
        }
    }

    @GetMapping("profile/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws MalformedURLException {

        Path filePath = Paths.get(uploadDirectory).resolve(filename);
        Resource file = new UrlResource(filePath.toUri());

        if (file.exists() || file.isReadable()) {
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                    .body(file);
        } else {
            throw new RuntimeException("Could not read the file!");
        }
    }
}
