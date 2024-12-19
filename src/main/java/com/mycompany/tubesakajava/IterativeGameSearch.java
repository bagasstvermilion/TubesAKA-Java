package com.mycompany.tubesakajava;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

public class IterativeGameSearch {
    public static DefaultTableModel searchGamesByRating(float minRating) {
        ResultSet resultSet = DatabaseHelper.getGameData();
        DefaultTableModel model = new DefaultTableModel();
        
        // Define column names
        model.addColumn("Nama Game");
        model.addColumn("Rating Game");
        model.addColumn("Developer Game");
        model.addColumn("Tahun Rilis");
        
        try {
            if (resultSet == null) {
                System.out.println("No ResultSet returned from database");
                return model;
            }
            
            while (resultSet.next()) {
                float rating = resultSet.getFloat("ratingGame");
                if (rating >= minRating) {
                    model.addRow(new Object[]{
                        resultSet.getString("namaGame"),
                        rating,
                        resultSet.getString("developerGame"),
                        resultSet.getInt("tahunRilis")
                    });
                }
            }
            System.out.println("Found " + model.getRowCount() + " games matching criteria");
            
        } catch (Exception e) {
            System.out.println("Error in iterative search: " + e.getMessage());
            e.printStackTrace();
        }
        return model;
    }
}