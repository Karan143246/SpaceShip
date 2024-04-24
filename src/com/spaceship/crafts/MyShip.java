package com.spaceship.crafts;
import javax.swing.ImageIcon;
public class MyShip extends ships{
    public int xspeed ;
    public int yspeed;
    public MyShip(){
        w=200;
        h=200;
        x=50;
        y=500;
        image =new ImageIcon(MyShip.class.getResource("spaceship.gif"));
    }
    public boolean outOfScreen(){
        return x>1500;
    }
    public void move(){
        Hmove();
        Vmove();
    }
    public void Hmove(){
        // remove the below condition for x to make the game one time winning
        if(x>1480) x=0;
        else if(x<0) x=1450;
        x+=xspeed;
    }
    public void Vmove(){
        if(y>920) y=0;
        else if(y<0) y=920;
        y+=yspeed;
    }
}
