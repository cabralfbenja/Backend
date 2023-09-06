public class Main {
    public static void main(String[] args) {
        Vendedor miVendedor = new Vendedor(3600);
        Obrero miObrero = new Obrero(1478.50);
        Administrador miAdmin = new Administrador(7000);

        miObrero.setDiasTrabajados(20);
        miAdmin.cumplioPresentismo();
        miVendedor.setMontoCantidadVendida(1200);


        System.out.println("Empleados: ");
        System.out.println("miVendedor: " + miVendedor.calcularSueldo());
        System.out.println("miObrero: " + miObrero.calcularSueldo());
        System.out.println("miAdmin: "+ miAdmin.calcularSueldo());

        miAdmin.noCumplioPresentismo();
        System.out.println("miAdmin cuando no viene a trabajar: "+ miAdmin.calcularSueldo());


    }
}