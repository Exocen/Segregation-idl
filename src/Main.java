/**
 * Created by exo on 19/01/15.
 */
public class Main {

    public static void main(String[] args) {
        Frame sm = new Frame();
        sm.dIt();
        while (sm.launch) {
            sm.play_it();
        }
        sm.dispose();
        sm.write_file(sm.s_log,"Hapiness.log");
        System.out.println("Sortie...");
    }

}
