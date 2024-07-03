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
		this.batsmanMap = new LinkedHashMap<>(); // [runs, balls, out]
		this.bowlerMap = new LinkedHashMap<>(); // [overs, balls, runs, wickets]
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
				bowlerMap.put(bowler[i - 3], new int[4]);
			}
		}

	}

	public void addRun(int run, int curBat) {
		
		totalRuns += run;
		batsmanMap.get(batsman[curBat])[0] += run;
		batsmanMap.get(batsman[curBat])[1]++;

	}

	public void batsmanOut(int curBat) {
	
		batsmanOut++;
		wicket++;
		batsmanMap.get(batsman[curBat])[2] = 1;
		batsmanMap.get(batsman[curBat])[1]++;

	}

	public void incrementWickets(int curBowler) {
		
		bowlerMap.get(bowler[curBowler])[3]++;


	}

	public void addRuns(int bowlerIndex, int runs) {
		bowlerMap.get(bowler[bowlerIndex])[2] += runs;
		bowlerMap.get(bowler[bowlerIndex])[1]++;
	}

	public void extras() {

		extras++;

	}

	public void printScore() {
		System.out.println(teamName + "---" + totalRuns + "------ " + " wicket :" + wicket);
	}

	public void battingScore() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 3; i++) {
			if (batsmanMap.get(batsman[i])[2] != 1) {
				System.out.println(batsman[i] + "*---runs: " + batsmanMap.get(batsman[i])[0] + "("
						+ batsmanMap.get(batsman[i])[1] + ")");
			} else {
				System.out.println(batsman[i] + "---runs: " + batsmanMap.get(batsman[i])[0] + "("
						+ batsmanMap.get(batsman[i])[1] + ")");

			}
		}

	}

	public void printBowlingScoreCard() {
		for (int i = 0; i < 2; i++) {
			System.out.println(bowler[i] + " " + bowlerMap.get(bowler[i])[0] + " " + bowlerMap.get(bowler[i])[1] + " "
					+ bowlerMap.get(bowler[i])[2] + " / " + bowlerMap.get(bowler[i])[3]);
		}
	}

	public void printPlayerStats() {
		for (int i = 0; i < 5; i++) {
			if (i < 3) {
				int runs = batsmanMap.get(batsman[i])[0];
				int balls = batsmanMap.get(batsman[i])[1];
				float sr = (int) 100 * ((float) runs / balls);
				float c = (100 * ((float) runs / totalRuns));
				int srInt = (int) Math.ceil(sr);
				int cInt = (int) Math.ceil(c);
				System.out.println(batsman[i] + " - " + runs + "(" + balls + ") " + srInt + "% " + cInt + " " + runs
						+ "/" + totalRuns);
			} else {
				int runs = bowlerMap.get(bowler[i - 3])[2];
				int wicket = bowlerMap.get(bowler[i - 3])[3];
				int c = bowlerMap.get(bowler[i - 3])[3] / wicket;
				String overs = bowlerMap.get(bowler[i - 3])[0] + "" + bowlerMap.get(bowler[i - 3])[1];
				if (wicket == 0) {
					System.out.println(
							bowler[i - 3] + " " + runs + "/" + wicket + " (" + overs + ") " + "W " + wicket + " " + 0);
				} else {
					System.out.println(bowler[i - 3] + " " + runs + "/" + wicket + " (" + overs + ") " + "W " + wicket
							+ " " + (c / wicket) * 100);
				}
			}
		}
	}

	public void increseOver(Team bowlingTeam, int curBowler) {
		// TODO Auto-generated method stub
		bowlingTeam.bowlerMap.get(bowler[curBowler])[0]++;

	}

}
