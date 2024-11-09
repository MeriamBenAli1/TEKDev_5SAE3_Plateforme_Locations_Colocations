package tn.esprit.spring.service;

import tn.esprit.spring.enities.DemandedRole;
import tn.esprit.spring.enities.ERole;

import java.util.List;

public interface IDemandedRoleService {
    public List<DemandedRole> getAllDemandedRoles();
    public DemandedRole addDemandedRole(Long idUser , ERole role,boolean accessApproved);
}
