package name.panitz.ludens.animationGame;
import name.panitz.ludens.util.ShowlnFrame;
import java.awt.Color;
public class PaintablePanel extends SizedPanel{
    Paintable pa;
    public PaintablePanel(Paintable pa){

        this.pa = pa;

    }
    @Override public void paintComponent(java.awt.Graphics g) {

        pa.paintTo(g);
    }
    public static void main(String[]args){
        ShowlnFrame.show(new PaintablePanel(new PaintableOval(100,50,30,50,new Color(0,0,255))));
        ShowlnFrame.show(new PaintablePanel(new PaintableOval(10.,10.,130.,40.,new Color(250,3,100))));
        ShowlnFrame.show(new PaintablePanel(new PaintableStar(new Vertex(800,400),100,800,50,new Color(0,0,255))));
    }
}