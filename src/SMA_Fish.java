import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SMA_Fish {

    private Frame frame;

    public static ArrayList<Fish> agents = new ArrayList<Fish>();
    public ArrayList<Fish> agents_alea = new ArrayList<Fish>();

    public final int length_map = 150;
    public int time = 0;
    /**
     * The value of the thread.sleep
     */
    public int slow = 20;

    public String tuna_shark_pop = "F S\n";
    public String tuna_shark_overTime = "T F S \n";
    public String tuna_shark_age = "A F S\n";


    public int nb_shark = 0;
    public int nb_tuna = 0;


    public Object[][] data;
    public Env env;

    public SMA_Fish() {

        env = constructor(length_map * 5, length_map * 5, 3, 8, 2, length_map);
        data = new Object[length_map][length_map];
        Rect rect = new Rect();
        rect.Set_Rect(data);
        this.frame = new Frame(rect);
    }
/*
    public static void main(String[] args) {
        SMA sm = new SMA();
        while (sm.launch) {
            sm.play_it();
        }
        sm.write_file(sm.tuna_shark_pop, "graph_pop.log");
        sm.write_file(sm.tuna_shark_overTime, "graph_pop_time.log");
        sm.make_age();
        sm.write_file(sm.tuna_shark_age,"graph_pop_age.log");
        sm.dispose();
        System.out.println("Sortie...");
    }
    */

    public void play_it() {

        if (frame.get_play()) {
            dIt();

           // if (nb_tuna == 0 || nb_shark == 0) {frame.dispose(); }
        }
    }

    /**
     * Do the stuff ^^
     */
    public void dIt() {
        readable_env();
        randomize_agents();
        int i = 0;
        while (i < agents_alea.size()) {
            Fish a = agents_alea.get(i);
            a.doIt();
            i++;
        }
        time++;
        get_pop();
        tuna_shark_pop += nb_tuna + " " + nb_shark + "\n";
        tuna_shark_overTime += time + " " + nb_tuna + " " + nb_shark + "\n";

        try {
            Thread.sleep(slow);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        frame.update();

    }

    /**
     * Refresh tuna_shark_age variable
     */
    public void make_age() {
        int l = 101;
        int fa[] = new int[l];
        int sa[] = new int[l];

        for (Agent a : agents) {
            if (a.toString().equals("F")) {
                if (a.get_age() < l) {
                    fa[a.get_age()]++;
                }
            }
            if (a.toString().equals("T")) {
                if (a.get_age() < l) {
                    sa[a.get_age()]++;
                }
            }
        }

        for (int i = 0; i < l; i++) {
            tuna_shark_age += i + " " + fa[i] + " " + sa[i] + "\n";
        }
    }

    /**
     * Refresh the env
     */
    public void readable_env() {
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

    public void get_pop() {
        nb_tuna = 0;
        nb_shark = 0;
        for (int i = 0; i < agents_alea.size(); i++) {
            if (agents.get(i).toString().equals("S")) {
                nb_shark++;
            } else if (agents.get(i).toString().equals("T")) {
                nb_tuna++;
            }
        }
    }

    public Env constructor(int nb_tuna, int nb_shark, int tuna_breeding_time, int shark_breeding_time, int feeding_time, int lenght_map) {
        Env env = new Env(lenght_map, lenght_map);
        for (int i = 0; i < nb_tuna; i++) {
            boolean search = true;
            while (search) {
                int x = get_alea(0, lenght_map - 1);
                int y = get_alea(0, lenght_map - 1);
                if (env.map[x][y] == null) {
                    Tuna t = new Tuna(env, x, y, tuna_breeding_time);
                    agents.add(t);
                    search = false;
                }
            }
        }

        for (int i = 0; i < nb_shark; i++) {
            boolean search = true;
            while (search) {
                int x = get_alea(0, lenght_map - 1);
                int y = get_alea(0, lenght_map - 1);
                if (env.map[x][y] == null) {
                    Shark s = new Shark(env, x, y, shark_breeding_time, feeding_time);
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

    public int get_alea(int min, int max) {
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
