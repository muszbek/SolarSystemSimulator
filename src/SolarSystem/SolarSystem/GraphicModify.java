package SolarSystem;

import Jama.Matrix;

public class GraphicModify {
	public static int horizontalRotate;
	public static int verticalRotate;
	static double scaleRotateAngle = 0.02 ;	//some coefficient set by us - one pixel drag means how many radians rotation
	public static double horizontalAngle;
	public static double verticalAngle;
	
	public static Matrix Rotate (Matrix place)
	{
		horizontalAngle+=horizontalRotate*scaleRotateAngle;
		verticalAngle+=verticalRotate*scaleRotateAngle;
		if (horizontalAngle>2*Math.PI)
			horizontalAngle-=2*Math.PI;
		if (verticalAngle>2*Math.PI)
			verticalAngle-=Math.PI;
		MouseRotation.xDifference=0;
		MouseRotation.yDifference=0;
		horizontalRotate=0;
		verticalRotate=0;	//zeroing out the difference saving variables after calculation
		
		double [][] rotator2array = {{1,0,0},
				{0,Math.cos(verticalAngle),-Math.sin(verticalAngle)},
				{0,Math.sin(verticalAngle),Math.cos(verticalAngle)}};
		double [][] rotator1array = {{Math.cos(horizontalAngle),-Math.sin(horizontalAngle),0},
				{Math.sin(horizontalAngle),Math.cos(horizontalAngle),0},{0,0,1}};
		Matrix rotator1=new Matrix (rotator1array);
		Matrix rotator2=new Matrix (rotator2array);	//creation of rotator matrices
		place=rotator2.times(rotator1.times(place));
		return place;
	}
	//needs a vector of coordinates
	//returns the same vector after rotation after calculating the angle of rotation
	
	public static int zoomRotation;
	static double zoomScale = 1.2 ;	//some number set by us between 0 and 1 - one rotation means how many times size increase
	
	static Matrix Zoom (Matrix place)
	{
		double sizer=Math.pow(zoomScale, zoomRotation);
		place=place.times(sizer);
		return place;
	}
	//needs a vector of coordinates
	//returns the same vector after zooming it
	
	static double sizeScale = 0.000001 ; //a 0<x<<<1 number, sizing down the thousends of kilometers to pixels
	static double projectCoeff = 0.2 ; //a 0<x<1 number, projection coefficient
	static double [][] projectionArray={{sizeScale,0,0},{0,sizeScale*projectCoeff,sizeScale}};
	static Matrix projector=new Matrix (projectionArray);
	
	static int[] Projector (Matrix place)
	{
		place=projector.times(place);
		double xCoord=place.get(0, 0);
		double yCoord=place.get(1, 0);
		int coordinates []={(int)xCoord,(int)yCoord};
		return coordinates;
	}
	//needs a vector of coordinates (3D)
	//creates a 2D projection, converts into paintable integers
	
	public static double timeScroll=-80;
	
	static double SetTimeDifference ()
	{
		double timeIncrement;
		if (timeScroll==-80)
			timeIncrement=0;
		else
			timeIncrement=Math.exp(timeScroll/10);
		return timeIncrement;
	}
	//reads the timescroll and sets the timeincrement according to it

}
