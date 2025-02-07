import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameLevel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameLevel extends Actor
{
    private static final int TEXT_SIZE = 24;
    private int level = 1;  // Initial game level
    
    public GameLevel()
    {
        updateImage();
    }
    
    /**
     * Act - do whatever the GameLevel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public void increaseLevel()
    {
        level++;
        updateImage();
    }
    
    private void updateImage()
    {
        GreenfootImage image = new GreenfootImage("Game Level: " + level, TEXT_SIZE, Color.YELLOW, new Color(0, 0, 0, 0));
        setImage(image);
    }
}
