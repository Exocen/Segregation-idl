import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SMA extends JFrame implements ActionListener {

    public static ArrayList<Agent> agents = new ArrayList<Agent>();
    public final int length_map = 150;
    public ArrayList<Agent> agents_alea = new ArrayList<Agent>();
    public int time = 0;
    /**
     * The value of the thread.sleep
     */
    public int slow = 250;
    public Object[][] data;
    public Env env;
    public boolean play = false;
    public boolean launch = true;
    public JButton ss;
    public JButton arret;

    public SMA() {
        super();
        setTitle("Segregation");
        this.setLayout(new BorderLayout());
        ss = new JButton("Start");
        this.add(ss, BorderLayout.EAST);
        arret = new JButton("Stop");
        this.add(arret, BorderLayout.SOUTH);
        arret.addActionListener(this);
        ss.addActionListener(this);
        env = constructor( length_map*60, length_map*60, 4, length_map);
        data = new Object[length_map][length_map];
        Rect jc = new Rect();
        jc.Set_Rect(data);
        this.getContentPane().add(jc);
        this.setSize(length_map * 8, length_map * 8);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SMA sm = new SMA();
        sm.dIt();
        while (sm.launch) {
            sm.play_it();
        }
        sm.dispose();
        System.out.println("Sortie...");
    }

    public void play_it() {

        if (play) {
            if (!this.getFocusableWindowState()){
                render_console(env);
            }
            dIt();
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
        repaint();

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

    public void write_file(String s, String file_name) {
        try {
            PrintWriter writer = new PrintWriter(file_name, "UTF-8");
            writer.println(s);
            writer.close();
        } catch (IOException ignored) {
        }
    }

    public Env constructor(int nb_green, int nb_red, int tolerance, int lenght_map) {
        Env env = new Env(lenght_map, lenght_map);
        for (int i = 0; i < nb_green; i++) {
            boolean search = true;
            while (search) {
                int x = get_alea(0, lenght_map - 1);
                int y = get_alea(0, lenght_map - 1);
                if (env.map[x][y] == null) {
                    Agent f = new Agent(env, x, y, "V", tolerance);
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
                    Agent s = new Agent(env, x, y, "R", tolerance);
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

    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == ss) {
            play = !play;
            if (!play) {
                ss.setText("Start");
            } else {
                ss.setText("Pause");
            }
        } else if (arg0.getSource() == arret) {
            launch = false;
            play = false;

        }
    }

}
