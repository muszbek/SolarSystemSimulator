package SolarSystem;

import java.awt.event.*;

public class MouseRotation implements MouseMotionListener, MouseListener {
	static int x;
	static int y;
	public static int xDifference;
	public static int yDifference;
	
	public void mousePressed (MouseEvent e)
	{
		x=e.getX();
		y=e.getY();
		//coordinates are saved
	}
	public void mouseDragged (MouseEvent e)
	{
		xDifference=e.getX()-x;
		yDifference=e.getY()-y;
		GraphicModify.horizontalRotate+=xDifference;
		GraphicModify.verticalRotate+=yDifference;
		//at a drag we can calculate a difference of past and present coordinates
		x=e.getX();
		y=e.getY();
		//save present coordinates
		
		//System.out.println(GraphicModify.horizontalRotate);
		//for testing, it works
	}
	public void mouseMoved (MouseEvent e)
	{//does nothing
	//needs to be implemented nevertheless, for the parent class to operate properly
		//same with those below
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
}
