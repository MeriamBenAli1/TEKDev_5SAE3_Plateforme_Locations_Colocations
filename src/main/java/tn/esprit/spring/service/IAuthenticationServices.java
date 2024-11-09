package tn.esprit.spring.service;

import org.springframework.http.ResponseEntity;
import tn.esprit.spring.enities.AuthenticationResponse;
import tn.esprit.spring.enities.RefreshTokenRequest;
import tn.esprit.spring.enities.User;

import java.util.HashMap;

public interface IAuthenticationServices {
    //Etudiant registerEtudiant(Etudiant etudiant);
    public User registerUserSimple(User user);
    ResponseEntity<AuthenticationResponse> login(String email, String password);
    AuthenticationResponse refreshToken(RefreshTokenRequest refreshToken);
    HashMap<String,String> forgetPassword(String email);
    HashMap<String,String> resetPassword(String passwordResetToken, String newPassword);
}
