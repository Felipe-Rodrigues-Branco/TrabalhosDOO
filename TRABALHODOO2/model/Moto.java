// Moto.java
package model;

public class Moto extends Veiculo {
    public Moto(String placa, String modelo) {
        super(placa, modelo);
    }

    public Moto(String placa, String modelo, String dataEntrada, String horaEntrada) {
        super(placa, modelo, dataEntrada, horaEntrada);
    }

    @Override
    public String getTipo() {
        return "Moto";
    }

    @Override
    public void exibirInfo() {
        System.out.println("Moto - Placa: " + placa + ", Modelo: " + modelo);
    }

    @Override
    public double calcularPagamento(int horas) {
        return horas * 3.0;
    }
}