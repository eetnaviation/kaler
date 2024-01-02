package gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class pointRenderer extends JPanel{
    public static final int POINTS_NUM = 99999;
    public static final Color POINT_COLOR = Color.RED;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        double padding = 10;
        double radius = Math.min(this.getWidth(), this.getHeight()) / 2 - padding * 2;

        g2.draw(new Ellipse2D.Double(padding, padding, radius * 2, radius * 2));
        g2.setColor(POINT_COLOR);
        g2.setColor(Color.GREEN);

        for (int i = 0; i < POINTS_NUM; i++) {
            double a = Math.random() * 2 * Math.PI;
            double r = radius * Math.sqrt(Math.random());
            g2.setColor(Color.RED);
            double x = r * Math.cos(a) + radius + padding;
            double y = r * Math.sin(a) + radius + padding;

            g2.draw(new Ellipse2D.Double(x, y, 1, 1));
            g2.setColor(Color.BLUE);
        }
    }

    public static void main() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("TestDots");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationByPlatform(true);
                frame.add(new pointRenderer());
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}