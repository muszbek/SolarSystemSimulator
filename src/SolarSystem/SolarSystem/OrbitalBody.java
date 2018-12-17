package SolarSystem;

import Jama.Matrix;

public class OrbitalBody extends CelestialBody {
	
	double apoapsis;
	double periapsis;
	double eccentricity;
	double orbitalperiod;
	double areaoforbit;	//calculated
	double semimajoraxis;	//calculated
	double semiminoraxis;	//calculated
	double semilatusrectum;	//calculated
	double inclination;
	double argumentofperiapsis;
	double anomaly;	//variable
	String orbits;
	boolean enabled;
	//double radius;	//variable
	
	public OrbitalBody(String [][] inputSource, int number)	//constructor needs some more work - other properties shall be read from external file
	{
		name=inputSource[number][0];
		apoapsis=Double.parseDouble(inputSource[number][3]);
		periapsis=Double.parseDouble(inputSource[number][2]);
		eccentricity=Double.parseDouble(inputSource[number][4]);
		orbitalperiod=Double.parseDouble(inputSource[number][6]);
		inclination=Double.parseDouble(inputSource[number][5]);
		argumentofperiapsis=Double.parseDouble(inputSource[number][10]);
		semimajoraxis=periapsis/(1-eccentricity);
		semilatusrectum=periapsis*(1+eccentricity);
		semiminoraxis=Math.sqrt(semimajoraxis*semilatusrectum);
		areaoforbit=semimajoraxis*semiminoraxis*Math.PI;
		enabled=false;
		orbits=inputSource[number][1];
	}
	
	Matrix GivePath (double angle)
	{
		Matrix place=new Matrix (3,1);
		if (angle>2*Math.PI)
			angle-=2*Math.PI;
		double range=semilatusrectum/(1+eccentricity*Math.cos(angle));
		place.set(0, 0, range*Math.cos(angle)); //initial coordinates -> polar to cartesian
		place.set(1, 0, range*Math.sin(angle));
		
		double [][] rotator1array = {{1,0,0},
				{0,Math.cos(inclination),-Math.sin(inclination)},
				{0,Math.sin(inclination),Math.cos(inclination)}};
		double [][] rotator2array = {{Math.cos(argumentofperiapsis),-Math.sin(argumentofperiapsis),0},
				{Math.sin(argumentofperiapsis),Math.cos(argumentofperiapsis),0},{0,0,1}};
		Matrix rotator1=new Matrix (rotator1array);
		Matrix rotator2=new Matrix (rotator2array);	//creation of rotator matrices
		place=rotator2.times(rotator1.times(place));	//rotation with path defining angles
		return place;
	}
	//this function gives the exact location of a celestial body with respect to the body it orbits
	//we need its angle of anomaly
	//it returns with the spatial coordinates of the celestial body, where the orbited object is the origo
	
	double GiveMovement (double angle, double timedifference)
	{
		double range=semilatusrectum/(1+eccentricity*Math.cos(angle));
		double angledifference=(2*areaoforbit/(orbitalperiod*range*range))*timedifference;
		//System.out.println(angledifference);
		return angle+angledifference;
	}
	//this function gives the new angle of anomaly for a celestial body after a definite time has passed
	//from this, we can calculate the new position of the body
	//we need its angle of anomaly and the time interval between formal and new positions
	
	double RandomPlace ()
	{
		double randomangle=Math.random()*2*Math.PI;
		return randomangle;
	}
	//gives a random angle of anomaly, useful at the start of display
}