package name.panitz.ludens.animationGame;
import java.awt.Polygon;
import java.awt.Color;
public class PaintableStar extends GeometricObject implements Paintable {
    Vertex pos;
    double rInnen;
    double rAussen;
    int zacken;
    Color c=new Color(0,0,0);
    Polygon p=new Polygon();
    PaintableStar(Vertex pos,double rInnen,double rAussen,int zacken){
        super(pos,rInnen,rAussen);
        this.pos=new Vertex(pos.x,pos.y);
        this.rInnen=width;
        this.rAussen=height;
        this.zacken=zacken;
        for(int i=0;i<2*zacken;i++){
            p.addPoint((int)(pos.x+Math.cos(2*Math.PI/2./zacken*i)*(rInnen*(i%2)+rAussen*((i+1)%2))),(int)(pos.y+Math.sin(2*Math.PI/2./zacken*i)*(rInnen*(i%2)+rAussen*((i+1)%2))));
        }
    }
    PaintableStar(Vertex pos,double rInnen,double rAussen,int zacken,Color c){
        this(pos,rInnen,rAussen,zacken);
        this.c=c;
    }
public void paintTo(java.awt.Graphics g){
    g.setColor(c);
    g.fillPolygon(p);
}
}
