package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_key extends SuperObject
{
	GamePanel gp;
	
	public OBJ_key(GamePanel gp)
	{
		this.gp = gp;
		name = "key";
		
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
