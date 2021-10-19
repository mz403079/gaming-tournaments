package com.example.gameorgbackend.model.service;

import com.example.gameorgbackend.model.dto.basic.GameAccountDTO;
import com.example.gameorgbackend.model.entity.GameAccount;
import com.example.gameorgbackend.model.entity.Tournament;
import com.example.gameorgbackend.model.repository.GameAccountRepository;
import com.example.gameorgbackend.model.repository.TournamentRepository;
import java.util.Collection;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GameAccountServiceImpl implements IService<GameAccountDTO> {
  private final ModelMapper modelMapper;
  private final GameAccountRepository gameAccountRepository;

  public GameAccountServiceImpl(ModelMapper modelMapper,
      GameAccountRepository gameAccountRepository) {
    this.modelMapper = modelMapper;
    this.gameAccountRepository = gameAccountRepository;
  }

  @Override
  public GameAccountDTO get(Long id) {
    return null;
  }

  @Override
  public Collection<GameAccountDTO> getAll() {
    return null;
  }

  @Override
  public GameAccountDTO create(GameAccountDTO gameAccountDTO) {
    GameAccount gameAccount = modelMapper.map(gameAccountDTO,GameAccount.class);
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
}
