package tn.esprit.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.spring.configuration.EmailService;
import tn.esprit.spring.enities.*;
import tn.esprit.spring.repositories.IRoleRepository;
import tn.esprit.spring.repositories.IUserRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class IAuthenticationServicesImp implements IAuthenticationServices {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final IJWTServices jwtServices;
    private final EmailService emailService;
    @Autowired
    IRoleRepository roleRepository;
    Set<Role> roles = new HashSet<>();

    public User registerUserSimple(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(ERole.SIMPLE_USER)
                .orElseThrow(() -> new RuntimeException("Error:Simple Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public ResponseEntity<AuthenticationResponse> login(String email, String password) {
        // Authenticate the user
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        // Find the user
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        // Generate tokens
        String jwt = jwtServices.generateToken(user);
        String refreshToken = jwtServices.generateRefreshToken(new HashMap<>(), user);

        // Prepare the authentication response
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAccessToken(jwt);
        authenticationResponse.setRefreshToken(refreshToken);

            User userDetails = convertToUserDto(user);
            authenticationResponse.setUserDetails(userDetails);


        // Include the token in the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  jwt);

        // Log the successful authentication
        System.out.println("User authenticated: " + email);

        // Return the response entity
        return ResponseEntity.ok().headers(headers).body(authenticationResponse);
    }

    private User convertToUserDto(User user) {
        User dto = new User();
        dto.setId(user.getId());
        dto.setNom(user.getNom());
        dto.setPrenom(user.getPrenom());
        dto.setImage(user.getImage());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRoles(user.getRoles());
        return dto;
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshToken) {
        String userEmail = jwtServices.extractUsername(refreshToken.getRefreshToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        if(jwtServices.isTokenValid(refreshToken.getRefreshToken(), user)) {
            var jwt = jwtServices.generateToken(user);

            AuthenticationResponse authenticationResponse = new AuthenticationResponse();

            authenticationResponse.setAccessToken(jwt);
            authenticationResponse.setRefreshToken(refreshToken.getRefreshToken());
            return authenticationResponse;
        }
        return null;
    }

    @Override
    public HashMap<String, String> forgetPassword(String email) {
        HashMap message = new HashMap();

        User userexisting = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        UUID token = UUID.randomUUID();
        userexisting.setPasswordResetToken(token.toString());
        userexisting.setId(userexisting.getId());

        Mail mail = new Mail();

        mail.setSubject("Reset Password");
        mail.setTo(userexisting.getEmail());
        mail.setContent("Votre nouveau TOKEN est : " + "http://localhost:4200/account/set-password/"+userexisting.getPasswordResetToken());        emailService.sendSimpleEmail(mail);
        userRepository.save(userexisting);
        message.put("user","user FOUND and email is Sent");
        return message;
    }

    @Override
    public HashMap<String, String> resetPassword(String passwordResetToken, String newPassword) {
        HashMap<String, String> message = new HashMap<>();
        // Find the user by the password reset token
        User userExisting = userRepository.findByPasswordResetToken(passwordResetToken)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the user exists
        if (userExisting != null) {
            // Set the new password and reset the password reset token
            userExisting.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            userExisting.setPasswordResetToken(null);
            userRepository.save(userExisting);
            message.put("resetpassword", "Password reset successfully");
        } else {
            // Prepare failure message if user is not found
            message.put("resetpassword", "Failed to reset password, user not found");
        }

        return message;
    }
}
