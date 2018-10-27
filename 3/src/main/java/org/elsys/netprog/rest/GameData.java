package org.elsys.netprog.rest;

public class GameData {
	public String gameId;
	public Integer turnsCount;
	public String secret;
	public Boolean success;
	
	public GameData(String gameId, Integer turnsCount, String secret, Boolean success) {
		super();
		this.gameId = gameId;
		this.turnsCount = turnsCount;
		this.secret = secret;
		this.success = success;
	}
	
}