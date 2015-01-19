public class Habitant extends Agent {

    protected int nb_copain = 0;
    protected int nb_ennemis = 0;
    protected int empty_case = 0;
    protected int tolerance;
    protected int try_it = 10;
    protected String color;


    public Habitant(Env e, int x, int y, String color, int tolerance) {
        super(e, x, y);
        this.env = e;
        this.posX = x;
        this.posY = y;
        this.color = color;
        this.tolerance = tolerance;
        env.map[posX][posY] = this;
    }

    @Override
    protected void doIt() {
        boolean b = true;
        zone_build(posX, posY);
        int i = 0;
        if (nb_copain < tolerance) {

            while (b) {
                int x = get_alea(0, env.map_lenght - 1);
                int y = get_alea(0, env.map_lenght - 1);
                if (env.map[x][y] == null) {
                    zone_build(x, y);
                    if (nb_copain >= tolerance || i < try_it) {
                        set_pos(x, y);
                        b = false;
                    }
                    i++;
                }
            }
        }
    }

    @Override
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

    public double get_hapiness(){
    return (8 - this.nb_ennemis)*12.5;
    }

    @Override
    public String toString() {
        return this.color;
    }
}
