package com.spaceship;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score {
    public int count;
    void draw(Graphics pen){
        pen.setFont(new Font("times", Font.BOLD, 30));
        pen.setColor(Color.RED);
        pen.drawString("SCORE : "+count, 1300 / 2,50);
    }
}
