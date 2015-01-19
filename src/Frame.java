import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Frame extends JFrame implements ActionListener, Observer {


    public JButton ss;
    public JButton arret;

    public static ArrayList<Habitant> agents = new ArrayList<Habitant>();
    public final int length_map = 150;
    public ArrayList<Habitant> agents_alea = new ArrayList<Habitant>();
    public int time = 0;
    /**
     * The value of the thread.sleep
     */
    public int slow = 50;
    public Object[][] data;
    public Env env;
    public boolean play = false;
    public boolean launch = true;
    public int nb_habitants;
    public double[] hapiness_hab;
    public double moy_hapiness = 0;
    public String s_log="";

    public Frame() {
        super();
        setTitle("Segregation");
        this.setLayout(new BorderLayout());
        ss = new JButton("Start");
        this.add(ss, BorderLayout.EAST);
        arret = new JButton("Stop");
        this.add(arret, BorderLayout.SOUTH);
        arret.addActionListener(this);
        ss.addActionListener(this);
        this.nb_habitants = length_map * 65;
        this.hapiness_hab = new double[nb_habitants*2];
        env = constructor(nb_habitants, nb_habitants, 4, length_map);
        data = new Object[length_map][length_map];
        Rect jc = new Rect();
        jc.Set_Rect(data);
        this.getContentPane().add(jc);
        this.setSize(length_map * 8, length_map * 8);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void play_it() {

        if (play) {
            if (!this.getFocusableWindowState()) {
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
            Habitant a = agents_alea.get(i);
            a.doIt();
            hapiness_hab[i] = a.get_hapiness();
            i++;
        }
        time++;
        get_moy_hapiness();
        s_log+=time+" "+moy_hapiness+"\n";

        try {
            Thread.sleep(slow);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        update();

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

    public void get_moy_hapiness(){
        int total=0;
        for (double i :hapiness_hab){
            total+=i;
        }
        moy_hapiness=(total/(nb_habitants*2));
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

    //Implémentation du pattern observer
    public void update() {
        repaint();
    }

}
