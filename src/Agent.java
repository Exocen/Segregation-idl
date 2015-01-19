import java.util.Random;

public class Agent {

    protected int posX;
    protected int posY;
    protected Env env;
    protected int nb_copain = 0;
    protected int nb_ennemis = 0;
    protected int empty_case = 0;
    protected int tolerance;
    protected String color;


    public Agent(Env e, int x, int y, String color, int tolerance) {
        this.env = e;
        this.posX = x;
        this.posY = y;
        this.color = color;
        this.tolerance = tolerance;
        env.map[posX][posY] = this;
    }

    protected void doIt() {
        boolean b = true;
        zone_build(posX, posY);
        if (nb_copain < tolerance) {

            while (b) {
                int x = get_alea(0, env.map_lenght - 1);
                int y = get_alea(0, env.map_lenght - 1);
                if (env.map[x][y] == null) {
                    zone_build(x, y);
                    if (nb_copain >= tolerance) {
                        set_pos(x, y);
                        b = false;
                    }
                }
            }
        }
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
        nb_copain = 0;
        nb_ennemis = 0;
        empty_case = 0;
        for (int i = 0; i <= 7; i++) {
            int[] emp = coordinate(i);
            int x2 = x + emp[0];
            int y2 = y + emp[1];
            if (x2 < env.map_lenght
                    && x2 >= 0
                    && y2 < env.map_lenght
                    && y2 >= 0) {
                if (env.map[x2][y2] == null) {
                    empty_case++;
                } else {
                    if (env.map[x2][y2].toString().equals(color)) {
                        nb_copain++;
                    } else {
                        nb_ennemis++;
                    }
                }
            }
        }
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

    public String get_color() {
        return this.color;
    }

    public String toString() {
        return this.color;
    }

}
