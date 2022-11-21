package test.de.westranger.geometry.common.simple;

import de.westranger.geometry.common.math.Vector2D;
import de.westranger.geometry.common.simple.Point2D;

import javax.swing.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;

public class CanvasTest extends Canvas {

    //TODO code sichern und entfernen

    private static final Point2D pt1 = new Point2D(100.0, 100.0);
    private static final Point2D pt2 = new Point2D(700.0, 100.0);
    private static final Point2D pt3 = new Point2D(700.0, 300.0);
    private static final Point2D pt4 = new Point2D(100.0, 300.0);

    private static final List<Point2D> pts = new LinkedList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("My Drawing");
        Canvas canvas = new CanvasTest();
        canvas.setSize(800, 600);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);

        lerpBetween(pt1, pt2);
        lerpBetween(pt2, pt3);
        lerpBetween(pt3, pt4);
        lerpBetween(pt4, pt1);
    }

    //TODO in point2D auslagern
    public static void lerpBetween(final Point2D pt1, final Point2D pt2) {
        final Vector2D diff = pt1.diff(pt2);

        for (double t = 0.0; t <= 1.0; t += 0.06 + (Math.random() * 0.06)) {
            Vector2D tmp = pt1.toVector2D().add(diff.lerp(t));
            pts.add(new Point2D(tmp.getX(), tmp.getY()));
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int idxA = 0;
        int idxB = pts.size() - 1;
        boolean toggle = false;
        Point2D prev = pts.get(idxA++);
        while (idxA < idxB) {
            Point2D current = null;
            if (toggle) {
                current = pts.get(idxA++);
            } else {
                current = pts.get(idxB--);
            }

            g2d.setStroke(new BasicStroke(4f));
            g2d.drawLine((int) prev.getX(), (int) prev.getY(), (int) current.getX(), (int) current.getY());

            prev = current;
            toggle = !toggle;
        }

        double diff = 6;
        Point2D pt1x = new Point2D(pt1.getX()-diff,pt1.getY()-diff);
        Point2D pt2x = new Point2D(pt2.getX()+diff,pt2.getY()-diff);
        Point2D pt3x = new Point2D(pt3.getX()+diff,pt3.getY()+diff);
        Point2D pt4x = new Point2D(pt4.getX()-diff,pt4.getY()+diff);

        g2d.drawLine((int) pt1x.getX(), (int) pt1x.getY(), (int) pt2x.getX(), (int) pt2x.getY());
        g2d.drawLine((int) pt2x.getX(), (int) pt2x.getY(), (int) pt3x.getX(), (int) pt3x.getY());
        g2d.drawLine((int) pt3x.getX(), (int) pt3x.getY(), (int) pt4x.getX(), (int) pt4x.getY());
        g2d.drawLine((int) pt4x.getX(), (int) pt4x.getY(), (int) pt1x.getX(), (int) pt1x.getY());


        //g.fillOval(100, 100, 200, 200);
    }
}

