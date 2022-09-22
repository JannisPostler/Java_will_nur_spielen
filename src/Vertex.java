public class Vertex {

    public double x;
    public double y;



    public Vertex(double x, double y) {
        this.x = x;this.y = y;
    }



    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public Vertex skalarMult(double s){
        return new Vertex(x*s,y*s);
    }

    public void skalarMultMod(double s){
        this.x*=s;
        this.y*=s;
    }
}