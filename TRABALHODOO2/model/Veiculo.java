// Veiculo.java
package model;

public abstract class Veiculo {
    protected String placa;
    protected String modelo;
    protected String dataEntrada;
    protected String horaEntrada;

    public Veiculo(String placa, String modelo) {
        this.placa = placa;
        this.modelo = modelo;
    }

    public Veiculo(String placa, String modelo, String dataEntrada, String horaEntrada) {
        this.placa = placa;
        this.modelo = modelo;
        this.dataEntrada = dataEntrada;
        this.horaEntrada = horaEntrada;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public abstract String getTipo();
    public abstract void exibirInfo();
    public abstract double calcularPagamento(int horas);
}