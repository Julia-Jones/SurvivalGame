//World.java controls the process of rendering the world and its moving objects
package survivalgame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//this class implements ActionListener so that it will be able to run
public class World extends JPanel implements ActionListener {

    private Timer timer;
    private Avatar avatar;
    private final int DELAY = 10;

    //the 2 dimensional array for the current screens contents [x][y][heat]
    String worldTiles[][] = new String[9][6];
    int heatMap[][] = new int[9][6];

    static Boolean inventoryDisplay = false;
    ImageIcon axe = new ImageIcon("axeSprite.png", "axe");

    public World() {
        //initializes the background and the avatar as well as the system for checking for inputs
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.white);
        setDoubleBuffered(true);

        initTerrain();

        avatar = new Avatar();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    /*
    precondition: a 2D array of Strings named wordTiles exists and is 9x6, the Screens.xml file exists and contains at least one Screen value
    postcondtion: the array will be filled with the files names for tiles for the overworld
     */
    public void initTerrain() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 6; y++) {

                int i = (int) Math.floor(Math.random() * 10);

                if (i < 5) {
                    worldTiles[x][y] = "snowTile.png";
                    /*
                    in the naturalHeatTile part, the temperature is increased around the heat tiles.
                    These if statements stop that radial heat from getting overwritten
                     */
                    if (heatMap[x][y] <= 0) {
                        heatMap[x][y] = 0;
                    }
                } else if ((i >= 5) && (i < 7)) {
                    worldTiles[x][y] = "stoneTile.png";
                    if (heatMap[x][y] <= 0) {
                        heatMap[x][y] = 0;
                    }
                } else if ((i >= 7) && (i < 9)) {
                    worldTiles[x][y] = "waterTile.png";
                    //waterTile does not have an if check because it cannot be warmed by heat sources
                    heatMap[x][y] = -1;
                } else {
                    worldTiles[x][y] = "naturalHeatTile.png";
                    heatMap[x][y] = 2;
                    //set the heat around the hot area to be hot but not as hot
                    //prevents the program from doing this if the square will be out of bounds
                    if (x != 0) {
                        //these are written with embedded if statements instead of &&'s to prevent checking for
                        //out of bounds array values
                        if (heatMap[x - 1][y] != 2) {
                            heatMap[x - 1][y] = 1;
                        }
                    }
                    if (y != 0) {
                        if (heatMap[x][y - 1] != 2) {
                            heatMap[x][y - 1] = 1;
                        }
                    }
                    if (x != 8) {
                        if (heatMap[x + 1][y] != 2) {
                            heatMap[x + 1][y] = 1;
                        }
                    }
                    if (y != 5) {
                        if (heatMap[x][y + 1] != 2) {
                            heatMap[x][y + 1] = 1;
                        }
                    }

                }
            }
        }
    }

    /*
    precondition a graphics value and a 2D array of Strings named wordTiles exists and is 9x6 exists
    postcondition images matching the file names in worldTiles are placed
     */
    public void drawTerrain(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 6; y++) {
                ImageIcon tileIcon = new ImageIcon(worldTiles[x][y]);
                Image tile = tileIcon.getImage();
                //multiply the x and y by 120 because that is the size of the tiles
                g2d.drawImage(tile, 120 * x, 120 * y, this);
            }
        }
    }

    /*
    precondition a graphics value exists, g2d is imported, avatar exists as an instance of Avatar.java and Avatar.java contains int temperature
    postcondition a bar representing the players temperature is drawn
     */
    
    //following 4 methods draw the bars that represent the survival status of the character
    public void drawTemperature(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.drawRect(9, 689, 101, 21);
        //color the rectangle
        g2d.setColor(Color.pink);
        //draws the avatars temperature bar
        //fill the rectangle
        g2d.fillRect(10, 690, (int) avatar.getTemp(), 20);

        g2d.setColor(Color.black);
        g2d.drawString("Temperature", 15, 705);
    }

    public void drawHealth(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.drawRect(9, 659, 101, 21);
        //color the rectangle
        g2d.setColor(Color.red);
        //draws the avatars temperature bar
        //fill the rectangle
        g2d.fillRect(10, 660, (int) avatar.getHealth(), 20);

        g2d.setColor(Color.black);
        g2d.drawString("Health", 15, 675);
    }

    public void drawThirst(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.drawRect(9, 629, 101, 21);
        //color the rectangle
        g2d.setColor(Color.blue);
        //draws the avatars temperature bar
        //fill the rectangle
        g2d.fillRect(10, 630, (int) avatar.getWater(), 20);

        g2d.setColor(Color.black);
        g2d.drawString("Thirst", 15, 645);
    }

    public void drawHunger(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.drawRect(9, 599, 101, 21);
        //color the rectangle
        g2d.setColor(Color.green);
        //draws the avatars temperature bar
        //fill the rectangle
        g2d.fillRect(10, 600, (int) avatar.getFood(), 20);

        g2d.setColor(Color.black);
        g2d.drawString("Hunger", 15, 615);
    }

    //draws the 
    public void drawInventory(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        String[] inventory = avatar.checkInventory();
        String[][] inventory2d = new String[3][3];

        for (int y = 0; y < 3; y++) {
            inventory2d[0][y] = inventory[3 * y];
            inventory2d[1][y] = inventory[3 * y + 1];
            inventory2d[2][y] = inventory[3 * y + 2];
        }

        g2d.fillRect(270, 180, 540, 360);

        g2d.setColor(Color.white);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                g2d.fillRect(280 + (175 * x), 190 + (115 * y), 170, 110);
                try {
                    if (inventory2d[x][y].equals("axe")) {
                        g2d.drawImage(axe.getImage(), 280 + (175 * x), 190 + (115 * y), this);
                    }
                } catch (NullPointerException e) {

                }

            }
        }
    }

    @Override
    //this is where images are rendered/rendering functions are called if they are rendered once
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //draws the background
        drawTerrain(g);

        //drawing the avatar in its appropriate location
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(avatar.getImage(), avatar.getX(),
                avatar.getY(), this);

        //drawing the frame for the temperature bar
        drawTemperature(g);
        drawHealth(g);
        drawHunger(g);
        drawThirst(g);

        if (inventoryDisplay) {
            drawInventory(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    //created a window to describe the game if needed
     public static void showRules(){
           // This code Shows the rules in a seperate window when the program starts.
    Rules fr = new Rules();
    fr.setVisible(true);
    fr.setSize(350,400);
    fr.setTitle("The Rules");
    }
     //Creating a window for the credits if won 
    public static void showCredits(){
    // This code Shows the rules in a seperate window when the program starts.
    Credits fr = new Credits();
    fr.setVisible(true);
    fr.setSize(350,400);
    fr.setTitle("The Credits");
    }             
    //creating a seperate jframe for when the game is lost it doesnt just close unexpectedly
       public static void showLost(){
    // This code Shows the rules in a seperate window when the program starts.
    Lost fr = new Lost();
    fr.setVisible(true);
    fr.setSize(350,400);
    fr.setTitle("Game Over");
    } 
    if(rules button. is pressed) {                                      
        showRules();
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        avatar.move();
        avatar.changeTemp(heatMap[avatar.getX()/120][avatar.getY()/120]);
        avatar.checkTemp();
        avatar.changeFood(heatMap[avatar.getX()/120][avatar.getY()/120]);
        avatar.checkFood();
        avatar.changeWater(heatMap[avatar.getX()/120][avatar.getY()/120]);
        avatar.checkWater();
        avatar.checkHealth();
        //repainting the area around the ship to avoid redrawing the entire screen
        //repaint(x of where to repaint, y of where to repaint, width of area to make the background colour,
        //height of area to make the background colour)
        repaint();

    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            avatar.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            avatar.keyPressed(e);
        }
    }
}
