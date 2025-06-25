package main;

import view.TelaInicial;
import util.DatabaseConnection;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            try {
                DatabaseConnection.getConnection();
                System.out.println("Conexão com banco de dados estabelecida com sucesso!");
                new TelaInicial().setVisible(true);
            } catch (Exception e) {
                System.err.println("Erro ao inicializar aplicação: " + e.getMessage());
                e.printStackTrace();
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DatabaseConnection.closeConnection();
            System.out.println("Aplicação finalizada.");
        }));
    }
}
