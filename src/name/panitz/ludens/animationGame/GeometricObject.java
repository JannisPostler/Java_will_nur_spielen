package name.panitz.ludens.animationGame;
import java.awt.Color;
public class GeometricObject {
    Vertex pos;
    double width;
    double height;
    Color c=new Color(0,0,0);
    public GeometricObject(Vertex pos, double width,double height){
        this.pos=new Vertex(pos.x,pos.y);
        this.width=width;
        this.height=height;
    }
    public GeometricObject(Vertex pos, double width,double height,Color c){
        this.pos=new Vertex(pos.x,pos.y);
        this.width=width;
        this.height=height;
        this.c=c;
    }
    public GeometricObject(double x,double y, double width,double height){
       this(new Vertex(x, y),width,height);
    }

    public GeometricObject(Vertex vertex) {
        this.pos=new Vertex(vertex.x,vertex.x);
        width=0;
        height=0;
    }

    public boolean equals(Object thatObject){
        if(thatObject instanceof GeometricObject){
            GeometricObject that=(GeometricObject)thatObject;
            return (pos.equals(that.pos)&&width==that.width&&height==that.height);
        }
        return false;
    }
    @Override public String toString(){
        return pos.x+","+pos.y+","+width+","+height;
    }
    public void setColor(Color c){
        this.c=c;
    }

    public void setX(double x){
        this.pos.x=x;
    }
    public void setY(double y){
        this.pos.y=y;
    }
    public void setWidth(double width){
        this.width=width;
    }
    public void setHeight(double height){
        this.height=height;
    }

    public double getX(){
        return pos.x;
    }
    public double getY(){
        return pos.y;
    }
    public double getWidth(){
        return width;
    }
    public double getHeight(){
        return height;
    }
}
