package com.spaceship.crafts;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public abstract class ships {
    public int speed;
    public int x;
    public int y;
    public int w;
    public int h;
    ImageIcon image;
    public void draw(Graphics pen){
        pen.drawImage(image.getImage(), x, y,w,h, null);
    } 
    
}
