import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PaddleSafari here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SafariPaddle extends Actor
{
     private int width;
    private int height;
    private int dx;

    /**
     * Constructs a new paddle with the given dimensions.
     */
    public SafariPaddle(int width, int height)
    {
        this.width = width;
        this.height = height;
        dx = 1;
        //createImage();
    }

    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //tryChangeDirection();
        //setLocation(getX() + dx, getY());
        checkKeyPress();
    }    

    /**
     * Will rotate the paddle 180 degrees if the paddle is at worlds edge.
     */
    private void tryChangeDirection()
    {
        //Check to see if we are touching the outer boundaries of the world:
        // IF we are touching the right boundary OR we are touching the left boundary:
        if(getX() + width/2 >= getWorld().getWidth() || getX() - width/2 <= 0)
        {
            //Change our 'x' direction to the inverted direction:
            dx = dx * -1;
        }
    }

    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage(width, height);
        image.setColor(Color.BLACK);
        image.fill();
        setImage(image);
    }
    
    public void checkKeyPress()
    {
        if (Greenfoot.isKeyDown("left"))
        {
            move(-2);
        }
        
        if (Greenfoot.isKeyDown("right"))
        {
            move(2);
        }
        
    }
}
