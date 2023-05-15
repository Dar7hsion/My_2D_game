package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager 
{
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp)
	{
		this.gp = gp;
		
		tile = new Tile[10];
		
		mapTileNum = new int[gp.maxWorldCol] [gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/world01.txt");
	}
	
	public void getTileImage()
	{
//		try
//		{
			setUp(0, "Grass", false);
			setUp(1, "Wall", true);
			setUp(2, "Water", true);
			setUp(3, "Earth", false);
			setUp(4, "Tree", true);
			setUp(5, "Sand", false);
//			
//			tile[1] = new Tile();
//			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Wall.png"));
//			tile[1].collision = true;
//			
//			tile[2] = new Tile();
//			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water.png"));
//			tile[2].collision = true;
//			
//			tile[3] = new Tile();
//			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Earth.png"));
//			
//			tile[4] = new Tile();
//			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree.png"));
//			tile[4].collision = true;
//			
//			tile[5] = new Tile();
//			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Sand.png"));
//			
//		}
//		catch(IOException e) 
//		{
//			e.printStackTrace();
//		}
	}

	public void setUp(int index, String imageName, boolean collision)
	{
		UtilityTool uTool = new UtilityTool();
		
		try
		{
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName +".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath)
	{
		try
		{
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0; 
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow)
			{
				String line = br.readLine();
				
				while(col < gp.maxWorldCol)
				{
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
					
				}
				if(col == gp.maxWorldCol)
				{
					col = 0;
					row++;
				}
			}
			br.close();
		}
		catch(Exception e) 
		{
			
		}
	}
	
	public void draw(Graphics2D g2)
	{
//		g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);
		
		int worldCol = 0;
		int worldRow = 0;

		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
		{
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
			{
			g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			
			worldCol++;

			
			if(worldCol == gp.maxWorldCol)
			{
				worldCol = 0;

				worldRow++;

			}
		}
	}
	
}
