public class Main {

    public static void main(String[] args) {
        //SMA_Seg sm = new SMA_Seg();
        SMA_Fish sm = new SMA_Fish();

        while (sm.get_launch()) {
            sm.play_it();
        }
        sm.stop_it();
        //Logger log = new Logger("Satisfaction.log");
        //log.write_file(sm.s_log);
        Logger log = new Logger("graph_pop.log");

        Logger log2 = new Logger("graph_pop_time.log");

        Logger log3 = new Logger("graph_pop_age.log");
        log.write_file(sm.tuna_shark_pop);
        log2.write_file(sm.tuna_shark_overTime);
        sm.make_age();
        log3.write_file(sm.tuna_shark_age);

        System.out.println("Sortie...");
    }
}
