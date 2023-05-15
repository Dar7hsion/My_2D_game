package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Wheat;
import object.OBJ_key;

public class AssetSetter 
{
	GamePanel gp;
	
	AssetSetter(GamePanel gp)
	{
		this.gp = gp;
	}
	
	public void setObjects()
	{
		int Wheat = 0;
		int k1 = 1;
		int k2 = 2;
		int k3 = 3;
		int k4 = 4;
		int k5 = 5;
		int wheatLimitCol = 10;
		int wheatLimitRow = 10;
		
		
//		for(int i = 0; i < gp.maxWorldCol-wheatLimitCol; i++)
//		{
//			for(int j =0; j <gp.maxWorldRow-wheatLimitRow; j++)
//			{
//				gp.obj[Wheat]= new OBJ_Wheat();
//				gp.obj[Wheat].worldX = (5+j)*gp.tileSize;
//				gp.obj[Wheat].worldY = (5+i)*gp.tileSize;
//				Wheat++;
//				
//			}
//		}
		
		gp.obj[k1]= new OBJ_key(gp);
		gp.obj[k1].worldX = 4*gp.tileSize;
		gp.obj[k1].worldY = 4*gp.tileSize;
		
		gp.obj[k2]= new OBJ_key(gp);
		gp.obj[k2].worldX = 4*gp.tileSize;
		gp.obj[k2].worldY = 8*gp.tileSize;
		
		gp.obj[k3]= new OBJ_Door(gp);
		gp.obj[k3].worldX = 4*gp.tileSize;
		gp.obj[k3].worldY = 16*gp.tileSize;
		
		gp.obj[k4]= new OBJ_Boots(gp);
		gp.obj[k4].worldX = 4*gp.tileSize;
		gp.obj[k4].worldY = 20*gp.tileSize;
		
		gp.obj[k5]= new OBJ_Chest(gp);
		gp.obj[k5].worldX = 4*gp.tileSize;
		gp.obj[k5].worldY = 24*gp.tileSize;
	

	}
}
