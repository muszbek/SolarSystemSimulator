package SolarSystem;

import java.io.IOException;
import java.awt.EventQueue;
import java.util.Timer;

public class Main {
	public static OrbitalBody [] solarArray =null;
	public static OrbitalBody [] presentArray;
	public static int [][] paintArray;
	public static String [] labelArray;
	public static void main(String[] args) throws IOException {
		
		
	    ReadExcel reader = new ReadExcel();

	    reader.chooseInputFile();
	    String [][] dataMatrix = reader.read();
	    solarArray = new OrbitalBody [dataMatrix.length];
	    for (int i=0; i<solarArray.length; i++)
	    {
	    	solarArray[i]= new OrbitalBody(dataMatrix, i);
	    	//System.out.println(solarArray[i].name);
	    }   
	    presentArray = CenterChooser.chooseCenter(0,solarArray);
	    paintArray=new int [presentArray.length][2];
	    labelArray=new String [paintArray.length];
	    
	    gui window = new gui();
		window.frmSolarsystemsimulator.setVisible(true);
		
        Timer timer = new Timer();
        OrbitTimer ot = new OrbitTimer(window, window.layeredPane.getGraphics());
        timer.scheduleAtFixedRate(ot, 0, 10);
	}
	
}

