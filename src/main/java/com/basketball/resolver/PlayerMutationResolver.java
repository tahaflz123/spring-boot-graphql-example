package com.basketball.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.basketball.entity.Player;
import com.basketball.entity.PlayerDto;
import com.basketball.repository.PlayerRepository;

import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class PlayerMutationResolver implements GraphQLMutationResolver {

	@Autowired
	private PlayerRepository playerRepository;

	public Player createPlayer(PlayerDto playerDto) {
		
		int sizeOfTeam = playerRepository.findAll().size();
		if(sizeOfTeam >= 15) {
			throw new GraphQLException("Team is full");
		}
		
		
		Player player = new Player();
		player.setName(playerDto.getName());
		player.setSurname(playerDto.getSurname());
		player.setPosition(playerDto.getPosition());
		return playerRepository.save(player);
	}
	
	public Boolean deletePlayer(Long id) {
		Boolean b = playerRepository.existsById(id);
		if(!b) {
			throw new GraphQLException("User not found");
		}
		playerRepository.deleteById(id);
		return b;
	}

}
