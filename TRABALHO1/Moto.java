package trabalhodoo1;

public class Moto extends Veiculo {
    public Moto(String placa, String modelo) {
        super(placa, modelo);
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
