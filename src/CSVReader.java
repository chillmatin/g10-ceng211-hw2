import java.io.*;

public abstract class CSVReader {


    public String csvToString(String filePath){

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = null;
            StringBuilder fileString = new StringBuilder();

            do {
                line = br.readLine();
                if (line != null){ fileString.append(line + "\n");}
            } while (line != null);

            br.close();
            return fileString.toString();

        } catch (Exception e){
            System.out.println("Error: FileIO failed.");
            System.exit(0);
            return null;
        }

    }

   public String[][] csvToArray(String filePath){
        String fileString = csvToString(filePath);
        String[] arrayOfLines = fileString.split("\n");
        String[][] csvArray = new String[arrayOfLines.length][];

        for (int i = 0; i < arrayOfLines.length; i++){
            csvArray[i] = arrayOfLines[i].split(",");
        }

        return csvArray;

   }

}


