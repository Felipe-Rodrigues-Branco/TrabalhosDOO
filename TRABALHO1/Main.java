package trabalhodoo1;

// Classe principal para simular o uso do sistema de estacionamento
public class Main {
    public static void main(String[] args) {
        Estacionamento est = new Estacionamento();

        // Polimorfismo: usamos o tipo genérico Veiculo para armazenar diferentes subclasses
        Veiculo v1 = new Carro("ABC1234", "Civic");
        Veiculo v2 = new Moto("XYZ9876", "CG Titan");
        Veiculo v3 = new Caminhao("GHI4567", "Volvo FH");

        est.adicionarVeiculo(v1);
        est.adicionarVeiculo(v2);
        est.adicionarVeiculo(v3);

        est.listarVeiculos();

        est.calcularPagamento("XYZ9876", 4);  // Moto
        est.removerVeiculo("TESTANDO1");      // Placa inexistente
        est.removerVeiculo("TESTANDO2");      // Placa inexistente
    }
}
