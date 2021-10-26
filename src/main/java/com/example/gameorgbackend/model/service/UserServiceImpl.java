package com.example.gameorgbackend.model.service;

import com.example.gameorgbackend.exceptions.DataNotFoundException;
import com.example.gameorgbackend.model.dto.basic.UserDTO;
import com.example.gameorgbackend.model.repository.UserRepository;
import java.util.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IService<UserDTO> {
  private final UserRepository userRepository;
  private final ModelMapper modelMapper;

  public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;
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
