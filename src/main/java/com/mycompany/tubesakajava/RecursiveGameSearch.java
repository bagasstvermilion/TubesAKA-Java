package com.mycompany.tubesakajava;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RecursiveGameSearch {
    private static class GameData {
        String name;
        float rating;
        String developer;
        int year;

        GameData(String name, float rating, String developer, int year) {
            this.name = name;
            this.rating = rating;
            this.developer = developer;
            this.year = year;
        }
    }

    public static DefaultTableModel searchGamesByRating(float minRating) {
        try {
            ResultSet resultSet = DatabaseHelper.getGameData();
            if (resultSet != null) {
                // Convert ResultSet to List to avoid recursive calls on ResultSet
                List<GameData> games = new ArrayList<>();
                while (resultSet.next()) {
                    games.add(new GameData(
                        resultSet.getString("namaGame"),
                        resultSet.getFloat("ratingGame"),
                        resultSet.getString("developerGame"),
                        resultSet.getInt("tahunRilis")
                    ));
                }

                // Create model with columns
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Nama Game");
                model.addColumn("Rating Game");
                model.addColumn("Developer Game");
                model.addColumn("Tahun Rilis");

                // Use recursive function on List instead of ResultSet
                return recursiveHelper(games, 0, minRating, model);
            } else {
                System.out.println("No ResultSet returned from database");
            }
        } catch (Exception e) {
            System.out.println("Error in recursive search: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private static DefaultTableModel recursiveHelper(List<GameData> games, int index, float minRating, DefaultTableModel model) {
        // Base case: if we've processed all games
        if (index >= games.size()) {
            return model;
        }

        // Process current game
        GameData game = games.get(index);
        if (game.rating >= minRating) {
            model.addRow(new Object[]{
                game.name,
                game.rating,
                game.developer,
                game.year
            });
        }

        // Recursive call with next index
        return recursiveHelper(games, index + 1, minRating, model);
    }
}