public class Env {

    public final int map_lenght;
    public Agent[][] map;
    public int[][] dijstra;

    public Env(int a, int b) {
        this.dijstra = new int[a][b];
        this.map_lenght = a;
        this.map = new Agent[a][b];
    }


}
