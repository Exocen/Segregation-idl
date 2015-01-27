import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SMA_Dij {

    private Frame frame;

    public static ArrayList<Agent> agents = new ArrayList<Agent>();
    public ArrayList<Agent> agents_alea = new ArrayList<Agent>();

    public final int length_map = 50;
    public int time = 0;
    /**
     * The value of the thread.sleep
     */
    public final int slow = 50;
    public Object[][] data;
    public Env env;
    public int nb_Prey = 1;
    public int nb_Hunter = 2;
    public int tolerance = 4;
    public String s_log = "";

    public SMA_Dij() {

        env = constructor(nb_Prey, nb_Hunter, tolerance, length_map);
        data = new Object[length_map][length_map];
        Rect rect = new Rect();
        rect.Set_Rect(data);
        this.frame = new Frame(rect);
        dIt();

    }


    public void play_it() {
        if (frame.get_play()) {
            dIt();
        }
    }

    /**
     * Do the stuff ^^
     */
    private void dIt() {
        readable_env();
        randomize_agents();
        int i = 0;
        while (i < agents_alea.size()) {
            Agent a = agents_alea.get(i);
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
                    data[i2][j2] = " ";
                } else {
                    data[i2][j2] = "" + j.toString();
                }
                j2++;
            }
            i2++;
            j2 = 0;
        }
    }


    private Env constructor(int nb_green, int nb_red, int tolerance, int lenght_map) {
        Env env = new Env(lenght_map, lenght_map);
        for (int i = 0; i < nb_green; i++) {
            boolean search = true;
            while (search) {
                int x = get_alea(0, lenght_map - 1);
                int y = get_alea(0, lenght_map - 1);
                if (env.map[x][y] == null) {
                    Prey f = new Prey(env, x, y);
                    agents.add(f);
                    search = false;
                }
            }
        }

        for (int i = 0; i < nb_red; i++) {
            boolean search = true;
            while (search) {
                int x = get_alea(0, lenght_map - 1);
                int y = get_alea(0, lenght_map - 1);
                if (env.map[x][y] == null) {
                    Hunter s = new Hunter(env, x, y);
                    agents.add(s);
                    search = false;
                }
            }
        }

        return env;
    }


    private void randomize_agents() {
        this.agents_alea = agents;
        Collections.shuffle(agents_alea);
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
