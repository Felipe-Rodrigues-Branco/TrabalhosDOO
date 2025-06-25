
package view;

import controller.EstacionamentoController;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TelaCadastro extends JFrame {
    private EstacionamentoController controller;
    private JTextField txtPlaca;
    private JTextField txtModelo;
    private JComboBox<String> cmbTipo;
    
    public TelaCadastro() {
        controller = new EstacionamentoController();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Cadastrar Veículo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBackground(new Color(230, 230, 250));
        
        // Título
        JLabel lblTitulo = new JLabel("Cadastrar Veículo", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(25, 25, 112));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Painel do formulário
        JPanel painelForm = new JPanel(new GridBagLayout());
        painelForm.setBackground(new Color(230, 230, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Campos do formulário
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        painelForm.add(new JLabel("Placa:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtPlaca = new JTextField(15);
        painelForm.add(txtPlaca, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        painelForm.add(new JLabel("Modelo:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtModelo = new JTextField(15);
        painelForm.add(txtModelo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        painelForm.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        cmbTipo = new JComboBox<>(new String[]{"Carro", "Moto", "Caminhão"});
        painelForm.add(cmbTipo, gbc);
        
        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.setBackground(new Color(230, 230, 250));
        
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnVoltar = new JButton("Voltar");
        
        estilizarBotao(btnCadastrar, new Color(34, 139, 34));
        estilizarBotao(btnLimpar, new Color(255, 140, 0));
        estilizarBotao(btnVoltar, new Color(220, 20, 60));
        
        // Eventos dos botões
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarVeiculo();
            }
        });
        
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
        
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaInicial().setVisible(true);
                dispose();
            }
        });
        
        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnVoltar);
        
        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        painelPrincipal.add(painelForm, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
        
        add(painelPrincipal);
    }
    
    private void estilizarBotao(JButton botao, Color cor) {
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createRaisedBevelBorder());
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private void cadastrarVeiculo() {
        try {
            String placa = txtPlaca.getText().trim();
            String modelo = txtModelo.getText().trim();
            String tipo = (String) cmbTipo.getSelectedItem();
            
            if (placa.isEmpty() || modelo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Veiculo veiculo;
            switch (tipo) {
                case "Carro":
                    veiculo = new Carro(placa, modelo);
                    break;
                case "Moto":
                    veiculo = new Moto(placa, modelo);
                    break;
                case "Caminhão":
                    veiculo = new Caminhao(placa, modelo);
                    break;
                default:
                    throw new IllegalArgumentException("Tipo inválido");
            }
            
            controller.adicionarVeiculo(veiculo);
            JOptionPane.showMessageDialog(this, "Veículo cadastrado com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
            
        } catch (SQLException ex) {
            if (ex.getMessage().contains("UNIQUE constraint failed")) {
                JOptionPane.showMessageDialog(this, "Já existe um veículo com esta placa!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar veículo: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limparCampos() {
        txtPlaca.setText("");
        txtModelo.setText("");
        cmbTipo.setSelectedIndex(0);
        txtPlaca.requestFocus();
    }
}