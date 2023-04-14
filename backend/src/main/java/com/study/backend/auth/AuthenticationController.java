package com.study.backend.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) throws Exception {
    return ResponseEntity.ok(service.register(request));
  }

//  @GetMapping("/user")
//  public String user(OAuth2AuthenticationToken token, Model model) {
//    String name = token.getPrincipal().getAttribute("name");
//    String email = token.getPrincipal().getAttribute("email");
//    String picture = token.getPrincipal().getAttribute("picture");
//    String id = token.getPrincipal().getAttribute("sub");
//    model.addAttribute("name", name);
//    model.addAttribute("email", email);
//    model.addAttribute("picture", picture);
//    model.addAttribute("id", id);
//    return "user";
//  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }


}
