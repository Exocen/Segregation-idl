public class Hunter extends Agent {

    protected int empty_case = 0;


    public Hunter(Env e, int x, int y) {
        super(e, x, y);
        env.dijstra[x][y]=-1;
    }

    protected void doIt() {
        super.doIt();
        int min = 999999;
        empty_case = 0;
        int x = 0;
        int y = 0;
        int oldX=posX;
        int oldY=posY;
        for (int i = 0; i <= 7; i++) {
            int[] emp = coordinate(i);
            int x2 = posX + emp[0];
            int y2 = posY + emp[1];
            if (x2 < env.map_lenght
                    && x2 >= 0
                    && y2 < env.map_lenght
                    && y2 >= 0
                    && env.dijstra[x2][y2]!=-1) {
                if (env.dijstra[x2][y2] > 0) {
                    if (min > env.dijstra[x2][y2]) {
                        min = env.dijstra[x2][y2];
                        x=x2;
                        y=y2;

                    }
                }
            }

    }
        if(env.map[x][y]!=null && env.map[x][y].toString().equals("P")){
            SMA_Dij.agents.remove(env.map[x][y]);
        }
        set_pos(x,y);
        env.dijstra[oldX][oldY]=0;
        env.dijstra[x][y]=-1;

    }



    protected void zone_build() {
        zone = new String[8];
        empty_case = 0;
        for (int i = 0; i <= 7; i++) {
            int[] emp = coordinate(i);
            int x2 = posX + emp[0];
            int y2 = posY + emp[1];
            if (x2 < env.map_lenght
                    && x2 >= 0
                    && y2 < env.map_lenght
                    && y2 >= 0) {
                if (env.map[x2][y2] == null) {
                    zone[i] = "E";
                    empty_case++;
                } else {
                    zone[i] = env.map[x2][y2].toString();
                }
            }
        }
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
