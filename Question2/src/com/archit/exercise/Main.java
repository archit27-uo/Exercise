package com.archit.exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filename = "E:\\project Spring\\Exercise1\\Question2\\src\\com\\archit\\exercise\\Scores.csv"; // Assuming the file name is "player_scores.txt"
        Map<String, Integer> highestScores = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] playerData = line.split(",");
                String playerName = playerData[0];
                for (int i = 1; i < playerData.length; i++) {
                    String[] data = playerData[i].split("_");
                    if (data.length == 3) {
                        int score = Integer.parseInt(data[1]);
                        if (highestScores.containsKey(playerName)) {
                            score = Math.max(score, highestScores.get(playerName));
                        }
                        highestScores.put(playerName, score);
                    }
                }
            }

           
            for (Map.Entry<String, Integer> entry : highestScores.entrySet()) {
                System.out.println("Player: " + entry.getKey() + ", Highest Score: " + entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
