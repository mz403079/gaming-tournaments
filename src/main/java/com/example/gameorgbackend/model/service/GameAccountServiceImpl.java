package com.example.gameorgbackend.model.service;

import com.example.gameorgbackend.exceptions.DataNotFoundException;
import com.example.gameorgbackend.model.dto.basic.GameAccountDTO;
import com.example.gameorgbackend.model.dto.basic.TournamentDTO;
import com.example.gameorgbackend.model.entity.GameAccount;
import com.example.gameorgbackend.model.entity.User;
import com.example.gameorgbackend.model.repository.GameAccountRepository;
import com.example.gameorgbackend.model.repository.UserRepository;
import java.util.Collection;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

@Service
public class GameAccountServiceImpl implements IService<GameAccountDTO> {
  private final ModelMapper modelMapper;
  private final GameAccountRepository gameAccountRepository;
  private final UserRepository userRepository;

  public GameAccountServiceImpl(ModelMapper modelMapper,
      GameAccountRepository gameAccountRepository,
      UserRepository userRepository) {
    this.modelMapper = modelMapper;
    this.gameAccountRepository = gameAccountRepository;
    this.userRepository = userRepository;
  }

  @Override
  public GameAccountDTO get(Long id) {
    return null;
  }

  @Override
  public Collection<GameAccountDTO> getAll() {
    return null;
  }

  public Collection<GameAccountDTO> getAll(Long id) {
    return modelMapper.map(gameAccountRepository.findByUserUserId(id),
        new TypeToken<Set<GameAccountDTO>>(){
        }.getType());
  }
  @Override
  public GameAccountDTO create(GameAccountDTO gameAccountDTO) {
    return null;
  }
  public GameAccountDTO create(GameAccountDTO gameAccountDTO, Long id) {
    GameAccount gameAccount = modelMapper.map(gameAccountDTO,GameAccount.class);
    User user = userRepository.findById(id).orElseThrow(
        DataNotFoundException::new);
    gameAccount.setUser(user);
    if(gameAccountRepository.existsByInGameNameAndGameName(gameAccount.getInGameName(), gameAccount.getGame().getName()))
      return null;
    if(gameAccountRepository.existsByGameNameAndUserUserId(gameAccount.getGame().getName(),gameAccount.getUser().getUserId()))
      return null;
    gameAccountRepository.save(gameAccount);
    return gameAccountDTO;
  }
  @Override
  public GameAccountDTO update(GameAccountDTO gameAccountDTO) {
    return null;
  }

  @Override
  public void delete(GameAccountDTO gameAccountDTO) {

  }

  public void delete(Long id) {
    GameAccount gameAccount = gameAccountRepository.findById(id).orElseThrow(
        DataNotFoundException::new);
    gameAccountRepository.delete(gameAccount);
  }
}
