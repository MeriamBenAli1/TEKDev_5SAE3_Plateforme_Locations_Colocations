package tn.esprit.myfirstproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.myfirstproject.entities.Admin;
@Repository
public interface IAdminRepository extends JpaRepository<Admin, Long>  {
}
