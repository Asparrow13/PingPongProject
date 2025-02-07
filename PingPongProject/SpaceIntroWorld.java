import greenfoot.*;

/**
 * Write a description of class IntroWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpaceIntroWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;

    /**
     * Constructor for objects of class IntroWorld.
     */
    public SpaceIntroWorld()
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        GreenfootImage background = getBackground();
        GreenfootImage image = new GreenfootImage("Hit <Enter> to start", 48, Color.WHITE, null);
        getBackground().drawImage(image, WORLD_WIDTH / 2 - 165, WORLD_HEIGHT / 2 + 140);
        GreenfootImage image2 = new GreenfootImage("Control the paddle with left and right arrow keys", 20, Color.WHITE, null);
        getBackground().drawImage(image2, WORLD_WIDTH / 2 - 165, WORLD_HEIGHT / 2 + 250);
        GreenfootImage image3 = new GreenfootImage("Change world by pressing <space>", 22, Color.WHITE, null);
        getBackground().drawImage(image3, WORLD_WIDTH / 2 - 130, WORLD_HEIGHT / 2 + 280);
    }
    
        public void act() 
    {
    String key = Greenfoot.getKey();
        if (key != null)
        {
            if (key.equals("enter"))
            {
                Greenfoot.setWorld(new SpacePingWorld(true));
            }
            else if (key.equals("space"))
            {
                Greenfoot.setWorld(new SafariIntroWorld());
            }
        }
    }

}
