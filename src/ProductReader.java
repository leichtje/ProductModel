import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {
    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec;
        ArrayList<String> lines = new ArrayList<>();

        String headerOne = "ID#";
        String headerTwo = "Product Name";
        String headerThree = "Description";
        String headerFour = "Cost";
        String divisor = "================================================================================";

        String record = "";
        String id;
        String name;
        String description;
        double cost;

        final int FIELDS_LENGTH = 4;


        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));


                int line = 0;
                while(reader.ready())
                {
                    rec = reader.readLine();
                    lines.add(rec);
                    line++;

                    System.out.printf("\nLine %4d %-60s ", line, rec);
                }
                reader.close();
                System.out.println("\n\nData file read!");

                System.out.printf("%-8s%-25s%-35s%-6s", headerOne, headerTwo, headerThree, headerFour);
                System.out.printf("\n");
                System.out.println(divisor);




                String[] fields;
                for(String l:lines)
                {
                    fields = l.split(",");

                    if(fields.length == FIELDS_LENGTH)
                    {
                        id        = fields[0].trim();
                        name = fields[1].trim();
                        description  = fields[2].trim();
                        cost       = Double.parseDouble(fields[3].trim());
                        System.out.printf("\n%-8s%-25s%-35s%-6.2f", id, name, description, cost);
                    }
                    else
                    {
                        System.out.println("Found a record that may be corrupt: ");
                        System.out.println(l);
                    }
                }

            }
            else
            {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File not found!!!");
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }


    }
}
