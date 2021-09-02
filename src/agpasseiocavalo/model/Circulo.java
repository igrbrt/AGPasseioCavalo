/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agpasseiocavalo.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author igor
 */
public class Circulo extends JPanel {

    Graphics cavalo;
    int x, y;

    public Circulo() {
    }
    
    @Override
    public void paintComponent(Graphics g){
        System.out.println("aSDasd");
        super.paintComponent(g);  
        this.setBackground(Color.WHITE);  
  
        g.setColor(Color.RED);  
        g.drawLine(x,y, x+30,y+30);  
          
//        g.setColor(Color.BLUE);  
//        g.drawRect(5, 40, 90, 55);  
//        g.fillRect(100, 40, 90, 55);  
//          
//        g.setColor(Color.CYAN);  
//        g.fillRoundRect(195, 40, 90, 55, 50, 50);  
//        g.drawRoundRect(290, 40, 90, 55, 20, 20);  
//          
//        g.setColor(Color.GREEN);  
//        g.draw3DRect(5, 100, 90, 55, true);  
//        g.fill3DRect(100, 100, 90, 55, false);  
//          
//        g.setColor(Color.MAGENTA);  
//        g.drawOval(195, 100, 90, 55);  
//        g.fillOval(290, 100, 90, 55);  
    }// end metodo

    

    public void setX(int aux) {
        this.x = aux;
    }

    public void setY(int aux) {
        this.y = aux;
    }
}
