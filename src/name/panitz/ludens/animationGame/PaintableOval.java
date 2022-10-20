package name.panitz.ludens.animationGame;
import java.awt.Color;
public class PaintableOval extends SimpleOval implements Paintable{
    Vertex pos;
    double width;
    double height;
    Color c=new Color(0,0,0);
    public PaintableOval(double width,double height,double x,double y,Color c) {
        super(width,height,x,y,c);
        this.pos=new Vertex(x,y);
        this.width=width;
        this.height=height;
        this.c=c;
    }

    public PaintableOval(int width, int height, int x, int y) {
        super(width,height,x,y);
    }

    public void paintTo(java.awt.Graphics g){
            g.setColor(c);
            g.fillOval((int)pos.x,(int)pos.y,(int)width,(int)height);
        }
    }