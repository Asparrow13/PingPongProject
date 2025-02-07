import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BallSafari here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SafariBall extends SmoothMover
{
    private static final int BALL_SIZE = 25;
    private static final int BOUNCE_DEVIANCE_MAX = 5;
    private static final int STARTING_ANGLE_WIDTH = 90;
    private static final int DELAY_TIME = 100;
    private static final int HIT_THRESHOLD = 10; // Number of hits before speed increases
    private static final int SPEED_INCREMENT = 1; // Amount by which speed increases

    private int speed;
    private boolean hasBouncedHorizontally;
    private boolean hasBouncedVertically;
    private int delay;
    private int hitCount = 0; // Counter for paddle hits

    /**
     * Constructor
     */
    public SafariBall()
    {
        init();
    }

    /**
     * Create the ball image
     */
    private void createImage()
    {
        GreenfootImage ballImage = new GreenfootImage(BALL_SIZE,BALL_SIZE);
        ballImage.setColor(Color.BLACK);
        ballImage.fillOval(0, 0, BALL_SIZE, BALL_SIZE);
        setImage(ballImage);
    }

    /**
     * Act method
     */
    public void act() 
    {
        if (delay > 0)
        {
            delay--;
        }
        else
        {
            move(speed);
            checkBounceOffWalls();
            checkBounceOffCeiling();
            checkRestart();
            bounceOffPaddle();
            collisionWithSelfMovingPaddle();
        }
    }    

    /**
     * Check if ball touches the sieds
     */
    private boolean isTouchingSides()
    {
        return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2);
    }

    /**
     * Check if ball touches the ceiling.
     */
    private boolean isTouchingCeiling()
    {
        return (getY() <= BALL_SIZE/2);
    }

    /**
     * Check if ball touches the floor.
     */
    private boolean isTouchingFloor()
    { 
        return (getY() >= getWorld().getHeight() - BALL_SIZE/2);
    }

    /**
     * Check to see if the ball should bounce off one of the walls.
     * If touching one of the walls, the ball is bouncing off.
     */
    private void checkBounceOffWalls()
    {
        if (isTouchingSides())
        {
            if (!hasBouncedHorizontally)
            {
                revertHorizontally();
                Greenfoot.playSound("WALLS - CEILING.wav");
            }
        }
        else
        {
            hasBouncedHorizontally = false;
        }
    }
    
    private void bounceOffPaddle()
    {
        if(isTouching(SafariPaddle.class))
        {
            if (! hasBouncedVertically)
            {
                revertVertically();
                Greenfoot.playSound("PaddlePlayer.wav");
                hitCount++; // Increment hit counter
                
                if (hitCount % HIT_THRESHOLD == 0)
                {
                    speed += SPEED_INCREMENT;
                    ((SafariPingWorld) getWorld()).increaseGameLevel();// Increase speed slightly
                }
            }
            else
            {
            hasBouncedVertically = false;
            }
    }
}
    
    private void collisionWithSelfMovingPaddle() 
    {
    SafariSelfMovingPaddle selfPaddle = (SafariSelfMovingPaddle) getOneIntersectingObject(SafariSelfMovingPaddle.class);

    if (selfPaddle != null) {
        // Ball moving upwards (negative vertical direction)
        if (getY() < selfPaddle.getY() && getRotation() > 180 && getRotation() < 360) {
            if (!hasBouncedVertically) {
                revertVertically(); // Bounce the ball downward
                Greenfoot.playSound("PADDLE.wav");
                hasBouncedVertically = true;
            }
        }
    } else {
        hasBouncedVertically = false; // Reset bounce flag when not colliding
    }
    }


    private void checkBounceOffCeiling()
    {
        if (isTouchingCeiling())
        {
            if (!hasBouncedVertically)
            {
                revertVertically();
                Greenfoot.playSound("WALLS - CEILING.wav");
            }
        }
        else
        {
            hasBouncedVertically = false;
        }
    }

    /**
     * Check to see if the ball should be restarted.
     * If touching the floor the ball is restarted in initial position and speed.
     */
    private void checkRestart()
    {
        if (isTouchingFloor())
        {
            init();
            Greenfoot.playSound("GAMEOVER.wav");
            ((SafariPingWorld) getWorld()).resetGameLevel();
            setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
        }
    }

    /**
     * Bounces the ball back from a vertical surface.
     */
    private void revertHorizontally()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((180 - getRotation()+ randomness + 360) % 360);
        hasBouncedHorizontally = true;
    }

    /**
     * Bounces the ball back from a horizontal surface.
     */
    private void revertVertically()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((360 - getRotation()+ randomness + 360) % 360);
        hasBouncedVertically = true;
    }

    /**
     * Initialize the ball settings.
     */
    private void init()
    {
        speed = 2;
        delay = DELAY_TIME;
        hasBouncedHorizontally = false;
        hasBouncedVertically = false;
        hitCount = 0; // Reset hit counter
        setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2);
    }
}
