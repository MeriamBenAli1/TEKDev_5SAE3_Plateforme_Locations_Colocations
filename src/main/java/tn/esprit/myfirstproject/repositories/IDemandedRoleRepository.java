package tn.esprit.myfirstproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.myfirstproject.entities.DemandedRole;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDemandedRoleRepository extends JpaRepository<DemandedRole, Long> {
    List<DemandedRole> findAllByAccessApprovedFalse();
    //DemandedRole findById();
    Optional<DemandedRole> findByUserIdAndRoleId(Long userId, Long roleId);

}
