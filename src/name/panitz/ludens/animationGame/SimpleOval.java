package name.panitz.ludens.animationGame;
import java.awt.Color;
public class SimpleOval extends GeometricObject{
    GeometricObject g;
    public SimpleOval(Vertex v, double width, double height,Color c){
       super(v,width,height,c);
    }
    public SimpleOval(double width,double height,double x, double y,Color c){
        super(new Vertex(x,y),width,height,c);
    }
    public SimpleOval(Vertex v, double width, double height){
        super(v,width,height);
     }
     public SimpleOval(double width,double height,double x, double y){
         super(new Vertex(x,y),width,height);
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
    return width*height*Math.PI;
  }
}
