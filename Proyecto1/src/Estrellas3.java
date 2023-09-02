public class Estrellas3 {
    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            int count = i+1;
            String a = "";
            for(int j=0;  j < count; j++){
                a += "* ";
            }
            System.out.printf(a);
            System.out.println();
        }
    }
}
