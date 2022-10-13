package name.panitz.ludens.animationGame;

import name.panitz.ludens.util.ShowlnFrame;
import javax.swing.*;
import java.util.Objects;

public class GeometricImage extends GeometricObject implements Paintable {
    ImageIcon icon;

    public GeometricImage(String fileName, double x, double y) {
        super(new Vertex(x, y));

        icon = new ImageIcon((getClass().getClassLoader().getResource(fileName)));
    init();
}
public GeometricImage(ImageIcon icon,double x,double y) {
    super(new Vertex(x,y));
    this.icon = icon;
    init();
}
        void init () {
            width=icon.getImage().getWidth(icon.getImageObserver());
            height=icon.getImage().getHeight(icon.getImageObserver());
}
    public void paintTo(java.awt.Graphics g) {
    icon.paintIcon(null,g,(int)pos.x,(int)pos.y);
    }

     public static void main(String [] args) {
         GeometricImage gi=new GeometricImage("test.png",10,10);
        System.out.println(gi.width);
        System.out.println(gi.height);
        ShowlnFrame.show(new PaintablePanel(gi));
}
        }
