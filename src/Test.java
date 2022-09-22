public class Test {
    public static void main(String[] args) {
        Vertex v1 = new Vertex(1.0, 2.0);
        Vertex v2 = new Vertex(42.0, 37.0);
        Vertex v3 = new Vertex(0, 0);
        System.out.println(v1 + " " + v2 + " " + v3);
        System.out.println(v1.length());
    }
}