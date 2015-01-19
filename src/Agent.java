import java.util.Random;

public class Agent {

    protected int posX;
    protected int posY;
    protected Env env;


    public Agent(Env e, int x, int y) {
        this.env = e;
        this.posX = x;
        this.posY = y;
        env.map[posX][posY] = this;
    }

    protected void doIt() {
    }

    protected int get_alea(int min, int max) {
        Random rand = new Random();
        return (rand.nextInt(max - min + 1) + min);
    }

    protected void set_pos(int x, int y) {
        env.map[posX][posY] = null;
        this.posX = x;
        this.posY = y;
        env.map[posX][posY] = this;
    }

    protected void zone_build(int x, int y) {
    }

    protected int[] coordinate(int x) {
        switch (x) {
            case 0:
                return new int[]{0, 1};
            case 1:
                return new int[]{1, 1};
            case 2:
                return new int[]{1, 0};
            case 3:
                return new int[]{1, -1};
            case 4:
                return new int[]{0, -1};
            case 5:
                return new int[]{-1, -1};
            case 6:
                return new int[]{-1, 0};
            case 7:
                return new int[]{-1, 1};
            default:
                return new int[]{0, 0};
        }
    }

    public String toString() {
        return "A";
    }

}
