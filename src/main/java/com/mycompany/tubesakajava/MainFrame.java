package com.mycompany.tubesakajava;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

public class MainFrame extends JFrame {
    private JTable table;

    public MainFrame() {
        setTitle("Data Game");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 564, 339);
        getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        loadData();
    }

    private void loadData() {
        ResultSet resultSet = DatabaseHelper.getGameData();

        try {
            if (resultSet != null) {
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Nama Game");
                model.addColumn("Rating Game");
                model.addColumn("Developer Game");
                model.addColumn("Tahun Rilis");

                while (resultSet.next()) {
                    model.addRow(new Object[]{
                            resultSet.getString("namaGame"),
                            resultSet.getFloat("ratingGame"),
                            resultSet.getString("developerGame"),
                            resultSet.getInt("tahunRilis")
                    });
                }

                table.setModel(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
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
