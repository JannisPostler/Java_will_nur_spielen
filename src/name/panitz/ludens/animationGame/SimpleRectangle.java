package name.panitz.ludens.animationGame;
import java.awt.Color;
public class SimpleRectangle extends GeometricObject {
    GeometricObject g;
    SimpleRectangle(Vertex v,double width,double height,Color c){
        super(v,width,height,c);
    }
    SimpleRectangle(double x,double y,double width,double height,Color c){
        this(new Vertex(x,y),width,height,c);
    }
    SimpleRectangle(Vertex v,double width,double height){
        super(v,width,height);
    }
    SimpleRectangle(double x,double y,double width,double height){
        this(new Vertex(x,y),width,height);
    }
    public void setX(double x){
        this.g.pos.x=x;
    }
    public void setY(double y){
        this.g.pos.y=y;
    }
    public void setWidth(double width){
        this.g.width=width;
    }
    public void setHeight(double height){
        this.g.height=height;
    }

    public double getX(){
        return g.pos.x;
    }
    public double getY(){
        return g.pos.y;
    }
    public double getWidth(){
        return g.width;
    }
    public double getHeight(){
        return g.height;
    }

  public double area(){
    return width*height;
  }
  public double circumference(){
    return 2*width+2*height;
  }
}