import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SafariSelfMovingPaddle extends Actor
{
    private int width;
    private int height;
    private int dx;
    private int speed;  // Speed of the paddle movement
    private int direction;  // 1 for right, -1 for left
    private static final int SAFE_DISTANCE_FROM_GAMER = 200;  // Minimum distance from the gamer's paddle

    
    public SafariSelfMovingPaddle(int width, int height)
    {
        this.width = width;
        this.height = height;
        dx = 1;
        //createImage();
    }
    
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage(width, height);
        image.setColor(Color.BLACK);
        image.fill();
        setImage(image);
    }
    
    public SafariSelfMovingPaddle(int direction)
    {
        this.speed = 3;  // Set the speed
        this.direction = direction;
    }

    // Act method is called in every frame
    public void act()
    {
        moveHorizontally();
        checkWallCollision();
        move(1);
    }

    // Method to move the paddle horizontally
    private void moveHorizontally()
    {
        setLocation(getX() + speed * direction, getY());
    }

    // Check if the paddle hits the wall
    private void checkWallCollision()
    {
        if (getX() <= 0 || getX() >= getWorld().getWidth() - 1)
        {
            respawnAtRandomHeight();
        }
    }

    // Respawn the paddle at a random height on the opposite wall
    private void respawnAtRandomHeight()
    {
        World world = getWorld();
        SafariPaddle gamer = (SafariPaddle) world.getObjects(SafariPaddle.class).get(0);  // Assuming there's only one gamer paddle

        int newY;
        do {
            // Random Y position for the new paddle
            newY = Greenfoot.getRandomNumber(world.getHeight());
        } while (Math.abs(newY - gamer.getY()) < SAFE_DISTANCE_FROM_GAMER);  // Ensure it doesn't spawn too close to the gamer paddle

        int newX;
        if (getX() <= 0)  // If it hit the left wall, respawn on the right wall
        {
            newX = world.getWidth() - 1;
            direction = -1;  // Move left
        }
        else  // If it hit the right wall, respawn on the left wall
        {
            newX = 0;
            direction = 1;  // Move right
        }

        // Set the new position of the paddle
        setLocation(newX, newY);
    }
}
