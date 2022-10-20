package name.panitz.ludens.animationGame;
import java.awt.Color;
public class PaintableRectangle extends SimpleOval implements Paintable {
    Vertex pos;
    double width;
    double height;
    Color c = new Color(0, 0, 0);

    public PaintableRectangle(double width, double height, double x, double y, Color c) {
        super(width, height, x, y, c);
        this.pos = new Vertex(x, y);
        this.width = width;
        this.height = height;
        this.c = c;
    }

    public PaintableRectangle(double width, double height, double x, double y) {
        super(width, height, x, y);
    }

    public void paintTo(java.awt.Graphics g) {
        g.setColor(c);
        g.fillRect((int) pos.x, (int) pos.y, (int) width, (int) height);
    }
}