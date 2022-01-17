package com.basketball.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.basketball.entity.Position;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;

import graphql.kickstart.autoconfigure.web.servlet.GraphQLWebsocketAutoConfiguration;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = GraphQLWebsocketAutoConfiguration.class)
class BasketballApplicationTests {

	@Autowired
	private GraphQLTestTemplate testTemplate;

	
	@Test
	void createPlayer_findAllPlayers_deletePlayerTest() throws IOException {
		//createPlayer - mutation
		GraphQLResponse createResponse = testTemplate.postForResource("create-player.graphql");
		assertTrue(createResponse.isOk());
		
		String id = createResponse.get("$.data.createPlayer.id");
		String name = createResponse.get("$.data.createPlayer.name");
		String surname = createResponse.get("$.data.createPlayer.surname");
		String position = createResponse.get("$.data.createPlayer.position");
		
		assertEquals(1L, Long.parseLong(id));
		assertEquals("taha" ,name);
		assertEquals("filiz", surname);
		assertEquals(Position.C.name(), position);
		
		
		//findAllPlayers - query
		GraphQLResponse listResponse = testTemplate.postForResource("find-all-players.graphql");
		
		assertTrue(listResponse.isOk());
		assertEquals(1L, Long.parseLong(listResponse.get("$.data.findAllPlayers.[0].id")));
		assertEquals("taha", listResponse.get("$.data.findAllPlayers.[0].name"));
		assertEquals("filiz", listResponse.get("$.data.findAllPlayers.[0].surname"));
		assertEquals(Position.C.name(), listResponse.get("$.data.findAllPlayers.[0].position"));
		
		//deletePlayer - mutation
		GraphQLResponse deleteResponse = testTemplate.postForResource("player-delete.graphql");
		
		boolean b = deleteResponse.isOk();
		
		assertTrue(b);
	}
}

