public class Main {

    public static void main(String[] args) {
        SMA_Seg sm = new SMA_Seg();
        //SMA_Fish sm = new SMA_Fish();

        while (sm.get_launch()) {
            sm.play_it();
        }
        sm.stop_it();
        Logger log = new Logger("Satisfaction.log");
        //log.write_file(sm.s_log);
        System.out.println("Sortie...");
    }
}
