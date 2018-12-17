package SolarSystem;
import java.io.File;
import java.io.IOException;
import java.lang.String;

import jxl.Cell;
/*import jxl.CellType;*/
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import javax.swing.JFileChooser;

public class ReadExcel {

  private String inputFile;
  private File inputWorkbook;

  public void setInputFile(String inputFile) {
    this.inputFile = inputFile;
  }
  public void chooseInputFile () {
	  JFileChooser fileChooser = new JFileChooser();
	  if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	  {
		  inputWorkbook = fileChooser.getSelectedFile();
	  }
  }

  public String[][] read() throws IOException  {
    Workbook w;
    String[][] matrix = null;
    try {
      w = Workbook.getWorkbook(inputWorkbook);
      
      Sheet sheet = w.getSheet(0);
      
      matrix = new String[sheet.getRows()][sheet.getColumns()];

      for (int j = 0; j < sheet.getRows(); j++) {
        for (int i = 0; i < sheet.getColumns(); i++) {
          Cell cell = sheet.getCell(i, j);
          matrix[j][i] = cell.getContents().replace("," , ".");
          

        }//System.out.println();
      }
    } catch (BiffException e) {
      e.printStackTrace();
    }
    return matrix;
  }

} 