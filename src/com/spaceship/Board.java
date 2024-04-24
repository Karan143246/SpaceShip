package com.spaceship;
import com.spaceship.crafts.EnemyShip;
import com.spaceship.crafts.MyShip;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class Board extends JPanel{

    BufferedImage background;
    MyShip player;
    EnemyShip enemies[]=new EnemyShip[3];
    Timer timer;
    JButton restartButton;
    Score score;
    public Board(){
        setSize(1500,920);
        BackGround();
        score=new Score();
        player=new MyShip();
        loadEnemies();
        gameLoop();
        setFocusable(true);
        bindEvents();
    }

    // It adds the restart buuton on the board when the game ends
    private void addRestartButton() {
        restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> resetGame());
        restartButton.setBounds(1500 / 2 - 75, 920 / 2, 150, 50);
        add(restartButton);
    }

    // For changing score continuously when the ship crosses the frame
    private void ChangeScore(){
        if(player.x>1480){
            score.count++;
        }
    }

    // To check whether the game is over or not
    private void gameOver(Graphics pen){
        boolean playerWon = player.outOfScreen();
        boolean playerLost = false;
        for (EnemyShip enemy : enemies) {
            if (isCollided(enemy)) {
                playerLost = true;
                break;
            }
        }
        if (playerWon || playerLost) {
            pen.setFont(new Font("times", Font.BOLD, 30));
            pen.setColor(Color.RED);
            if (playerWon) {
                pen.drawString("Congratulations!!", 1200 / 2, 800 / 2);
                pen.drawString("You Won the Game", 1200 / 2, 900 / 2);
            } else if (playerLost) {
                pen.drawString("Game Over !!", 1200 / 2, 800 / 2);
            }
            addRestartButton();
            timer.stop();
        }
    }
    
    // To check the enemy collision
    private boolean isCollided(EnemyShip enemy){
        int xDistance=Math.abs(player.x-enemy.x);
        int yDistance=Math.abs(player.y-enemy.y);
        int maxH=Math.max(player.h,enemy.h);
        int maxW=Math.max(player.w,enemy.w);
        return xDistance<=maxW-120 && yDistance<=maxH-120;
    }

    // Action events 
    private void bindEvents(){
        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e){
                
            }
            public void keyReleased(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                player.xspeed=0;
                else if(e.getKeyCode()==KeyEvent.VK_LEFT)
                player.xspeed=0;
                else if(e.getKeyCode()==KeyEvent.VK_UP)
                player.yspeed=0;
                else if(e.getKeyCode()==KeyEvent.VK_DOWN)
                player.yspeed=0;
            }
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                player.xspeed=2;
                else if(e.getKeyCode()==KeyEvent.VK_LEFT)
                player.xspeed=-2;
                else if(e.getKeyCode()==KeyEvent.VK_UP)
                player.yspeed=-2;
                else if(e.getKeyCode()==KeyEvent.VK_DOWN)
                player.yspeed=2;
            }
        });
    }

    // For loading the enemies on the Borad
    private void loadEnemies(){
        int y=50;
        int gap=300;
        int speed=2;
        for(int i=0;i<3;i++){
            enemies[i] = new EnemyShip(y,speed);
            y+=(gap);
            speed+=1;
        }
    }

    // for painting the enemies
    private void paintEnemies(Graphics pen){
        for(EnemyShip enemy : enemies){
            enemy.draw(pen);
            enemy.move();
        }
    }

    //For running trhe game in loop i,e. continuous printing process
    private void gameLoop(){
        timer =new Timer(5, (e)->repaint());
        timer.start();
    }

    // To load the background
    private void BackGround(){
        try {
            background = ImageIO.read(Board.class.getResource("space.png"));
        } catch (IOException e) {
            System.out.println("Background image not found !!");
            System.exit(1);
            e.printStackTrace();
        }
    }

    // For resetting the game when button is clicked
    public void resetGame() {
        // Reset player and enemy positions
        player.x = 50;
        player.y = 500;
        score.count=0;
        int y=50;
        int gap=300;
        int speed=2;
        for(int i=0;i<3;i++){
            enemies[i].x=1000;
            enemies[i].y=y;
            y+=gap;
            enemies[i].speed=speed;
            speed+=1;
        }
        // Restart the timer or game loop if needed
        timer.restart();
        // Remove restart button if it exists
        if (restartButton != null) {
            remove(restartButton);
            restartButton = null;
        }
    }

    // For painting the components
    public void paintComponent(Graphics pen){
        // all printing lodic will be here
        super.paintComponent(pen);
        pen.drawImage(background,0,0,1500,920,null);
        score.draw(pen);
        player.draw(pen);
        ChangeScore();
        player.move();
        paintEnemies(pen);
        gameOver(pen);
    }
}
