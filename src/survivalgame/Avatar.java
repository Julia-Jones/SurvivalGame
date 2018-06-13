//Avatar.java handles the character and interactions with them
package survivalgame;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Avatar {
    //how much the avatar will move horizontally
    private int dx;
    //how much the avatar will move vertically
    private int dy;
    //the avatars default position
    private int x = 40;
    private int y = 60;
    //avatars game stats
    private double temperature = 100;
    private double food = 100;
    private double water = 100;
    private double health = 100;
    //the image for the avatar
    final private Image image;
    //inventory array
    String inventory [] = new String [9];

    //prints the avatar to screen
    public Avatar() {
        ImageIcon avatarImage = new ImageIcon("protagonist.png");
        image = avatarImage.getImage();
    }

    public void move() {
        //change x and y based on the appropriate change from dx/dy/keyPressed
        x += dx;
        y += dy;
    }
    
    public void checkTemp(){
        if (temperature <= 0){
            //eventually replace this with the actual death event
            health-= 0.1;
        }
    }
    
    public void changeTemp(int areaTemp){
        if ((areaTemp == -1)&&(temperature > 0)){
            temperature-=0.5;
        }
        if ((areaTemp == 0)&&(temperature > 0)){
            temperature-=0.05;
        }
        if ((areaTemp == 1)&&(temperature < 100)){
            temperature+=0.05;
        }
        if ((areaTemp == 2)&&(temperature < 100)){
            temperature+=0.1;
        }
    }
    public void checkFood() {
    	if (food <= 0) {
    		health-= 0.1;
    	}
    }
    
    public void checkWater() {
    	if (water <= 0) {
    		health-= 0.1;
    	}
    }
    
    public void checkHealth(){
        if (health <= 0){
            System.exit(0);
        }
    }
    
    public void changeWater(int areaTemp) {
    	if ((areaTemp > -1)&&(water > 0)) {
    		water-=0.1;
    	}
    	if ((areaTemp == -1)&&(water < 100)) {
    		water+=2;
    	}
    }
    
    public void changeFood(int areaTemp) {
    	if ((areaTemp < 2)&&(food > 0)) {
    		food-=0.1;
    		
    	}
    	if ((areaTemp == 2)&&(food < 100)) {
    		food+=0.1;
    		
    	}
    }
    
    public double getTemp(){
        return temperature;
    }
    
    public double getWater(){
        return water;
    }
    
    public double getFood(){
        return food;
    }
    
    public double getHealth(){
        return health;
    }

    public int getX() {
        
        return x;
    }

    public int getY() {
        
        return y;
    }    

    public Image getImage() {
        
        return image;
    }
    
    public String [] checkInventory (){
        inventory [0] = "axe";
        return inventory;
    }
    
    public void addToInventory (String item){
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
        if (key == KeyEvent.VK_I) {
            if (World.inventoryDisplay){
                World.inventoryDisplay = false;
            }else{
                World.inventoryDisplay = true;
            }
        }
        
    }

    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}