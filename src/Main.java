import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private static String filename = "b_small";

    public static void main(String[] args) {
        int total;
        ArrayList<Slide> unordered = new ArrayList<Slide>();
        ArrayList<Photo> vertical = new ArrayList<Photo>();

        try (BufferedReader br = new BufferedReader(new FileReader("out/production/googlehashcode2019/" + filename + ".in"))) {
            String line;
            line = br.readLine();

            total = Integer.parseInt(line);

            int indexRead = -1;
            while ((line = br.readLine()) != null) {
                String [] params = line.split("\\s+");
                ArrayList<String> tags = new ArrayList<>(Arrays.asList(params).subList(0, Integer.parseInt(params[1])));
                if (params[0].equals("H")){
                    unordered.add(new Slide(new ArrayList<Photo>(new Photo(indexRead, false, params))));
                } else {
                    vertical.add(new Photo(indexRead, true, params));
                }

                indexRead++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeToOutput(String outputString) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("out/production/googlehashcode2019/output/" + filename + ".out"));
            writer.write(outputString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void print(Object object) {
        System.out.println(object);
    }
}
