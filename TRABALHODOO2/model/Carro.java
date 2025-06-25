// Carro.java
package model;

public class Carro extends Veiculo {
    public Carro(String placa, String modelo) {
        super(placa, modelo);
    }

    public Carro(String placa, String modelo, String dataEntrada, String horaEntrada) {
        super(placa, modelo, dataEntrada, horaEntrada);
    }

    @Override
    public String getTipo() {
        return "Carro";
    }

    @Override
    public void exibirInfo() {
        System.out.println("Carro - Placa: " + placa + ", Modelo: " + modelo);
    }

    @Override
    public double calcularPagamento(int horas) {
        return horas * 5.0;
    }
}