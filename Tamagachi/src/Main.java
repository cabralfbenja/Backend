// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // Press Alt+Intro with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.print("Hola... Bienvenido a Tamagotchi!\n");
        Tamagotchi miMascota = new Tamagotchi(100, 5);
        System.out.printf(miMascota.toString());
        boolean flag = true;
        while (flag){
            Scanner scanner = new Scanner(System.in);
            menu();
            System.out.print("Ingrese la opcion: ");
            int opc = scanner.nextInt();
            switch (opc) {
                case 1 -> {
                    if (miMascota.comer()) {

                        System.out.printf(miMascota.toString());
                    } else {
                        System.out.print("No puede comer...");
                    }
                    ;
                }
                case 2 -> {
                    if (miMascota.beber()) {
                        System.out.printf(miMascota.toString());
                    } else {
                        System.out.print("No puede beber...");
                    }
                    ;
                }
                case 3 -> {
                    if (miMascota.correr()) {
                        System.out.printf(miMascota.toString());
                    } else {
                        System.out.print("No puede correr...");
                    }
                    ;
                }
                case 4 -> {
                    if (miMascota.saltar()) {
                        System.out.printf(miMascota.toString());
                    } else {
                        System.out.print("No puede saltar...");
                    }
                    ;
                }
                case 5 -> {
                    if (miMascota.dormir()) {
                        System.out.printf(miMascota.toString());
                    } else {
                        System.out.print("No puede dormir...");
                    }
                    ;
                }
                case 6 -> {
                    if (miMascota.despertar()) {
                        System.out.printf(miMascota.toString());
                    } else {
                        System.out.print("No se puede despertar... ha muerto...");
                    }
                }
                case 7 -> {
                    System.out.print("BYE BYE");
                    flag = false;
                }
                default -> {
                    System.out.print("Numero no valido");
                }
            }

            System.out.println();

        }

    }

    public static void menu(){
        System.out.printf("Elija qué hacer con su Tamagotchi:\n" +
                "1) Comer\n" +
                "2) Beber\n" +
                "3) Correr\n" +
                "4) Saltar\n" +
                "5) Dormir\n" +
                "6) Despertar\n" +
                "7) Salir\n"
        );
    }
}
