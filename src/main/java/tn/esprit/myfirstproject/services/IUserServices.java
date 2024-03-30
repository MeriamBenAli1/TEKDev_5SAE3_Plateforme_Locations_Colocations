package tn.esprit.myfirstproject.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import tn.esprit.myfirstproject.entities.ERole;
import tn.esprit.myfirstproject.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserServices {
    UserDetailsService userDetailsService();
    User addUser(User user);
    User updateUser(User user);
    List<User> getAllUsers();
    User getUserById(Long idUser);
    void deleteUser(Long idUser);
     void modifyUserRole(Long idUser, ERole newRole);
    void AddRoleToUser(Long idUser, ERole newRole);
    void AddRoleToUserRemoveDemand(Long idDemand);

    public void modifyRoleInUser(Long idUser, ERole oldRoleName, ERole newRoleName);
    int countUsers();


    public void save(User user);
}
