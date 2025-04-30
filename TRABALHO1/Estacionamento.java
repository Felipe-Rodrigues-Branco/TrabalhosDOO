package trabalhodoo1;

import java.util.ArrayList;

// Classe que gerencia os veículos no estacionamento
public class Estacionamento {
    private ArrayList<Veiculo> veiculos; // composição: um estacionamento tem uma lista de veículos

    public Estacionamento() {
        veiculos = new ArrayList<>();
    }

    // Método para adicionar um veículo (polimorfismo em ação: pode ser qualquer tipo de Veiculo)
    public void adicionarVeiculo(Veiculo v) {
        veiculos.add(v);
        System.out.println("Veículo adicionado com sucesso!");
    }

    // Remove veículo com base na placa
    public void removerVeiculo(String placa) {
        boolean removido = false;
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getPlaca().equals(placa)) {
                veiculos.remove(i);
                System.out.println("Veículo removido com sucesso!");
                removido = true;
                break;
            }
        }

        if (!removido) {
            System.out.println("Erro: Veículo com placa " + placa + " não encontrado.");
        }
    }

    // Lista todos os veículos no estacionamento
    public void listarVeiculos() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo no estacionamento.");
        } else {
            for (Veiculo v : veiculos) {
                v.exibirInfo();
            }
        }
    }

    // Calcula e exibe o pagamento para o veículo com base na placa e nas horas
    public void calcularPagamento(String placa, int horas) {
        boolean encontrado = false;
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(placa)) {
                double valor = v.calcularPagamento(horas);
                System.out.println("Pagamento para " + placa + ": R$ " + valor);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Erro: Veículo com placa " + placa + " não encontrado.");
        }
    }
}
