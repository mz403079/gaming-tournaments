package com.example.gameorgbackend.model.service;

import com.example.gameorgbackend.exceptions.DataNotFoundException;
import com.example.gameorgbackend.model.dto.basic.TeamDTO;
import com.example.gameorgbackend.model.dto.basic.UserDTO;
import com.example.gameorgbackend.model.entity.Team;
import com.example.gameorgbackend.model.entity.Tournament;
import com.example.gameorgbackend.model.entity.User;
import com.example.gameorgbackend.model.repository.TeamRepository;
import com.example.gameorgbackend.model.repository.TournamentRepository;
import com.example.gameorgbackend.model.repository.UserRepository;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements IService<TeamDTO>{

  private final ModelMapper modelMapper;
  private final TeamRepository teamRepository;
  private final TournamentRepository tournamentRepository;
  private final UserRepository userRepository;

  public TeamServiceImpl(ModelMapper modelMapper,
      TeamRepository teamRepository,
      TournamentRepository tournamentRepository,
      UserRepository userRepository) {
    this.modelMapper = modelMapper;
    this.teamRepository = teamRepository;
    this.tournamentRepository = tournamentRepository;
    this.userRepository = userRepository;
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
    return null;
  }
  public String createGetRes(TeamDTO teamDTO) {
    try {
      Team team = modelMapper.map(teamDTO, Team.class);
      Tournament tournament = tournamentRepository.findById(teamDTO.getTournament().
          getTournamentId()).orElseThrow(
          DataNotFoundException::new);
      team.setTournament(tournament);
      Set<User> players = new HashSet<>();
      for (UserDTO player : teamDTO.getPlayers()) {
        players.add(userRepository.findById(player.getUserId()).orElseThrow(
            DataNotFoundException::new));
      }
      team.setPlayers(players);
      if (teamRepository.existsByTeamName(team.getTeamName()))
        return "team exists";
      for (User player : team.getPlayers()) {
        player.addTeam(team);
      }
      tournament.setCurrentNumberOfTeams(tournament.getCurrentNumberOfTeams()+1);
      tournamentRepository.save(tournament);
      teamRepository.save(team);

      return "ok";
    }catch(Exception e) {
      return "no user";
    }
  }

  @Override
  public TeamDTO update(TeamDTO teamDTO) {
    return null;
  }

  @Override
  public void delete(TeamDTO teamDTO) {

  }
}
