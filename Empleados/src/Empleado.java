public abstract class Empleado {
    private double salarioFijo;

    public Empleado(double salarioFijo) {
        this.salarioFijo = salarioFijo;
    }
    public double getSalarioFijo(){
        return this.salarioFijo;
    }
    public abstract double calcularSueldo();

}
