package SolarSystem;

import java.awt.Color;

public class CenterChooser {
	
	public static String center;
	
	public static OrbitalBody[] chooseCenter(int selnum, OrbitalBody [] sA){

		switch (selnum) {
		case 0:  center = "sun";
		Painter.centerColor=Color.ORANGE;
				 break;
	    case 1:  center = "mercury";
	    Painter.centerColor=Color.DARK_GRAY;
	             break;
	    case 2:  center = "venus";
	    Painter.centerColor=Color.YELLOW;
	             break;
	    case 3:  center = "earth";
	    Painter.centerColor=Color.BLUE;
	             break;
	    case 4:  center = "mars";
	    Painter.centerColor=Color.RED;
	             break;
	    case 5:  center = "jupiter";
	    Painter.centerColor=Color.ORANGE;
	             break;
	    case 6:  center = "saturn";
	    Painter.centerColor=Color.YELLOW;
	             break;
	    case 7:  center = "uranus";
	    Painter.centerColor=Color.CYAN;
	             break;
	    case 8:  center = "neptune";
	    Painter.centerColor=Color.BLUE;
	             break;
	    case 9:  center = "pluto";
	    Painter.centerColor=Color.LIGHT_GRAY;
	             break;
		}
		int num=0;
		for (int z=0; z<sA.length;z++){
			if (center.equalsIgnoreCase(sA[z].orbits)){
				num++;
		    }
		}
		OrbitalBody [] printArray = new OrbitalBody [num];
		int f=0;
		for (int h=0; h<sA.length;h++){
			if (center.equalsIgnoreCase(sA[h].orbits)){
				printArray[f]=sA[h];
				f++;
			}
		}
		for (int r=0; r<printArray.length; r++)
		{
			printArray[r].anomaly=printArray[r].RandomPlace();	//whenever there is a new selection, we randomize the position of its contents
		}
		//for testin prourposes, print the elements
		//for (int b=0;b<printArray.length;b++){
	   // 	System.out.println(printArray[b].name);
	    //}
		//end of testing
		return printArray;
	}

}
