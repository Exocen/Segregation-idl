public class Prey extends Agent {

    protected int empty_case = 0;

    public Prey(Env e, int x, int y) {
        super(e, x, y);
    }

    /**
     * La proie se déplace aléatoirement
     */
    protected void doIt() {
        super.doIt();
        if (empty_case != 0) {
            int alea_pos = get_alea(1, empty_case);

            int ite = 1;
            int x = 0;
            int y = 0;
            for (int i = 0; i < zone.length; i++) {
                if (zone[i] != null && zone[i].equals("E")) {
                    if (ite == alea_pos) {
                        int[] coord = coordinate(i);
                        x = coord[0];
                        y = coord[1];
                        i = zone.length + 1;//sortie de boucle

                    } else {
                        ite++;
                    }
                }
            }
            if (x == 0 && y == 0) {
                System.out.println("x=" + posX + " y=" + posY + " ERREUR");
            }
            int x2 = posX + x;
            int y2 = posY + y;
            set_pos(x2, y2);

            make_dijstra();

        }
    }


    /**
     * Le calcul de dijstra se fait en cercle autour de la proie
     */
    public void make_dijstra() {
        env.dijstra[posX][posY] = 1;
        int x = posX;
        int y = posY;
        int a = 0;

        int fy, fx;
        for (int i = 1; a < (env.map_lenght * env.map_lenght)-(env.nb_obsta); i++) {
            y++;
            x++;

            if (x < env.map_lenght && x >= 0 && y < env.map_lenght && y >= 0 && env.dijstra[x][y]!=-1 ) {
                a++;
                env.dijstra[x][y] = getmin(x, y); }

            fy = posY - i;
            while (y != fy) {
                if (x < env.map_lenght && x >= 0 && y < env.map_lenght && y >= 0 && env.dijstra[x][y]!=-1 ) {
                    a++;
                    env.dijstra[x][y] = getmin(x, y);
                }
                y--;

            }
            fx = posX - i;
            while (x != fx) {
                if (x < env.map_lenght && x >= 0 && y < env.map_lenght && y >= 0 && env.dijstra[x][y]!=-1 ) {
                    a++;
                    env.dijstra[x][y] = getmin(x, y);
                }
                x--;

            }
            fy = posY + i;
            while (y != fy) {

                if (x < env.map_lenght && x >= 0 && y < env.map_lenght && y >= 0 && env.dijstra[x][y]!=-1 ) {
                    a++;
                    env.dijstra[x][y] = getmin(x, y);
                }
                y++;

            }
            fx = posX + i;
            while (x != fx) {

                if (x < env.map_lenght && x >= 0 && y < env.map_lenght && y >= 0 && env.dijstra[x][y]!=-1 ) {
                    a++;
                    env.dijstra[x][y] = getmin(x, y);
                }
                x++;
            }

        }
    }

    /**
     *
     * @param x position
     * @param y position
     * @return la valeur minimale
     */
    protected int getmin(int x, int y) {
        int min = 0;
        boolean change = false;
        for (int i = 0; i <= 7; i++) {
            int[] emp = coordinate(i);
            int x2 = x + emp[0];
            int y2 = y + emp[1];
            if (x2 < env.map_lenght
                    && x2 >= 0
                    && y2 < env.map_lenght
                    && y2 >= 0) {
                if (env.dijstra[x2][y2] > 0) {
                    if (min > env.dijstra[x2][y2] || !change) {
                        change = true;
                        min = env.dijstra[x2][y2];
                    }
                }
            }
        }
        if (!change) {
            min = 0;
        }
        return ++min;
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
                    if (env.dijstra[x2][y2]==-1){
                        zone[i] = "W";}
                    else {
                        zone[i] = "E";
                        empty_case++;
                    }
                } else {
                    zone[i] = env.map[x2][y2].toString();

                }
            }
        }
    }

    public String toString() {
        return "P";
    }

}
