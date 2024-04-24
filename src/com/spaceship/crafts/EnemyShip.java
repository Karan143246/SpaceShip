package com.spaceship.crafts;
import javax.swing.ImageIcon;
public class EnemyShip extends ships{
    public EnemyShip(int y, int speed){
        w=200;
        h=200;
        this.speed=speed;
        x=1000;
        this.y=y;
        image=new ImageIcon(EnemyShip.class.getResource("enemyship.gif"));
    }
    public void move(){
        if(x<0){
            x=1500;
            y=(y+100)%920;
        }
        x-=speed;
    }
}
