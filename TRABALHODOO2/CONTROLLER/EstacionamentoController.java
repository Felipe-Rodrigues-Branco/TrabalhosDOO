
package controller;

import model.*;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EstacionamentoController {
    
    public void adicionarVeiculo(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO veiculos (placa, modelo, tipo, data_entrada, hora_entrada) VALUES (?, ?, ?, ?, ?)";
        
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        
        veiculo.setDataEntrada(agora.format(formatoData));
        veiculo.setHoraEntrada(agora.format(formatoHora));
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, veiculo.getPlaca());
            pstmt.setString(2, veiculo.getModelo());
            pstmt.setString(3, veiculo.getTipo());
            pstmt.setString(4, veiculo.getDataEntrada());
            pstmt.setString(5, veiculo.getHoraEntrada());
            pstmt.executeUpdate();
        }
    }

    public void removerVeiculo(String placa) throws SQLException {
        String sql = "DELETE FROM veiculos WHERE placa = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, placa);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected == 0) {
                throw new SQLException("Veículo com placa " + placa + " não encontrado.");
            }
        }
    }

    public List<Veiculo> listarVeiculos() throws SQLException {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculos";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String placa = rs.getString("placa");
                String modelo = rs.getString("modelo");
                String tipo = rs.getString("tipo");
                String dataEntrada = rs.getString("data_entrada");
                String horaEntrada = rs.getString("hora_entrada");
                
                Veiculo veiculo = criarVeiculo(tipo, placa, modelo, dataEntrada, horaEntrada);
                veiculos.add(veiculo);
            }
        }
        return veiculos;
    }

    public Veiculo buscarVeiculo(String placa) throws SQLException {
        String sql = "SELECT * FROM veiculos WHERE placa = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, placa);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String modelo = rs.getString("modelo");
                String tipo = rs.getString("tipo");
                String dataEntrada = rs.getString("data_entrada");
                String horaEntrada = rs.getString("hora_entrada");
                
                return criarVeiculo(tipo, placa, modelo, dataEntrada, horaEntrada);
            } else {
                throw new SQLException("Veículo com placa " + placa + " não encontrado.");
            }
        }
    }

    public void editarVeiculo(String placaOriginal, Veiculo novoVeiculo) throws SQLException {
        String sql = "UPDATE veiculos SET placa = ?, modelo = ?, tipo = ? WHERE placa = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, novoVeiculo.getPlaca());
            pstmt.setString(2, novoVeiculo.getModelo());
            pstmt.setString(3, novoVeiculo.getTipo());
            pstmt.setString(4, placaOriginal);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Veículo com placa " + placaOriginal + " não encontrado.");
            }
        }
    }

    private Veiculo criarVeiculo(String tipo, String placa, String modelo, String dataEntrada, String horaEntrada) {
        switch (tipo) {
            case "Carro":
                return new Carro(placa, modelo, dataEntrada, horaEntrada);
            case "Moto":
                return new Moto(placa, modelo, dataEntrada, horaEntrada);
            case "Caminhão":
                return new Caminhao(placa, modelo, dataEntrada, horaEntrada);
            default:
                throw new IllegalArgumentException("Tipo de veículo inválido: " + tipo);
        }
    }
}
