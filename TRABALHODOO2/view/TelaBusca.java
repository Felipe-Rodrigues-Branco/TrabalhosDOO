
package view;
import controller.EstacionamentoController;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TelaBusca extends JFrame {
    private EstacionamentoController controller;
    private JTextField txtPlacaBusca;
    private JTextField txtPlacaEdicao;
    private JTextField txtModelo;
    private JComboBox<String> cmbTipo;
    private JLabel lblDataEntrada;
    private JLabel lblHoraEntrada;
    private JButton btnEditar;
    private JButton btnRemover;
    private Veiculo veiculoAtual;
    private String placaOriginal;

    public TelaBusca() {
        controller = new EstacionamentoController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Buscar/Editar Veículo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBackground(new Color(230, 230, 250));

        // Título
        JLabel lblTitulo = new JLabel("Buscar/Editar Veículo", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(25, 25, 112));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Painel de busca
        JPanel painelBusca = new JPanel(new FlowLayout());
        painelBusca.setBackground(new Color(230, 230, 250));
        painelBusca.setBorder(BorderFactory.createTitledBorder("Buscar Veículo"));
        painelBusca.add(new JLabel("Placa:"));
        txtPlacaBusca = new JTextField(15);
        painelBusca.add(txtPlacaBusca);

        JButton btnBuscar = new JButton("Buscar");
        estilizarBotao(btnBuscar, new Color(100, 149, 237));
        painelBusca.add(btnBuscar);

        // Painel de edição
        JPanel painelEdicao = new JPanel(new GridBagLayout());
        painelEdicao.setBackground(new Color(230, 230, 250));
        painelEdicao.setBorder(BorderFactory.createTitledBorder("Dados do Veículo"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Campos de edição
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        painelEdicao.add(new JLabel("Placa:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtPlacaEdicao = new JTextField(15);
        txtPlacaEdicao.setEnabled(false);
        painelEdicao.add(txtPlacaEdicao, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        painelEdicao.add(new JLabel("Modelo:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtModelo = new JTextField(15);
        txtModelo.setEnabled(false);
        painelEdicao.add(txtModelo, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        painelEdicao.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        cmbTipo = new JComboBox<>(new String[]{"Carro", "Moto", "Caminhão"});
        cmbTipo.setEnabled(false);
        painelEdicao.add(cmbTipo, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        painelEdicao.add(new JLabel("Data Entrada:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        lblDataEntrada = new JLabel("-");
        painelEdicao.add(lblDataEntrada, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE;
        painelEdicao.add(new JLabel("Hora Entrada:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        lblHoraEntrada = new JLabel("-");
        painelEdicao.add(lblHoraEntrada, gbc);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.setBackground(new Color(230, 230, 250));

        btnEditar = new JButton("Editar");
        btnRemover = new JButton("Remover");
        JButton btnVoltar = new JButton("Voltar");

        estilizarBotao(btnEditar, new Color(255, 140, 0));
        estilizarBotao(btnRemover, new Color(220, 20, 60));
        estilizarBotao(btnVoltar, new Color(128, 128, 128));

        btnEditar.setEnabled(false);
        btnRemover.setEnabled(false);

        // Eventos dos botões
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarVeiculo();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnEditar.getText().equals("Editar")) {
                    habilitarEdicao();
                } else {
                    salvarEdicao();
                }
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerVeiculo();
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaInicial().setVisible(true);
                dispose();
            }
        });

        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnVoltar);

        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        JPanel painelCentro = new JPanel(new BorderLayout());
        painelCentro.setBackground(new Color(230, 230, 250));
        painelCentro.add(painelBusca, BorderLayout.NORTH);
        painelCentro.add(painelEdicao, BorderLayout.CENTER);
        painelPrincipal.add(painelCentro, BorderLayout.CENTER);
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

    private void buscarVeiculo() {
        String placa = txtPlacaBusca.getText().trim();
        if (placa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite uma placa para buscar!",
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            veiculoAtual = controller.buscarVeiculo(placa);
            placaOriginal = placa;

            // Preencher campos
            txtPlacaEdicao.setText(veiculoAtual.getPlaca());
            txtModelo.setText(veiculoAtual.getModelo());
            cmbTipo.setSelectedItem(veiculoAtual.getTipo());
            lblDataEntrada.setText(veiculoAtual.getDataEntrada());
            lblHoraEntrada.setText(veiculoAtual.getHoraEntrada());

            // Habilitar botões
            btnEditar.setEnabled(true);
            btnRemover.setEnabled(true);

            JOptionPane.showMessageDialog(this, "Veículo encontrado!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Veículo não encontrado!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            limparCampos();
        }
    }

    private void habilitarEdicao() {
        txtPlacaEdicao.setEnabled(true);
        txtModelo.setEnabled(true);
        cmbTipo.setEnabled(true);
        btnEditar.setText("Salvar");
        btnRemover.setEnabled(false);
    }

    private void salvarEdicao() {
        try {
            String novaPlaca = txtPlacaEdicao.getText().trim();
            String novoModelo = txtModelo.getText().trim();
            String novoTipo = (String) cmbTipo.getSelectedItem();

            if (novaPlaca.isEmpty() || novoModelo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Veiculo novoVeiculo;
            switch (novoTipo) {
                case "Carro":
                    novoVeiculo = new Carro(novaPlaca, novoModelo);
                    break;
                case "Moto":
                    novoVeiculo = new Moto(novaPlaca, novoModelo);
                    break;
                case "Caminhão":
                    novoVeiculo = new Caminhao(novaPlaca, novoModelo);
                    break;
                default:
                    throw new IllegalArgumentException("Tipo inválido");
            }

            controller.editarVeiculo(placaOriginal, novoVeiculo);
            JOptionPane.showMessageDialog(this, "Veículo editado com sucesso!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            // Desabilitar edição
            txtPlacaEdicao.setEnabled(false);
            txtModelo.setEnabled(false);
            cmbTipo.setEnabled(false);
            btnEditar.setText("Editar");
            btnRemover.setEnabled(true);

            // Atualizar dados
            veiculoAtual = novoVeiculo;
            placaOriginal = novaPlaca;

        } catch (SQLException ex) {
            if (ex.getMessage().contains("UNIQUE constraint failed")) {
                JOptionPane.showMessageDialog(this, "Já existe um veículo com esta placa!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao editar veículo: " +
                    ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerVeiculo() {
        int confirmacao = JOptionPane.showConfirmDialog(this,
            "Tem certeza que deseja remover o veículo " + veiculoAtual.getPlaca() + "?",
            "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            try {
                controller.removerVeiculo(veiculoAtual.getPlaca());
                JOptionPane.showMessageDialog(this, "Veículo removido com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover veículo: " +
                    ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limparCampos() {
        txtPlacaBusca.setText("");
        txtPlacaEdicao.setText("");
        txtModelo.setText("");
        cmbTipo.setSelectedIndex(0);
        lblDataEntrada.setText("-");
        lblHoraEntrada.setText("-");

        txtPlacaEdicao.setEnabled(false);
        txtModelo.setEnabled(false);
        cmbTipo.setEnabled(false);

        btnEditar.setText("Editar");
        btnEditar.setEnabled(false);
        btnRemover.setEnabled(false);

        veiculoAtual = null;
        placaOriginal = null;
    }
}
