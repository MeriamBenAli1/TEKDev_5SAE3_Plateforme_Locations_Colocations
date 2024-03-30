package tn.esprit.myfirstproject.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import tn.esprit.myfirstproject.entities.DemandedRole;
import tn.esprit.myfirstproject.entities.ERole;
import tn.esprit.myfirstproject.entities.Role;
import tn.esprit.myfirstproject.entities.User;
import tn.esprit.myfirstproject.repositories.IDemandedRoleRepository;
import tn.esprit.myfirstproject.repositories.IRoleRepository;
import tn.esprit.myfirstproject.repositories.IUserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class IUserServicesImp implements IUserServices {
    @Autowired
    IRoleRepository roleRepository;
    @Autowired
    IDemandedRoleRepository iDemandedRoleRepository;
    private final IUserRepository userRepository;
    private final  IDemandedRoleService iDemandedRoleService;

    Set<Role> roles = new HashSet<>();
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String s) {
                return userRepository.findByEmail(s).orElseThrow(() -> new RuntimeException("User not found"));
            }
        };
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long idUser) {
        return userRepository.findById(idUser).orElse(null);
    }

    @Override
    public void deleteUser(Long idUser) {
        userRepository.deleteById(idUser);
    }

    @Override
    public void modifyUserRole(Long idUser, ERole newRole) {
        Role userRole = roleRepository.findByName(newRole)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        userRepository.findById(idUser).orElse(null).setRoles(roles);
    }

    @Override
    public void AddRoleToUser(Long idUser, ERole newRole) {
        Role userRole = roleRepository.findByName(newRole)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        User user =  userRepository.findById(idUser).orElse(null);
        user.getRoles().add(userRole);
        userRepository.save(user);



    }

    @Override
    public void AddRoleToUserRemoveDemand(Long idDemand ) {
        DemandedRole demand = iDemandedRoleRepository.findById(idDemand)
                .orElseThrow(() -> new RuntimeException("Error: demand not found."));

        if (demand != null) {

            Role userRole = roleRepository.findByName(demand.getRole().getName())
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            User user = userRepository.findById(demand.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("Error: User not found."));
            demand.setAccessApproved(true);
            user.getRoles().add(userRole);
            iDemandedRoleRepository.save(demand);

        } else  {
            throw new IllegalArgumentException("Demand not found: " + demand);
        }


    }

    @Override
    public void modifyRoleInUser(Long idUser, ERole oldRoleName, ERole newRoleName) {

        User user =  userRepository.findById(idUser).orElse(null);
        if (user== null) {
            throw new IllegalArgumentException("User not found with email: " + idUser);
        }


        // Find the old role by name
        Role oldRole = roleRepository.findByName(oldRoleName)
                .orElseThrow(() -> new IllegalArgumentException("Old role not found with name: " + oldRoleName));

        // Find the new role by name
        Role newRole = roleRepository.findByName(newRoleName)
                .orElseThrow(() -> new IllegalArgumentException("New role not found with name: " + newRoleName));
System.out.println(idUser);
        System.out.println(oldRoleName);
        System.out.println(oldRoleName);
        // Update the role in the user's set of roles
        if (oldRoleName!= null) {
            user.getRoles().remove(oldRole);
            user.getRoles().add(newRole); // Add new role
        } else {
            throw new IllegalArgumentException("User does not have the old role: " + oldRoleName);
        }

        // Save the user to persist the changes
        userRepository.save(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
    @Override
    public int countUsers() {
        return userRepository.countUsers();
    }


}
