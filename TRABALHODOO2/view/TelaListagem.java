
package view;
import controller.EstacionamentoController;
import model.Veiculo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class TelaListagem extends JFrame {
    private EstacionamentoController controller;
    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaListagem() {
        controller = new EstacionamentoController();
        initComponents();
        carregarVeiculos();
    }

    private void initComponents() {
        setTitle("Listar Veículos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBackground(new Color(230, 230, 250));

        // Título
        JLabel lblTitulo = new JLabel("Veículos Cadastrados", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(25, 25, 112));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Tabela
        String[] colunas = {"Placa", "Modelo", "Tipo", "Data Entrada", "Hora Entrada"};
        modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela = new JTable(modelo);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getTableHeader().setBackground(new Color(100, 149, 237));
        tabela.getTableHeader().setForeground(Color.WHITE);
        tabela.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.setBackground(new Color(230, 230, 250));

        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnRemover = new JButton("Remover Selecionado");
        JButton btnCalcularPagamento = new JButton("Calcular Pagamento");
        JButton btnVoltar = new JButton("Voltar");

        estilizarBotao(btnAtualizar, new Color(100, 149, 237));
        estilizarBotao(btnRemover, new Color(220, 20, 60));
        estilizarBotao(btnCalcularPagamento, new Color(34, 139, 34));
        estilizarBotao(btnVoltar, new Color(128, 128, 128));

        // Eventos dos botões
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarVeiculos();
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerVeiculo();
            }
        });

        btnCalcularPagamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularPagamento();
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaInicial().setVisible(true);
                dispose();
            }
        });

        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnCalcularPagamento);
        painelBotoes.add(btnVoltar);

        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
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

    private void carregarVeiculos() {
        try {
            modelo.setRowCount(0);
            List<Veiculo> veiculos = controller.listarVeiculos();
            for (Veiculo v : veiculos) {
                Object[] linha = {
                    v.getPlaca(),
                    v.getModelo(),
                    v.getTipo(),
                    v.getDataEntrada(),
                    v.getHoraEntrada()
                };
                modelo.addRow(linha);
            }
            if (veiculos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum veículo cadastrado.",
                    "Informação", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar veículos: " +
                ex.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerVeiculo() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um veículo para remover!",
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String placa = (String) modelo.getValueAt(linhaSelecionada, 0);
        int confirmacao = JOptionPane.showConfirmDialog(this,
            "Tem certeza que deseja remover o veículo " + placa + "?",
            "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            try {
                controller.removerVeiculo(placa);
                JOptionPane.showMessageDialog(this, "Veículo removido com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                carregarVeiculos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover veículo: " +
                    ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void calcularPagamento() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um veículo para calcular o pagamento!",
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String placa = (String) modelo.getValueAt(linhaSelecionada, 0);
        String tipo = (String) modelo.getValueAt(linhaSelecionada, 2);

        String horasStr = JOptionPane.showInputDialog(this,
            "Digite a quantidade de horas que o veículo ficou estacionado:",
            "Calcular Pagamento", JOptionPane.QUESTION_MESSAGE);

        if (horasStr != null && !horasStr.trim().isEmpty()) {
            try {
                int horas = Integer.parseInt(horasStr.trim());
                if (horas <= 0) {
                    JOptionPane.showMessageDialog(this, "O número de horas deve ser maior que zero!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double valor = 0;
                switch (tipo) {
                    case "Carro":
                        valor = horas * 5.0;
                        break;
                    case "Moto":
                        valor = horas * 3.0;
                        break;
                    case "Caminhão":
                        valor = horas * 10.0;
                        break;
                }

                JOptionPane.showMessageDialog(this,
                    String.format("Pagamento para %s (%s):\n%d horas × R$ %.2f = R$ %.2f",
                        placa, tipo, horas, valor/horas, valor),
                    "Pagamento Calculado", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um número válido de horas!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}