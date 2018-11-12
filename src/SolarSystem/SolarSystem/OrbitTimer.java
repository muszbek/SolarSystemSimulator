package SolarSystem;

import java.util.TimerTask;
import java.awt.Graphics;

public class OrbitTimer extends TimerTask {

	public gui mygui;
	Graphics g;
	
	OrbitTimer (gui window, Graphics mygraphics)
	{
		super();
		mygui=window;
		g=mygraphics;
	}
	public void run()
	{
		mygui.layeredPane.DrawCoordinates();
		mygui.layeredPane.update(g);
		//System.out.println(CenterChooser.center);
	}

}
