public class Estrellas4 {
    public static void main(String[] args){
        int count;
        int h = 1;
        for (int i = 0; i < 9; i++) {
            if(i < 5){
                count = i+1;
            } else {
                count = i - h;
                h = h+2;
            }
            String a = "";
            for(int j=0;  j < count; j++){
                a += "* ";
            }
            System.out.printf(a);
            System.out.println();
        }
    }
}
