package com.example.gameorgbackend.model.service;

import com.example.gameorgbackend.model.dto.basic.TeamDTO;
import com.example.gameorgbackend.model.entity.Team;
import com.example.gameorgbackend.model.repository.TeamRepository;
import java.util.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements IService<TeamDTO>{

  private final ModelMapper modelMapper;
  private final TeamRepository teamRepository;

  public TeamServiceImpl(ModelMapper modelMapper,
      TeamRepository teamRepository) {
    this.modelMapper = modelMapper;
    this.teamRepository = teamRepository;
  }

  @Override
  public TeamDTO get(Long id) {
    return null;
  }

  @Override
  public Collection<TeamDTO> getAll() {
    return null;
  }

  @Override
  public TeamDTO create(TeamDTO teamDTO) {
    Team team = modelMapper.map(teamDTO, Team.class);
    if(teamRepository.existsByTeamName(team.getTeamName()))
      return null;
    teamRepository.save(team);
    return teamDTO;
  }

  @Override
  public TeamDTO update(TeamDTO teamDTO) {
    return null;
  }

  @Override
  public void delete(TeamDTO teamDTO) {

  }
}
