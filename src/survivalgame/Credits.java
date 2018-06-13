/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package survivalgame;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JFrame;

/**
 *
 * @author R
 */
public class Credits extends JFrame {
    JLabel End;
    JLabel End2;
    JLabel End3;
    
    
   Credits(){
       End= new JLabel("Congradulations you have won!!");
       add(End);
       End2 = new JLabel("The programmers responsible in creating this are:");
       add(End2);
       End3 = new JLabel("Garnet Koebel,Kieran Campbell,Graham Kendall,Julia Jones & Rhys Maxwell.");
       add(End3);
       setLayout(new FlowLayout()); 
   }

 }
