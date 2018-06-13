/*
created and basic movement written  Rhys M. 6/3/2018
heating system implemented Garnet K. 6/6/2018
heating system debugged Rhys Maxwell 6/8/2017
*/

//This code was based on the sprite tutorial from http://zetcode.com/tutorials/javagamestutorial
//additional g2d help from http://www.codejava.net/java-se/graphics/drawing-rectangles-examples-with-graphics2d
//SurvivalGame.java is the main entry point for the program
package survivalgame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class SurvivalGame extends JFrame {

    public SurvivalGame() {   
        add(new World());
        //name above the program
        setTitle("Survival Game");
        //size of the window, 1080 x 720 + 29(size of the top bar)
        setSize(1080, 749);
        
        //centers the program
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
//    Credits fr = new Credits();
//    fr.setVisible(true);
//    fr.setSize(350,400);
//    fr.setTitle("Credits");
//
// // public GUI(){
//        JButton AddButton = new javax.swing.JButton();    
////public class GUI 
//{    
//  public GUI()
//  {    
//    JFrame frame = new JFrame ("Quotes");
//    frame.setSize(500, 500);
//    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//
//    JButton.addActionListener (new ActionListener() {
//       public void actionPerformed (ActionEvent ae) {
//         try{
//         MainProgram.SurvivalGame (new String[]);
//         } catch (Exception e) {
//        e.printStackTrace();
//       }
//     }
//  });
//  }
        
        
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            SurvivalGame ex = new SurvivalGame();
            ex.setVisible(true);
        });
    }
}