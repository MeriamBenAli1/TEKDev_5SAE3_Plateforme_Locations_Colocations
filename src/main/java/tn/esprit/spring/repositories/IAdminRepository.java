package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.enities.Admin;
@Repository
public interface IAdminRepository extends JpaRepository<Admin, Long>  {
}
