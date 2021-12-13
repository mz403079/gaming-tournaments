package com.example.gameorgbackend.model.service;
import com.example.gameorgbackend.model.dto.basic.GameDTO;
import com.example.gameorgbackend.model.repository.GameRepository;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements IService<GameDTO>{

  private final ModelMapper modelMapper;
  private final GameRepository gameRepository;

  public GameServiceImpl(ModelMapper modelMapper,
      GameRepository gameRepository) {
    this.modelMapper = modelMapper;
    this.gameRepository = gameRepository;
  }


  @Override
  public GameDTO get(Long id) {
    return null;
  }

  @Override
  public Collection<GameDTO> getAll() {
    return modelMapper.map(gameRepository.findAll((Sort.by(Sort.Direction.ASC, "name"))),
        new TypeToken<List<GameDTO>>(){
        }.getType());
  }

  @Override
  public GameDTO create(GameDTO gameDTO) {
    return null;
  }

  @Override
  public GameDTO update(GameDTO gameDTO) {
    return null;
  }

  @Override
  public void delete(GameDTO gameDTO) {

  }
}
