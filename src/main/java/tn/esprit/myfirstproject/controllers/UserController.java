package tn.esprit.myfirstproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.myfirstproject.entities.ERole;
import tn.esprit.myfirstproject.entities.User;
import tn.esprit.myfirstproject.services.IUserServices;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

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

}
