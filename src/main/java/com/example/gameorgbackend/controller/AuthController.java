package com.example.gameorgbackend.controller;

import com.example.gameorgbackend.model.dto.basic.UserDTO;
import com.example.gameorgbackend.model.dto.specialized.JwtResponse;
import com.example.gameorgbackend.model.dto.specialized.LoginFormDTO;
import com.example.gameorgbackend.model.dto.specialized.MessageResponse;
import com.example.gameorgbackend.model.dto.specialized.RegisterFormDTO;
import com.example.gameorgbackend.model.repository.UserRepository;
import com.example.gameorgbackend.model.service.UserDetailsImpl;
import com.example.gameorgbackend.model.service.UserServiceImpl;
import com.example.gameorgbackend.security.JwtUtils;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private final AuthenticationManager authenticationManager;

  @Autowired
  private final JwtUtils jwtUtils;

  private final ModelMapper modelMapper;

  private final UserServiceImpl userService;

  private final UserRepository userRepository;

  public AuthController(
      AuthenticationManager authenticationManager,
      JwtUtils jwtUtils, ModelMapper modelMapper,
      UserServiceImpl userService,
      UserRepository userRepository) {
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
    this.modelMapper = modelMapper;
    this.userService = userService;
    this.userRepository = userRepository;
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginFormDTO loginFormDTO) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginFormDTO.getUsername(),
            loginFormDTO.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
        userDetails.getUserId(),
        userDetails.getUsername(),
        userDetails.getEmail(),
        roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterFormDTO registerFormDTO) {
    if (userRepository.existsByUsername(registerFormDTO.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(registerFormDTO.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already registered!"));
    }

    UserDTO userDTO = modelMapper.map(registerFormDTO, UserDTO.class);
    userService.create(userDTO);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
