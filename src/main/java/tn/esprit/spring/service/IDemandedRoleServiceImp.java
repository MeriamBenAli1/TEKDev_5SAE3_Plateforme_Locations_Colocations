package tn.esprit.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.enities.DemandedRole;
import tn.esprit.spring.enities.ERole;
import tn.esprit.spring.enities.Role;
import tn.esprit.spring.enities.User;
import tn.esprit.spring.repositories.IDemandedRoleRepository;
import tn.esprit.spring.repositories.IRoleRepository;
import tn.esprit.spring.repositories.IUserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor

public class IDemandedRoleServiceImp implements IDemandedRoleService{
    @Autowired
    private IDemandedRoleRepository demandedRoleRepository;

    @Autowired
    IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    @Override
    public List<DemandedRole> getAllDemandedRoles() {
        return demandedRoleRepository.findAll();
    }

    @Override
    public DemandedRole addDemandedRole(Long idUser , ERole role,boolean accessApproved) {
        User user =  userRepository.findById(idUser).orElse(null);
        Role userRole = roleRepository.findByName(role)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        Optional<DemandedRole> existingDemandedRole = demandedRoleRepository.findByUserIdAndRoleId(idUser, userRole.getId());
        if (existingDemandedRole.isPresent()) {
            throw new IllegalArgumentException("Demanded role already exists for the user and role.");
        }

        DemandedRole demandedRole=new DemandedRole();
        demandedRole.setUser(user);
        demandedRole.setRole(userRole);
        demandedRole.setAccessApproved(accessApproved);
        return demandedRoleRepository.save(demandedRole);
    }
}
