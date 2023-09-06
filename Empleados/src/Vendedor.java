public class Vendedor extends Empleado {

    private double montoCantidadVendida;

    public Vendedor(double salario) {
        super(salario);
        this.montoCantidadVendida = 0;
    }

    public void setMontoCantidadVendida(double monto){
        this.montoCantidadVendida = monto;
    }
    @Override
    public double calcularSueldo() {
        return this.getSalarioFijo() + 0.01 * this.montoCantidadVendida;
    }
}


