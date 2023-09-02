public class Tamagotchi {
    private int energia;
    private int humor;
    private boolean durmiendo;
    private boolean muerta;
    private int ingestas;
    private int actividades;

    @Override
    public String toString() {
        return "Tamagotchi{" +
                "energia:" + energia +
                ", humor:" + humor +
                ", durmiendo=" + durmiendo +
                ", muerta=" + muerta + "\n";
    }

    public Tamagotchi(int energia, int humor) {
        this.energia = energia;
        this.humor = humor;
        this.durmiendo = false;
        this.muerta = false;
        this.ingestas = 0;
        this.actividades = 0;
    }
    public int getEnergia() {
        return energia;
    }
    public int getHumor() {
        return humor;
    }

    public boolean comer(){
        if(activo()) {
            ruidoAccion("ÑAM ÑAM ÑAM");
            this.actividades = 0;
            this.ingestas++;

            incremetarEnergia(10);
            incrementarHumor(1);

            chequearEstado();
            return true;
        } else return false;
    }
    public boolean beber(){
        if(activo()) {
            ruidoAccion("GLU GLU GLU");
            this.actividades = 0;
            this.ingestas++;

            incremetarEnergia(5);
            incrementarHumor(1);

            chequearEstado();
            return true;
        }else return false;
    }

    public boolean correr(){
        if(activo()) {
            ruidoAccion("TAP TAP TAP");
            this.ingestas = 0;
            this.actividades++;
            incremetarEnergia(-35);
            incrementarHumor(-2);
            chequearEstado();
            return true;
        } else return false;
    }
    public boolean saltar(){
        if(activo()) {
            ruidoAccion("FIUUUUUUM");
            this.ingestas = 0;
            this.actividades++;
            incremetarEnergia(-15);
            incrementarHumor(-2);
            chequearEstado();
            return true;
        } else return false;
    }

    public boolean dormir(){
        if(activo()) {
            ruidoAccion("ZZZ ZZZ ZZZ");
            this.ingestas = 0;
            this.actividades = 0;
            incremetarEnergia(25);
            incrementarHumor(2);
            this.durmiendo = true;
            return true;
        } else return false;
    }

    public boolean despertar(){
        if(durmiendo && !muerta) {
            ruidoAccion("HOLA");
            incrementarHumor(-1);
            this.durmiendo = false;
            return true;
        } else return false;
    }

    private void incremetarEnergia(int incremento){
        this.energia += incremento;
        if (this.energia > 100) this.energia = 100;
        else if (this.energia <= 0) morir();
    }
    private void morir(){
        this.muerta = true;
        ruidoAccion("------------------------ HE MUERTO ... RIP ------------------------");
    }
    private void incrementarHumor(int incremento){
        this.humor += incremento;
        if (this.humor > 5) this.humor = 5;
        else if (this.humor <= 0){
            this.humor = 0;
            dormir();
        }
    }
    private boolean activo(){
        return !this.durmiendo && !this.muerta;
    }

    private void chequearEstado(){
        if(this.ingestas>2) {
            incrementarHumor(-1);
            if (this.ingestas == 5) {
                morir();
            }
        }
        else
        if(actividades > 2){
            dormir();
        }
    }

    private void ruidoAccion(String ruido){
        System.out.print(ruido+"\n");
    }

}
