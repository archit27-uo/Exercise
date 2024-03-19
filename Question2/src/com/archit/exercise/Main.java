package com.archit.exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filename = "E:\\project Spring\\playerScore\\src\\com\\archit\\exercise\\Scores.csv"; // Assuming the file name is "player_scores.txt"
        Map<String, Integer> highestScores = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] entries = line.split(",");
                String playerName = entries[0];
                for (int i = 1; i < entries.length; i++) {
                    String[] matchDetails = entries[i].split("_");
                    if (matchDetails.length == 3) {
                        int score = Integer.parseInt(matchDetails[1]);
                        if (highestScores.containsKey(playerName)) {
                            score = Math.max(score, highestScores.get(playerName));
                        }
                        highestScores.put(playerName, score);
                    }
                }
            }

            // Print highest scores for each player
            for (Map.Entry<String, Integer> entry : highestScores.entrySet()) {
                System.out.println("Player: " + entry.getKey() + ", Highest Score: " + entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
