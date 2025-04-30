package trabalhodoo1;

// Classe abstrata representando um veículo genérico
public abstract class Veiculo {
    // Atributos protegidos (encapsulamento) para serem acessados pelas subclasses
    protected String placa;
    protected String modelo;

    // Construtor
    public Veiculo(String placa, String modelo) {
        this.placa = placa;
        this.modelo = modelo;
    }

    // Getter para placa (encapsulamento)
    public String getPlaca() {
        return placa;
    }

    // Método abstrato que será implementado pelas subclasses (abstração + polimorfismo)
    public abstract void exibirInfo();

    // Método abstrato para cálculo de pagamento (abstração + polimorfismo)
    public abstract double calcularPagamento(int horas);
}
