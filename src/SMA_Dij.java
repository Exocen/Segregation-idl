import java.util.ArrayList;
import java.util.Random;

public class SMA_Dij {

    private Frame frame;

    public static ArrayList<Agent> agents = new ArrayList<Agent>();

    public final int length_map = 50;
    public int time = 0;
    /**
     * The value of the thread.sleep
     */
    public final int slow = 50;
    public Object[][] data;
    public Env env;
    public final int nb_Prey = 3;
    public int nb_Hunter = 10;
    public int nb_wall = 50;

    public SMA_Dij() {

        env = constructor(nb_Prey, nb_Hunter, nb_wall, length_map);
        data = new Object[length_map][length_map];
        Rect rect = new Rect();
        rect.Set_Rect(data);
        this.frame = new Frame(rect);
        dIt();

    }

    /**
     * Si le chasseur a tué la dernière proie elle(s) est(sont) recrée(s)
     */
    public void play_it() {
        if (frame.get_play()) {

            dIt();

            if (get_pop() == 0 ) {
                frame.set_play(false);
                for (int i = 0; i < nb_Prey; i++) {
                    boolean search = true;
                    while (search) {
                        int x = get_alea(0, this.length_map - 1);
                        int y = get_alea(0, this.length_map - 1);
                        if (env.map[x][y] == null && env.dijstra[x][y]!=-1) {
                            Prey prey = new Prey(env, x, y);
                            agents.add(0, prey);
                            search = false;
                        }
                    }
                }
            }
        }
    }

    /**
     * Do the stuff ^^
     */
    private void dIt() {

        readable_env();
        int i = 0;
        while (i < agents.size()) {
            Agent a = agents.get(i);
            a.doIt();
            i++;
        }
        time++;

        try {
            Thread.sleep(slow);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        frame.update();

    }


    /**
     * Refresh the env
     */
    private void readable_env() {
        int i2 = 0;
        int j2 = 0;
        for (Agent i[] : env.map) {
            for (Agent j : i) {
                if (j == null) {
                    if (env.dijstra[i2][j2]==-1){
                        data[i2][j2] = "W";}
                    else{  data[i2][j2] = " ";}
                }
                else {
                    data[i2][j2] = "" + j.toString();
                }
                j2++;
            }
            i2++;
            j2 = 0;
        }
    }

    /**
     * @return le nombre de proies
     */
    public int get_pop() {
        int nb_Prey = 0;

        for (Agent agent : agents) {
            if (agent.toString().equals("P")) {
                nb_Prey++;
            }
        }
        return nb_Prey;
    }

    private Env constructor(int preys, int hunters, int walls, int lenght_map) {
        Env env = new Env(lenght_map, lenght_map, preys + hunters + walls);
        for (int i = 0; i < walls; i++) {
            boolean search = true;
            while (search) {
                int x = get_alea(0, lenght_map - 1);
                int y = get_alea(0, lenght_map - 1);
                if (env.map[x][y] == null && env.dijstra[x][y]!=-1) {
                    env.dijstra[x][y]=-1;
                    search = false;
                }
            }
        }
        for (int i = 0; i < preys; i++) {
            boolean search = true;
            while (search) {
                int x = get_alea(0, lenght_map - 1);
                int y = get_alea(0, lenght_map - 1);
                if (env.map[x][y] == null && env.dijstra[x][y]!=-1) {
                    Prey f = new Prey(env, x, y);
                    agents.add(f);
                    search = false;
                }
            }
        }

        for (int i = 0; i < hunters; i++) {
            boolean search = true;
            while (search) {
                int x = get_alea(0, lenght_map - 1);
                int y = get_alea(0, lenght_map - 1);
                if (env.map[x][y] == null && env.dijstra[x][y]!=-1) {
                    Hunter s = new Hunter(env, x, y);
                    agents.add(s);
                    search = false;
                }
            }
        }

        return env;
    }

    private int get_alea(int min, int max) {
        Random rand = new Random();
        return (rand.nextInt(max - min + 1) + min);
    }

    public void stop_it() {
        frame.dispose();
    }

    public boolean get_launch() {
        return frame.get_launch();
    }

}
