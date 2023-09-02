// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Estrellas1 {
    public static void main(String[] args) {
        String a;
        for (int i = 0; i < 4; i = i + 1) {
            a = "";
            for (int j = 0; j < 5; j = j + 1) {
                a += "* ";
            }
            System.out.printf(a);
            System.out.println(); // Agregar una nueva línea después de cada bucle interno
        }
    }
}
