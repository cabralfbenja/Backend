import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        Lista l = new ListaEnArray();

        for (int i = 0; i < 100; i++) {
            Object nro = random.nextInt(100);
            l.add(nro);
        }


        long acum = 0;

        for (int i = 0; i < l.size(); i++) {
            acum += (int)l.get(i);
        }
        double promedio = (double) acum/l.size();
        System.out.println("El promedio es: " + acum);
        System.out.println(l);

    }
}