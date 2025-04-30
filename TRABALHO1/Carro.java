package trabalhodoo1;

// Subclasse de Veiculo, herda seus atributos e métodos
public class Carro extends Veiculo {
    // Construtor
    public Carro(String placa, String modelo) {
        super(placa, modelo); // chamada ao construtor da superclasse
    }

    // Implementação específica do método abstrato (polimorfismo)
    @Override
    public void exibirInfo() {
        System.out.println("Carro - Placa: " + placa + ", Modelo: " + modelo);
    }

    // Cálculo de pagamento específico para carro
    @Override
    public double calcularPagamento(int horas) {
        return horas * 5.0;
    }
}
