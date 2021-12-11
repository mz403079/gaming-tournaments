package com.example.gameorgbackend.model.service;

import com.example.gameorgbackend.exceptions.DataNotFoundException;
import com.example.gameorgbackend.model.dto.basic.UserDTO;
import com.example.gameorgbackend.model.dto.specialized.UserInfoDTO;
import com.example.gameorgbackend.model.entity.Contact;
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

  public UserInfoDTO getUserInfo(Long id) {
    try {
      return modelMapper.map(userRepository.findById(id).orElseThrow(
          DataNotFoundException::new), UserInfoDTO.class);
    } catch(Exception e) {
      return null;
    }
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

  public UserInfoDTO update(UserInfoDTO userInfoDTO, Long userId) {
    try {
      User user = userRepository.findById(userId).orElseThrow(DataNotFoundException::new);
      user.setName(userInfoDTO.getName());
      user.setSurname(userInfoDTO.getSurname());
      if(user.getContact() == null) {
        Contact contact = new Contact();
        user.setContact(contact);
      }
      if (userRepository.existsByEmail(userInfoDTO.getContact().getEmailAddress()))
        return null;
      user.getContact().setEmailAddress(userInfoDTO.getContact().getEmailAddress());
      user.getContact().setDiscordName(userInfoDTO.getContact().getDiscordName());
      user.getContact().setPhoneNumber(userInfoDTO.getContact().getPhoneNumber());
      userRepository.save(user);
      return userInfoDTO;
    } catch(Exception e) {
      return null;
    }
  }
  @Override
  public void delete(UserDTO userDTO) {

  }
}
