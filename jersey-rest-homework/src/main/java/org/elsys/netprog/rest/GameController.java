package org.elsys.netprog.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Random;


@Path("/game")
public class GameController {
	private static HashMap<String, Integer> games = new HashMap<String, Integer>();
	private static HashMap<String, Integer> turns = new HashMap<String, Integer>();
	private static HashMap<String, Boolean> successes = new HashMap<String, Boolean>();
	
	@POST
	@Path("/startGame")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response startGame() throws URISyntaxException{
		//TODO: Add your code here
		String uid = generateString();
		games.put(uid, generateNumber());
		turns.put(uid, 0);
		return Response.created(new URI("/games")).entity(uid).build();
	}
	
	@PUT
	@Path("/guess/{id}/{guess}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response guess(@PathParam("id") String gameId, @PathParam("guess") String guess) throws Exception{
		//TODO: Add your code here
		if(!games.containsKey(gameId)) {
			return Response.status(404).build();
		}
		
		if(!isValidNumber(guess)) {
			return Response.status(400).build();
		}
		
		turns.put(gameId, turns.get(gameId) + 1);
		
		String real = games.get(gameId).toString();
		
		int bulls = 0, cows = 0;
		
		for(int i = 0 ; i < 4 ; ++ i) {
			for(int j = 0 ; j < 4 ; ++ j) {
				if(real.charAt(i) == guess.charAt(j)) {
					if(i == j) {
						++ bulls;
					}else {
						++ cows;
					}
				}
			}
		}
		
		if(bulls == 4) {
			successes.put(gameId, true);
		}
		
		return Response.status(200).entity(new Game(gameId, cows, bulls, turns.get(gameId), bulls == 4)).build();
	}
	
	@GET
	@Path("/games")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response getGames() {
		//TODO: Add your code here
		return Response.status(200).entity(buildGamesArrayList()).build();
	}
	
	 private static String generateString() {
	        String uuid = UUID.randomUUID().toString();
	        return uuid;
	 }
	 
	 private static Integer generateNumber() {
		 Random rand = new Random();
		 return rand.nextInt(9000) + 1000;
	 }
	 
	 private static boolean isValidNumber(String num) {
		 if(num.length() != 4) {
			 return false;
		 }
		 ArrayList<Character> numList = new ArrayList<Character>();
		 
		 for(int i = 0 ; i < num.length() ; ++ i) {
			 numList.add(num.charAt(i));
		 }
		 
		 if(num.length() != numList.stream().
				 distinct().collect(Collectors.toList()).size()) {
			 return false;
		 }
		 
		 return true;
	 }
	 
	 private static ArrayList<GameData> buildGamesArrayList() {
		 ArrayList<GameData> gamesList = new ArrayList<GameData>();
		 
		 for(String uid : games.keySet()) {
			 gamesList.add(new GameData(uid, turns.get(uid), 
					 successes.get(uid) ? games.get(uid).toString() : "****", successes.get(uid)));
			
		 }
		 
		 return gamesList;
	 }
}
