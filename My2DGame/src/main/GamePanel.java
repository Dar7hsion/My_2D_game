package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable
{
	//SCREEN SETTINGS
	final int originalTileSize = 16; // 16X16 tile
	final int scale = 3; //scales the tile for modern monitors 
	
	public int tileSize = originalTileSize * scale; //40X40 tile
	public final int maxScreenCol = 16; // Height in units
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int maxScreenRow = 12; // width in units
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	//public final int worldWidth = tileSize * maxWorldCol;
	//public final int worldHeight = tileSize * maxWorldRow;
	
	//FPS
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(); //Instantiation
	Sound Music = new Sound();
	Sound SE = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread; //repeats a process// ****
	
	
	public Player player = new Player(this, keyH);//passes its self in and the key handler class in
	public SuperObject obj[] = new SuperObject[2000];
	
	
//	//set player's default position
//	int playerX = 100;
//	int playerY = 100;
//	int playerSpeed = 4;
	
	public GamePanel() //constructor
	{
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));//constructs window
		this.setBackground(Color.black); //sets background color
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setUpGame()
	{
		aSetter.setObjects();
		playMusic(0);
	}
	
	public void startGameThread() //***
	{
		gameThread = new Thread(this); //Instantiation of a thread, "this" is GamePanel, this is how we refresh the screen
		gameThread.start();//starts thread
	}

	@Override
//	public void run() //core of game
//	{
//		double drawInterval = 1000000000/FPS; //0.01666 sec
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		while(gameThread != null)
//		{
//			//System.out.println("hello world");
//			//long currentTime = System.nanoTime();
//			//System.out.println("current times: " + currentTime);
//			//long currentTime2 = System.currentTimeMillis();1000/1
//			// 1 update info such as character position
//			// 2 draw and update the screen
//			
//			
//			update();
//			
//			repaint();
//			
//			
//			
//			try{
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000;
//				
//				if(remainingTime < 0)
//				{
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long) remainingTime);
//				
//				nextDrawTime += drawInterval;
//				
//			} catch (InterruptedException e) 
//			{
//				
//				e.printStackTrace();
//			}
//			
//			
//			
//		}
//		
//	}
	public void run()
	{
		double drawInterval = 1000000000/FPS; 
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;

		while(gameThread != null)
		{
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1)
			{
			update();
			repaint();
			delta--;
			drawCount++;
			}
			
			if(timer >= 1000000000L)//***literals are ints by default, L changes it to long
			{
				System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			}
		
			
		}
	}
	
	public void update() 
	{
//		
//		if(keyH.upPressed == true)
//		{
//			playerY -= playerSpeed; // same as saying playerY = playerY - playerSpeed;
//		}
//		else if(keyH.downPressed == true)
//		{
//			playerY +=playerSpeed;
//		}
//		else if(keyH.leftPressed ==true)
//		{
//			playerX -=playerSpeed;
//		}
//		else if(keyH.rightPressed ==true)
//		{
//			playerX +=playerSpeed;
//		}
//		
		
		player.update();
	}
	
	public void paintComponent(Graphics g)//this is where you draw
	{
		super.paintComponent(g);
		
		Graphics g2 = (Graphics2D)g; // look this up*** extra functions
		
		//Debug
		long drawStart = 0;
		if(keyH.checkDrawTime == true)
		{
			drawStart = System.nanoTime();
		}
		
		
//		
//		g2.setColor(Color.white);
//		
//		g2.fillRect(playerX, playerY, tileSize, tileSize); //player
		
		//TILE
		tileM.draw((Graphics2D) g2);
		
		//OBECT
		for(int i = 0; i < obj.length; i++)
		{
			if(obj[i] != null)
			{
				obj[i].draw((Graphics2D)g2, this);
			}
		}
		
		//PLAYER
		player.draw((Graphics2D)g2);
		
		
		//UI
		ui.draw((Graphics2D)g2);
		
		//DEBUG
		if(keyH.checkDrawTime == true)
		{
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time:" + passed , 10, 400);
			System.out.println("Draw Time:" + passed);
		}
		g2.dispose();
	}
	
	public void  playMusic(int i)
	{
		Music.setFile(i);
		Music.play();
		Music.loop();
	}
	public void stopMusic()
	{
		Music.stop();
	}
	public void playSE(int i)
	{
		SE.setFile(i);
		SE.play();
	}
	
	
}
