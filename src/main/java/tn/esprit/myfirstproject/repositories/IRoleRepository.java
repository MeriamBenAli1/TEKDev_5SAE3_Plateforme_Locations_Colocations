package tn.esprit.myfirstproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.myfirstproject.entities.ERole;
import tn.esprit.myfirstproject.entities.Role;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}
