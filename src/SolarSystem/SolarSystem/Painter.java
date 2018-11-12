package SolarSystem;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import Jama.Matrix;
import java.awt.image.BufferedImage;

public class Painter extends JPanel{
	
	public static int labelsDraw=0;
	public static Color centerColor;
	static int radius=10;
	static int centerRadius=20;
	
	protected void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		DrawEverything(g);
		
	}
	public int [] DrawOrbital (OrbitalBody drawable)
	{
		drawable.anomaly=drawable.GiveMovement(drawable.anomaly, GraphicModify.SetTimeDifference());
		drawable.location=drawable.GivePath(drawable.anomaly);
		int [] coords = GraphicModify.Projector(GraphicModify.Zoom(GraphicModify.Rotate(drawable.location)));
		coords[0]=coords[0]+this.getBounds().width/2-radius/2;
		coords[1]=coords[1]+this.getBounds().height/2-radius/2;
		return coords;
		
		//System.out.println(GraphicModify.SetTimeDifference());
		/*for (int i=0; i<2; i++)
		{
			System.out.println(coords[i]);
		}*/
		//for testing
	}
	public void DrawCoordinates ()
	{
		int [][] coordinatesArray = new int [Main.presentArray.length][2];
		String [] myStringArray = new String [Main.presentArray.length];
		for (int c=0; c<Main.presentArray.length; c++)
		{
			int [] presentCoordinates = DrawOrbital (Main.presentArray[c]);
			coordinatesArray[c][0]=presentCoordinates[0];
			coordinatesArray[c][1]=presentCoordinates[1];
			myStringArray[c]=Main.presentArray[c].name;
		}
		Main.paintArray=coordinatesArray;
		Main.labelArray=myStringArray;
	}
	public void DrawEverything (Graphics mygraphics)
	{
			mygraphics.setColor(Color.WHITE);
			for (int d=0; d<Main.paintArray.length; d++)
			{
				mygraphics.fillOval(Main.paintArray[d][0], Main.paintArray[d][1], radius, radius);
			}
			
			mygraphics.setColor(centerColor);
			mygraphics.fillOval(this.getBounds().width/2-centerRadius/2, this.getBounds().height/2-centerRadius/2, centerRadius, centerRadius);
			
		if (labelsDraw==1)
		{
			
			mygraphics.setColor(Color.PINK);
			mygraphics.drawString(CenterChooser.center, this.getBounds().width/2-centerRadius/2, this.getBounds().height/2-centerRadius/2);
			for (int l=0; l<Main.paintArray.length; l++)
			{
				mygraphics.drawString(Main.labelArray[l], Main.paintArray[l][0], Main.paintArray[l][1]);
			}
		}
	}
	

}
