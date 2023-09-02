public class Estrellas2 {
    public static void main(String[] args) {
        String a;
        for (int i = 0; i < 4; i = i + 1) {
            a = "";
            for (int j = 0; j < 5; j = j + 1) {
                if(i%2==0){
                    a += " *";
                } else {
                    a += "* ";
                }
            }
            System.out.printf(a);
            System.out.println(); // Agregar una nueva línea después de cada bucle interno
        }
    }
}
