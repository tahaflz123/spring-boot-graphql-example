package com.basketball.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.basketball.entity.Player;
import com.basketball.repository.PlayerRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;


@Component
public class PlayerQueryResolver implements GraphQLQueryResolver {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	public List<Player> findAllPlayers(){
		return playerRepository.findAll();
	}
	
}
