package trabalhodoo1;

public class Caminhao extends Veiculo {
    public Caminhao(String placa, String modelo) {
        super(placa, modelo);
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
