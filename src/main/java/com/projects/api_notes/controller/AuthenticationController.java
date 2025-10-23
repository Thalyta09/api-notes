package com.projects.api_notes.controller;

import com.projects.api_notes.dto.request.LoginRequestDTO;
import com.projects.api_notes.dto.request.LoginResponseDTO;
import com.projects.api_notes.entity.Login;
import com.projects.api_notes.service.TokenService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Login", description = "Operação para obter token de autenticação")
@RestController
@RequestMapping("/login")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LoginRequestDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Usuário e/ou senha incorreto.", content = @Content)
    })
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