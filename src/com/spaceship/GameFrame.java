package com.spaceship;
import javax.swing.JFrame;
public class GameFrame extends JFrame{
    public GameFrame(){
        Board board=new Board();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SPACESHIP");
        setResizable(false);
        setSize(1500,920);
        setLocationRelativeTo(null);
        add(board);
        setVisible(true);
    }
    public static void main(String[] args) {
        new GameFrame();
    }
}
