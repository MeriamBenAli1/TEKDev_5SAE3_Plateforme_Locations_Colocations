package tn.esprit.myfirstproject.services;

import tn.esprit.myfirstproject.entities.DemandedRole;
import tn.esprit.myfirstproject.entities.ERole;

import java.util.List;

public interface IDemandedRoleService {
    public List<DemandedRole> getAllDemandedRoles();
    public DemandedRole addDemandedRole(Long idUser , ERole role,boolean accessApproved);
}
