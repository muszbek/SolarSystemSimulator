
package SolarSystem;

import Jama.Matrix;

public class CelestialBody {
	Matrix location;
	String name;
	
	CelestialBody ()	//this constructor will only be used on the sun
	{
		location=new Matrix (3,1);
	}
}
