
package model;

public class Caminhao extends Veiculo {
    public Caminhao(String placa, String modelo) {
        super(placa, modelo);
    }

    public Caminhao(String placa, String modelo, String dataEntrada, String horaEntrada) {
        super(placa, modelo, dataEntrada, horaEntrada);
    }

    @Override
    public String getTipo() {
        return "Caminhão";
    }

    @Override
    public void exibirInfo() {
        System.out.println("Caminhão - Placa: " + placa + ", Modelo: " + modelo);
    }

    @Override
    public double calcularPagamento(int horas) {
        return horas * 10.0;
    }
}
