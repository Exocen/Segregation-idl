import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

    private String file_name;

    public Logger(String file_name){
        this.file_name = file_name;
    }

    public void write_file(String s) {
        try {
            PrintWriter writer = new PrintWriter(this.file_name, "UTF-8");
            writer.println(s);
            writer.close();
        } catch (IOException ignored) {
        }
    }
}
