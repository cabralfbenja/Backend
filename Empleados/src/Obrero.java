public class Obrero extends Empleado {

    private int diasTrabajados;

    public Obrero(double salario) {
        // Constructor de Obrero
        super(salario);
        this.diasTrabajados = 0;

    }
    public void setDiasTrabajados(int dias){
        this.diasTrabajados = dias;
    }
    @Override
    public double calcularSueldo() {
        return this.getSalarioFijo() + this.diasTrabajados * 50;
    }
}
