/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package survivalgame;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author R
 */
public class Rules extends JFrame{
    JLabel rule1;
    JLabel rule2;
    JLabel rule3;
    
    
   Rules(){
       rule1= new JLabel("Keep your character alive by keeping your health bar up.");
       add(rule1);
       rule2 = new JLabel("");
       add(rule2);
       rule3 = new JLabel("Controls: Use the arrow keys to more the charcter.");
       add(rule3);
       setLayout(new FlowLayout()); 
   }
  
 }

