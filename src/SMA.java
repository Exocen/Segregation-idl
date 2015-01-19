import java.util.ArrayList;

/**
 * Created by exo on 19/01/15.
 */
public abstract class SMA {

    private ArrayList<Observer> listObserver = new ArrayList<Observer>();

    public static ArrayList<Agent> agents = new ArrayList<Agent>();
    public final int length_map = 150;
    public ArrayList<Agent> agents_alea = new ArrayList<Agent>();
    public int time = 0;
    /**
     * The value of the thread.sleep
     */
    public int slow = 50;
    public Object[][] data;
    public Env env;
    public boolean play = false;
    public boolean launch = true;


    public SMA(){

    }
}
