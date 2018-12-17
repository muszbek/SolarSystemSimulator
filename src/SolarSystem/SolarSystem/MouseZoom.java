package SolarSystem;

import java.awt.event.*;

public class MouseZoom implements MouseWheelListener {

	public void mouseWheelMoved(MouseWheelEvent e) {
		GraphicModify.zoomRotation+=e.getWheelRotation();
		
		//System.out.println(GraphicModify.zoomRotation);
		//for testing, it works
	}

}
