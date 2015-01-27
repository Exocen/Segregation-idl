import javax.swing.*;
import java.awt.*;

public class Rect extends JComponent {
    public Object[][] donnees;

    public void Set_Rect(Object[][] o) {
        donnees = o;
    }

    public void paint(Graphics g) {
        int x = 10;
        int y = 10;
        for (Object[] i : donnees) {
            for (Object j : i) {
                if (j == null) {
                    g.setColor(Color.white);
                } else if (j.toString().equals(" ")) {
                    g.setColor(Color.white);
                } else if (j.toString().equals("R")) {
                    g.setColor(Color.red);
                } else if (j.toString().equals("V")) {
                    g.setColor(Color.green);
                } else if (j.toString().equals("S")) {
                    g.setColor(Color.red);
                } else if (j.toString().equals("T")) {
                    g.setColor(Color.green);
                } else if (j.toString().equals("P")) {
                    g.setColor(Color.green);
                } else if (j.toString().equals("H")) {
                    g.setColor(Color.red);
                }
                else if (j.toString().equals("W")) {
                    g.setColor(Color.black);
                }

                g.fillRect(x, y, 5, 5);
                x += 6;
            }
            x = 10;
            y += 6;
        }

    }

}
