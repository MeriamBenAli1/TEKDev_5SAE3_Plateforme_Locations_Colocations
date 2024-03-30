package tn.esprit.myfirstproject.services;

import org.springframework.http.ResponseEntity;
import tn.esprit.myfirstproject.entities.AuthenticationResponse;
import tn.esprit.myfirstproject.entities.RefreshTokenRequest;
import tn.esprit.myfirstproject.entities.User;

import java.util.HashMap;

public interface IAuthenticationServices {
    //Etudiant registerEtudiant(Etudiant etudiant);
    public User registerUserSimple(User user);
    ResponseEntity<AuthenticationResponse> login(String email, String password);
    AuthenticationResponse refreshToken(RefreshTokenRequest refreshToken);
    HashMap<String,String> forgetPassword(String email);
    HashMap<String,String> resetPassword(String passwordResetToken, String newPassword);
}
