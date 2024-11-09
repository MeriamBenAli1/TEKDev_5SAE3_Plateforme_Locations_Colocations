package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.enities.ERole;
import tn.esprit.spring.enities.Role;
import tn.esprit.spring.enities.User;

import java.util.List;
import java.util.Optional;
@Repository

public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);


    Optional<User> findByPasswordResetToken(String passwordResetToken);
  //  User findByRole(Role role);
    User findByRolesContains(Role role);
    User findByRolesName(ERole roleName);


    @Query("SELECT COUNT(u) FROM User u")
    int countUsers();
   // List<User> findByRoleAndAccessApprovedFalse(String role);
   // Optional<User> findByIdAndAccessApprovedFalse(Long id);

}
