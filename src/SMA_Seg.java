import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SMA_Seg {

    private Frame frame;

    public static ArrayList<Habitant> agents = new ArrayList<Habitant>();
    public ArrayList<Habitant> agents_alea = new ArrayList<Habitant>();

    public final int length_map = 150;
    public int time = 0;
    /**
     * The value of the thread.sleep
     */
    public final int slow = 50;
    public Object[][] data;
    public Env env;
    public int nb_habitants;
    public double[] satisfaction_hab;
    public double moy_satisfaction = 0;
    public int tolerance = 4;
    public String s_log = "";

    public SMA_Seg() {

        this.nb_habitants = length_map * ((length_map / 2) - 10);
        this.satisfaction_hab = new double[nb_habitants * 2];
        env = constructor(nb_habitants, nb_habitants, tolerance, length_map);
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
            Habitant a = agents_alea.get(i);
            a.doIt();
            satisfaction_hab[i] = a.get_satisfaction();
            i++;
        }
        time++;
        get_moy_hapiness();
        double percentage_beehavior = tolerance * 12.5;
        s_log += time + " " + moy_satisfaction + " " + percentage_beehavior + "\n";

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

    private void get_moy_hapiness() {
        int total = 0;
        for (double i : satisfaction_hab) {
            total += i;
        }
        moy_satisfaction = (total / (nb_habitants * 2));
    }

    /*
    public void render_console(Env e) {
        for (Agent i[] : e.map) {
            for (Agent j : i) {
                if (j == null) {
                    System.out.print(" |");
                } else {
                    System.out.print(j + "|");
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------------------------------------");
    }
    */

    private Env constructor(int nb_green, int nb_red, int tolerance, int lenght_map) {
        Env env = new Env(lenght_map, lenght_map);
        for (int i = 0; i < nb_green; i++) {
            boolean search = true;
            while (search) {
                int x = get_alea(0, lenght_map - 1);
                int y = get_alea(0, lenght_map - 1);
                if (env.map[x][y] == null) {
                    Habitant f = new Habitant(env, x, y, "V", tolerance);
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
                    Habitant s = new Habitant(env, x, y, "R", tolerance);
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
