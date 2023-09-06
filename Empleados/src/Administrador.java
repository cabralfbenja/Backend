public class Administrador extends Empleado {
    // Atributos específicos de Admin
    private boolean presentismo;
    public Administrador(double salario) {
        super(salario);
        this.presentismo = false;
    }

    public void cumplioPresentismo(){
        this.presentismo = true;
    }
    public void noCumplioPresentismo(){
        this.presentismo = false;
    }
    @Override
    public double calcularSueldo() {
        if(this.presentismo){
            return Math.round(this.getSalarioFijo() * 1.15);
        }
        return Math.round(this.getSalarioFijo());
    }
}
