package name.panitz.ludens.animationGame;

import name.panitz.ludens.util.ShowlnFrame;

import java.awt.Color;
import java.util.*;

public  class PaintablesPanel extends SizedPanel{
    List<Paintable> pas;
    public PaintablesPanel(List<Paintable>pas){
        this.pas=pas;
    }
    public void paintComponent(java.awt.Graphics g){
        for(Paintable pa:pas) pa.paintTo(g);
    }
    public static void main(String args[]){
        List<Paintable> ps=new ArrayList<Paintable>();
        //ps.add(new PaintableRectangle(100,200, 300,500));
        ps.add(new PaintableStar(new Vertex(500,300),40,30,5,new Color(255, 154, 100)));
        ShowlnFrame.show("Ovale",new PaintablesPanel(ps));
        ShowlnFrame.show(new PaintablePanel(new PaintableRectangle(300,200,100,50)));
    }
}