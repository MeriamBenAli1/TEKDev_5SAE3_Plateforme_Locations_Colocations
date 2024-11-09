package tn.esprit.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.enities.DemandedRole;
import tn.esprit.spring.enities.ERole;
import tn.esprit.spring.enities.User;
import tn.esprit.spring.service.IAuthenticationServices;
import tn.esprit.spring.service.IDemandedRoleService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("users/role")
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
