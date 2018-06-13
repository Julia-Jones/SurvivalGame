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
    public class Lost extends JFrame{
    JLabel output;
        
    
    //outputs a losing message before the game just exits automatically
   Lost(){
       output= new JLabel("You have lost the game!");
       add(output);
       setLayout(new FlowLayout()); 
   }
}
