import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args) {

        ArrayList<String> products = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        boolean done = false;
        String record = "";
        String id = "";
        String name = "";
        String description = "";
        double cost = 0;

        do {
            id = SafeInput.getNonZeroLenString(in, "Enter the ID of the product [6 Digits]" );
            name = SafeInput.getNonZeroLenString(in, "Enter the name of the product");
            description = SafeInput.getNonZeroLenString(in, "Enter a short description of the product");
            cost = SafeInput.getDouble(in,"Enter cost of the product");

            record = id + ", " + name + ", " + description + ", " + cost;
            products.add(record);

            done = SafeInput.getYNConfirm(in, "Do you have any more records to add?");

        }while(done);

        for( String f: products)
            System.out.println(f);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        try{
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file,CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String rec: products){
                writer.write(rec,0,rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data has been successfully written");

        }catch (IOException ex){
            ex.printStackTrace();
        }


    }


}
