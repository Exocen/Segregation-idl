public class Env {

    public final int map_lenght;
    public Agent[][] map;
    public int[][] dijstra;
    public int nb_obsta;

    public Env(int a, int b) {
        this.map_lenght = a;
        this.map = new Agent[a][b];
    }
    public Env(int a, int b, int c) {
        this.nb_obsta = c;
        this.dijstra = new int[a][b];
        this.map_lenght = a;
        this.map = new Agent[a][b];
    }

}
