package com.cricket;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		String[] teamName = new String[2];

		System.out.println("Enter the team names");
		teamName[0] = scan.next();
		teamName[1] = scan.next();
//		System.out.println("Enter team 1 name");
		String[] team1Players = getPlayers();
		String[] team2Players = getPlayers();

		Team team1 = new Team(teamName[0], team1Players);
		Team team2 = new Team(teamName[1], team2Players);
		innings(team1, team2);
		innings(team2, team1);
		if (team1.totalRuns < team2.totalRuns) {
			team2.result = "win";
			team1.result = "lost";

		} else {
			team1.result = "win";
			team2.result = "Lost";
		}
		System.out.println("First innings");
		team1.printScore();
		System.out.println();
		team1.battingScore();
		System.out.println();

		team2.printBowlingScoreCard();
		System.out.println();

		team1.printPlayerStats();
		System.out.println();
		System.out.println("second innings");
		team2.printScore();
		System.out.println();
		team2.battingScore();
		team1.printBowlingScoreCard();
		team2.printPlayerStats();

	}

	private static void innings(Team battingTeam, Team bowlingTeam) {

		Deque<Integer> batQue = new ArrayDeque<>(Arrays.asList(0, 1));
		Queue<Integer> bowQue = new ArrayDeque<>(Arrays.asList(0, 1));
		int curOver = 0;
		while (curOver < 2 && battingTeam.batsmanOut < 2) {
			int curBowler = bowQue.peek();
			int balls = 0;
			while (balls < 6 && battingTeam.batsmanOut < 2) {
				String ballResult = scan.next();
				int curBat = batQue.peek();
				int runs = ballResult(battingTeam, bowlingTeam, ballResult, curBowler, curBat, batQue);
				if (runs % 2 == 1) {
					batQue.add(batQue.poll());
				}

				balls++;

			}
			if (battingTeam.batsmanOut < 2) {
				batQue.add(batQue.poll());
				bowQue.add(bowQue.poll());
				battingTeam.over++;
			}

			bowlingTeam.increseOver(bowlingTeam, curBowler);

			curOver++;

		}

	}

	private static int ballResult(Team battingTeam, Team bowlingTeam, String ballResult, int curBowler, int curBat,
			Queue<Integer> batQue) {
		// TODO Auto-generated method stub
		int run = 0;
		if (ballResult.equals("W")) {
			battingTeam.batsmanOut(curBat);

			bowlingTeam.incrementWickets(curBowler);
			batQue.poll();
			if (battingTeam.batsmanOut < 2) {
				((Deque<Integer>) batQue).addFirst(2);

			}

		} else if (ballResult.equals("WD") || ballResult.equals("NB")) {
			battingTeam.extras();

		} else {
			run = Integer.parseInt(ballResult);
			battingTeam.addRun(run, curBat);
			bowlingTeam.addRuns(curBowler, run);
		}
		return run;
	}

	private static String[] getPlayers() {

		String[] teamPlayers = new String[5];
		for (int i = 0; i < 5; i++) {
			teamPlayers[i] = scan.next();
		}
		return teamPlayers;
	}

}
