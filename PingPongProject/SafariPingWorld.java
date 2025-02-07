import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SafariPingWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SafariPingWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    private GameLevel gameLevel;
    
    /**
     * Constructor for objects of class SafariPingWorld.
     * 
     */
    public SafariPingWorld(boolean gameStarted)
    {    
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        if (gameStarted)
        {
            GreenfootImage background = getBackground();
            background.setColor(Color.BLACK);
            // Create a new world with WORLD_WIDTHxWORLD_HEIGHT cells with a cell size of 1x1 pixels.
            addObject(new SafariBall(), WORLD_WIDTH/2, WORLD_HEIGHT/2);
            addObject(new SafariPaddle(100,20), 250, WORLD_HEIGHT - 50);
            addObject(new SafariSelfMovingPaddle(100,20), 50, (Greenfoot.getRandomNumber(450)));
            
            gameLevel = new GameLevel();
            addObject(gameLevel, getWidth() - 100, 30); // Position in the upper right corner

        }
        else
        {
            Greenfoot.setWorld(new SafariIntroWorld());
        }
    }
    
     public void increaseGameLevel()
    {
        gameLevel.increaseLevel();
    }
    
     public void resetGameLevel()
    {
        if (gameLevel != null) {
            removeObject(gameLevel);
        }
        gameLevel = new GameLevel(); // Re-create the game level display
        addObject(gameLevel, getWidth() - 100, 30); // Re-add it to the world
    }
}
