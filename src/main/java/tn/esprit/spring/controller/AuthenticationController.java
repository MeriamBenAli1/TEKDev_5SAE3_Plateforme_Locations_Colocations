package tn.esprit.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.enities.*;
import tn.esprit.spring.service.IAuthenticationServices;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  public static String uploadDirectory = System.getProperty("user.dir") + "/uploadUser";

  private final IAuthenticationServices authenticationServices;

  @PostMapping("/registerUser")
  public ResponseEntity<User> registerUser(@RequestParam("nom") String nom,
                                           @RequestParam("prenom") String prenom,
                                           @RequestParam("email") String email,
                                           @RequestParam("password") String password) throws IOException {
    User user  = new User();
    user.setNom(nom);
    user.setPrenom(prenom);
    user.setEmail(email);
    user.setPassword(password);




    User saveduser= authenticationServices.registerUserSimple(user);
    return ResponseEntity.ok(saveduser);
  }

  @GetMapping("/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws MalformedURLException {

    Path filePath = Paths.get(uploadDirectory).resolve(filename);
    Resource file = new UrlResource(filePath.toUri());

    if (file.exists() || file.isReadable()) {
      return ResponseEntity
              .ok()
              .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
              .body(file);
    } else {
      throw new RuntimeException("Could not read the file!");
    }
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(@RequestBody User user) {
    return authenticationServices.login(user.getEmail(), user.getPassword());
  }

  @PostMapping("/refreshToken")
  public AuthenticationResponse refreshToken(@RequestBody RefreshTokenRequest refreshToken) {
      return authenticationServices.refreshToken(refreshToken);
  }

  @PostMapping("/forgetpassword")
  public HashMap<String,String> forgetPassword(@RequestParam String email){
        return authenticationServices.forgetPassword(email);
  }

  @PostMapping("/resetPassword/{passwordResetToken}")
  public HashMap<String,String> resetPassword(@PathVariable String passwordResetToken,@RequestParam  String newPassword){
    return authenticationServices.resetPassword(passwordResetToken, newPassword);
  }
}
