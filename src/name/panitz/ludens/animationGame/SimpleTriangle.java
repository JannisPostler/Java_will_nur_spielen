package name.panitz.ludens.animationGame;
import java.awt.Color;
public class SimpleTriangle extends GeometricObject{
    GeometricObject g;
    Vertex pos;
    double width;
    double height;
    Color c=new Color(0,0,0);
    SimpleTriangle(Vertex pos,double a){
        super(pos,a,Math.sqrt(a*a*3/4.));
        this.pos=new Vertex(pos.x,pos.y);
        this.width=a;
        height=Math.sqrt(width*width*3/4.);
    }
    SimpleTriangle(Vertex pos,double a,Color c){
        this(pos,a);
        this.c=c;
    }
    public double circumference(){
        return 3*width;
    }
    public double area(){
        return width*height/2.;
    }
}
