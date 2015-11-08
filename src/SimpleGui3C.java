import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Shipa on 07.11.15.
 */

public class SimpleGui3C{
    JFrame frame;
    JLabel label;
    boolean down = true;
    int x  = 150, y = 150;
    MyDrowPanel drawPanel = new MyDrowPanel();
    public static void main (String[] args){
        SimpleGui3C gui = new SimpleGui3C();
        gui.go();
    }

    private void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton labelButton = new JButton("Change Label");
        labelButton.addActionListener(new LabelListner());

        JButton colorButton = new JButton("Change Color");
        colorButton.addActionListener(new ColorListner());

        label = new JLabel("I'm а label");


        frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        frame.getContentPane().add(BorderLayout.EAST, labelButton);
        frame.getContentPane().add(BorderLayout.WEST, label);

        frame.setSize(7000, 7000);
        frame.setVisible(true);
        anim();

    }
    private void anim(){
        if (down){
            for (int i = 0; i < 130; i++) {
                x++;
                y++;
                drawPanel.repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        } else{
            for (int i = 130; i > 0; i--) {
                x--;
                y--;
                drawPanel.repaint();
                try {
                    Thread.sleep(25);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        down = !down;
    }
    class LabelListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            label.setText("Ouch!");
        }
    }

    class ColorListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            anim();
        }
    }

    class MyDrowPanel extends JPanel{
        public void paintComponent(Graphics g){
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            Graphics2D g2d = (Graphics2D) g;

            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            Color StartColor = new Color(red, green, blue);

            red = (int) (Math.random() * 255);
            green = (int) (Math.random() * 255);
            blue = (int) (Math.random() * 255);
            Color EndColor = new Color(red, green, blue);

            GradientPaint gradient = new GradientPaint(150, 150, StartColor, 550, 550, EndColor);
            g2d.setPaint(gradient);
            g2d.fillOval(x, y, 500, 500);
        }
    }
}