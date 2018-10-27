package org.elsys.netprog.rest;

public class Game {
	public String gameId;
	public Integer cowsNumber;
	public Integer bullsNumber;
	public Integer turnsCount;
	public Boolean success;

	public Game(String gameId, Integer cowsNumber, Integer bullsNumber, Integer turnsCount, Boolean success) {
		super();
		this.gameId = gameId;
		this.cowsNumber = cowsNumber;
		this.bullsNumber = bullsNumber;
		this.turnsCount = turnsCount;
		this.success = success;
	}
}