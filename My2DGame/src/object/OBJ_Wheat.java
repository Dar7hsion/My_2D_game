package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Wheat extends SuperObject
{
	GamePanel gp;
	
	public OBJ_Wheat(GamePanel gp)
	{
		
		this.gp = gp;
		name = "Wheat";
		
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("/objects/wheat.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
}
