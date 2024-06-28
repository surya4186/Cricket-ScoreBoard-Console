package com.cricket;

import java.util.LinkedHashMap;
import java.util.Map;

public class Team {
	String teamName;
	String[] palyers;
	int totalRuns;
	String[] batsman;
	String[] bowler;
	Map<String, int[]> batsmanMap;
	Map<String, int[]> bowlerMap;
	int batsmanOut;
	String result;
	int over;
	int extras;
	int wicket;
	int balls;

	Team(String teamName, String[] players) {
		this.teamName = teamName;
		this.palyers = players;
		this.totalRuns = 0;
		this.batsman = new String[3];
		this.bowler = new String[2];
		this.batsmanMap = new LinkedHashMap<>();
		this.bowlerMap = new LinkedHashMap<>();
		this.batsmanOut = 0;
		this.result = null;
		this.over = 0;
		this.balls = 0;
		this.wicket = 0;
		this.extras = 0;

		for (int i = 0; i < 5; i++) {
			if (i < 3) {
				batsman[i] = palyers[i];
				batsmanMap.put(batsman[i], new int[3]);

			} else {
				bowler[i - 3] = palyers[i];
				bowlerMap.put(bowler[i - 3], new int[2]);
			}
		}

	}

}
