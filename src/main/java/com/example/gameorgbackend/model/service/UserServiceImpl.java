package com.example.gameorgbackend.model.service;

import com.example.gameorgbackend.exceptions.DataNotFoundException;
import com.example.gameorgbackend.model.dto.basic.UserDTO;
import com.example.gameorgbackend.model.entity.ERole;
import com.example.gameorgbackend.model.entity.Role;
import com.example.gameorgbackend.model.entity.User;
import com.example.gameorgbackend.model.repository.RoleRepository;
import com.example.gameorgbackend.model.repository.UserRepository;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IService<UserDTO> {
  private final UserRepository userRepository;
  private final ModelMapper modelMapper;
  private final RoleRepository roleRepository;
  @Autowired
  private final PasswordEncoder encoder;
  public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
      RoleRepository roleRepository,
      PasswordEncoder encoder) {
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;
    this.roleRepository = roleRepository;
    this.encoder = encoder;
  }

  @Override
  public UserDTO get(Long id) {
    return null;
  }

  public UserDTO getByUsername(String username) {
    return modelMapper.map(userRepository.findByUsername(username).orElseThrow(
        DataNotFoundException::new), UserDTO.class);
  }
  @Override
  public Collection<UserDTO> getAll() {
    return null;
  }

  @Override
  public UserDTO create(UserDTO userDTO) {
    User user = modelMapper.map(userDTO, User.class);
    user.setPassword(encoder.encode(user.getPassword()));
    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName(ERole.ROLE_PLAYER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    roles.add(userRole);
    user.setRoles(roles);
    userRepository.save(user);
    return null;
  }

  @Override
  public UserDTO update(UserDTO userDTO) {
    return null;
  }

  @Override
  public void delete(UserDTO userDTO) {

  }
}
