package com.cricket;

import java.util.Scanner;

public class Main {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		String[] teamName = new String[2];

		System.out.println("Enter the team names");
		teamName[0] = scan.next();
		teamName[1] = scan.next();
		System.out.println("Enter team 1 name");
		String[] team1Players = getPlayers();
		String[] team2Players = getPlayers();

		Team team1 = new Team(teamName[0], team1Players);
		Team team2 = new Team(teamName[1], team2Players);

	}

	private static String[] getPlayers() {

		String[] teamPlayers = new String[5];
		for (int i = 0; i < 5; i++) {
			teamPlayers[i] = scan.next();
		}
		return teamPlayers;
	}

}
