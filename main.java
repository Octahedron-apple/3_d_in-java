import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("3D animation - Sphere");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        object ob = new object();
        ob.unit_Sphere(50);
        camera cam = new camera(new float[] { 0, 0, -100 }, new float[] { 0, 0, 0 }, 15);

        for (int i = 0; i < ob.main.size(); i++) {
            ob.main.get(i).cam = cam;
        }

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                ob.render();
                for (int i = 0; i < ob.main.size(); i++) {
                    int x = (int) (ob.main.get(i)._2D_position[0] * 4 + 250);
                    int y = (int) (ob.main.get(i)._2D_position[1] * 4 + 250);
                    g.fillOval(x - 2, y - 2, 4, 4);
                }
            }
        };

        panel.setBackground(Color.WHITE);
        frame.add(panel);
        frame.setVisible(true);

        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.rotate(5, 0); // X-axis
                ob.rotate(3, 1); // Y-axis
                panel.repaint();
            }
        });
        timer.start();
    }
}
