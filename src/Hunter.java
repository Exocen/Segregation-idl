public class Hunter extends Agent {

    protected int empty_case = 0;


    public Hunter(Env e, int x, int y) {
        super(e, x, y);
    }

    protected void doIt() {
        super.doIt();
        int min = 999999;
        empty_case = 0;
        int x = 0;
        int y = 0;
        for (int i = 0; i <= 7; i++) {
            int[] emp = coordinate(i);
            int x2 = posX + emp[0];
            int y2 = posY + emp[1];
            if (x2 < env.map_lenght
                    && x2 >= 0
                    && y2 < env.map_lenght
                    && y2 >= 0) {
                if (env.dijstra[x2][y2] > 0) {
                    if (min > env.dijstra[x2][y2]) {
                        min = env.dijstra[x2][y2];
                        x=x2;
                        y=y2;
                    }
                }
            }

    }
        set_pos(x,y);
    }


    protected int get_min(int x, int y) {
        int min = 999999;
        empty_case = 0;
        for (int i = 0; i <= 7; i++) {
            int[] emp = coordinate(i);
            int x2 = x + emp[0];
            int y2 = y + emp[1];
            if (x2 < env.map_lenght
                    && x2 >= 0
                    && y2 < env.map_lenght
                    && y2 >= 0) {
                if (env.dijstra[x2][y2] > 0) {
                    if (min > env.dijstra[x2][y2]) {
                        min = env.dijstra[x2][y2];
                    }
                }
            }
        }
        if (min == 999999) {
            min = 0;
        }
        return ++min;
    }


    public String toString() {
        return "H";
    }

}
