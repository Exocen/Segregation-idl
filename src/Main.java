public class Main {

    public static void main(String[] args) {

        if (args.length > 0) {
            if (args[0].equals("0")) {
                SMA_Fish sm = new SMA_Fish();

                while (sm.get_launch()) {
                    sm.play_it();
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                sm.stop_it();
               // Logger log = new Logger("graph_pop.log");
              //  Logger log2 = new Logger("graph_pop_time.log");
                //Logger log3 = new Logger("graph_pop_age.log");
                //log.write_file(sm.tuna_shark_pop);
               // log2.write_file(sm.tuna_shark_overTime);
                sm.make_age();
                //log3.write_file(sm.tuna_shark_age);


            }
            if (args[0].equals("1")) {
                SMA_Seg sm = new SMA_Seg();
                while (sm.get_launch()) {
                    sm.play_it();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                sm.stop_it();
               // Logger log = new Logger("Satisfaction.log");
               // log.write_file(sm.s_log);

            } else {
                System.out.println("Veuillez indiquer un argument\n" +
                        "0 pour l'application Fish\n" +
                        "1 pour l'application Segregation\n" +
                        "Rien pour l'application Dijstra");
            }
        } else {
            SMA_Dij sm = new SMA_Dij();

            while (sm.get_launch()) {
                sm.play_it();
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sm.stop_it();


        }
        System.out.println("Sortie...");
    }

}
