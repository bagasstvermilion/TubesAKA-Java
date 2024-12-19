package com.mycompany.tubesakajava;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTable table;

    public MainFrame() {
        setTitle("Data Game");
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Use BorderLayout for better component organization
        getContentPane().setLayout(new BorderLayout());

        // Create table with initial model
        table = new JTable();
        DefaultTableModel initialModel = new DefaultTableModel();
        initialModel.addColumn("Nama Game");
        initialModel.addColumn("Rating Game");
        initialModel.addColumn("Developer Game");
        initialModel.addColumn("Tahun Rilis");
        table.setModel(initialModel);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        
        // Create buttons
        JButton iterativeButton = new JButton("Cari (Iteratif)");
        JButton recursiveButton = new JButton("Cari (Rekursif)");
        JButton refreshButton = new JButton("Refresh Data");

        // Add action listeners
        iterativeButton.addActionListener(e -> {
            try {
                String input = JOptionPane.showInputDialog(this, 
                    "Masukkan minimum rating:",
                    "Input Rating",
                    JOptionPane.QUESTION_MESSAGE);
                
                if (input != null && !input.trim().isEmpty()) {
                    float minRating = Float.parseFloat(input);
                    DefaultTableModel model = IterativeGameSearch.searchGamesByRating(minRating);
                    
                    if (model != null && model.getRowCount() > 0) {
                        table.setModel(model);
                        System.out.println("Table updated with " + model.getRowCount() + " rows");
                    } else {
                        JOptionPane.showMessageDialog(this,
                            "Tidak ada data yang ditemukan untuk rating >= " + minRating,
                            "Tidak Ada Data",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                    "Masukkan rating yang valid (angka)",
                    "Error Input",
                    JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        recursiveButton.addActionListener(e -> {
            try {
                String input = JOptionPane.showInputDialog(this,
                    "Masukkan minimum rating:",
                    "Input Rating",
                    JOptionPane.QUESTION_MESSAGE);
                
                if (input != null && !input.trim().isEmpty()) {
                    float minRating = Float.parseFloat(input);
                    DefaultTableModel model = RecursiveGameSearch.searchGamesByRating(minRating);
                    
                    if (model != null && model.getRowCount() > 0) {
                        table.setModel(model);
                        System.out.println("Table updated with " + model.getRowCount() + " rows");
                    } else {
                        JOptionPane.showMessageDialog(this,
                            "Tidak ada data yang ditemukan untuk rating >= " + minRating,
                            "Tidak Ada Data",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                    "Masukkan rating yang valid (angka)",
                    "Error Input",
                    JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        refreshButton.addActionListener(e -> {
            try {
                DefaultTableModel model = IterativeGameSearch.searchGamesByRating(0); // Get all games
                if (model != null) {
                    table.setModel(model);
                    System.out.println("Table refreshed with " + model.getRowCount() + " rows");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error refreshing data: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        // Add buttons to panel
        buttonPanel.add(iterativeButton);
        buttonPanel.add(recursiveButton);
        buttonPanel.add(refreshButton);

        // Add button panel to frame
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Load initial data
        try {
            DefaultTableModel model = IterativeGameSearch.searchGamesByRating(0);
            if (model != null) {
                table.setModel(model);
                System.out.println("Initial data loaded with " + model.getRowCount() + " rows");
            }
        } catch (Exception e) {
            System.out.println("Error loading initial data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // Set system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            try {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}