import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {


    public JButton ss;
    public JButton arret;

    public boolean play = false;
    public boolean launch = true;

    public Frame(Rect rect) {
        super();
        setTitle("Segregation");
        this.setLayout(new BorderLayout());
        ss = new JButton("Start");
        this.add(ss, BorderLayout.EAST);
        arret = new JButton("Stop");
        this.add(arret, BorderLayout.SOUTH);
        arret.addActionListener(this);
        ss.addActionListener(this);
        this.getContentPane().add(rect);
        this.setSize(150 * 8, 150 * 8);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public boolean get_play(){
        return this.play;
    }

    public boolean get_launch(){
        return this.launch;
    }

    //Implémentation du pattern observer
    public void update() {
        repaint();
    }

}
