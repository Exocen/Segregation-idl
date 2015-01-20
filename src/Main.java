public class Main {

    public static void main(String[] args) {
        SMA sm = new SMA();

        while (sm.get_launch()) {
            sm.play_it();
        }
        sm.stop_it();
        Logger log = new Logger("Satisfaction.log");
        log.write_file(sm.s_log);
        System.out.println("Sortie...");
    }

}
