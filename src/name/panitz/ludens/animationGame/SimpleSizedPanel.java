package name.panitz.ludens.animationGame;
import name.panitz.ludens.util.ShowlnFrame;
public class SimpleSizedPanel extends SizedPanel{
    public void paintComponent(java.awt.Graphics g) {
    g.fillRect(30,50,45,80);
    }
    
    public static void main(String[]args){
        ShowlnFrame.show(new SimpleSizedPanel());
    }
}