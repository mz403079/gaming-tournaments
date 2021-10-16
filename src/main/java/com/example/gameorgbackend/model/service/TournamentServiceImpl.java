package com.example.gameorgbackend.model.service;

import com.example.gameorgbackend.model.dto.basic.TournamentDTO;
import com.example.gameorgbackend.model.entity.Tournament;
import com.example.gameorgbackend.model.repository.TournamentRepository;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

@Service
public class TournamentServiceImpl implements IService<TournamentDTO> {

  private final ModelMapper modelMapper;
  private final TournamentRepository tournamentRepository;

  public TournamentServiceImpl(ModelMapper modelMapper, TournamentRepository tournamentRepository) {
    this.modelMapper = modelMapper;
    this.tournamentRepository = tournamentRepository;
  }

  @Override
  public TournamentDTO get(Long id) {
    return null;
  }

  @Override
  public Collection<TournamentDTO> getAll() {
    return modelMapper.map(tournamentRepository.findAll(),
        new TypeToken<Set<TournamentDTO>>(){
        }.getType());
  }

  @Override
  public TournamentDTO create(TournamentDTO tournamentDTO) {
    Tournament tournament = modelMapper.map(tournamentDTO,Tournament.class);
    if(tournamentRepository.existsByName(tournament.getName()))
      return null;
    tournament.setCurrentNumberOfTeams(0);
    tournamentRepository.save(tournament);
    return tournamentDTO;
  }

  @Override
  public TournamentDTO update(TournamentDTO tournamentDTO) {
    return null;
  }

  @Override
  public void delete(TournamentDTO tournamentDTO) {

  }
}
