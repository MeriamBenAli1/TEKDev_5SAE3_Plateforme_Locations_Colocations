package tn.esprit.myfirstproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.myfirstproject.entities.DemandedRole;
import tn.esprit.myfirstproject.entities.ERole;
import tn.esprit.myfirstproject.entities.User;
import tn.esprit.myfirstproject.services.IAuthenticationServices;
import tn.esprit.myfirstproject.services.IDemandedRoleService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/role")
@RequiredArgsConstructor
public class DemandedRoleController {
    private final IDemandedRoleService iDemandedRoleService;

    @PostMapping("/make-demand")
    public DemandedRole addDemand(@RequestParam ERole role, @RequestParam  Long userId ,@RequestParam boolean accessApproved ) {
        return iDemandedRoleService.addDemandedRole(userId,role,accessApproved);
    }
    @GetMapping("/all")
    public List<DemandedRole> getAllDemands() {
        return iDemandedRoleService.getAllDemandedRoles();
    }

}
