package name.panitz.ludens.util;
import javax.swing.*;
public class ShowlnFrame{
    public static void show(String title,JComponent c){
        JFrame f=new JFrame(title);
        f.add(c);
        f.pack() ;
        f.setVisible(true);
    }
    public static void show(JComponent c){
        show("",c);
    }
}