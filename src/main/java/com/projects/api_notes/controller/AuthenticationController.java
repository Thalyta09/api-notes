package com.projects.api_notes.controller;

import com.projects.api_notes.dto.request.LoginRequestDTO;
import com.projects.api_notes.dto.request.LoginResponseDTO;
import com.projects.api_notes.entity.Login;
import com.projects.api_notes.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                loginRequestDTO.username(),
                loginRequestDTO.password()
        );

        Authentication auth = authenticationManager.authenticate(usernamePassword);
        var usuario = (Login) auth.getPrincipal();
        String token = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}