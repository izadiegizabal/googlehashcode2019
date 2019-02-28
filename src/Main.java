import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private static String filename = "a_example";

    public static void main(String[] args) {
        int total;
        ArrayList<Slide> unordered = new ArrayList<>();
        ArrayList<Photo> vertical = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("out/production/googlehashcode2019/" + filename + ".txt"))) {
            String line;
            line = br.readLine();

            total = Integer.parseInt(line);

            int indexRead = 0;
            while ((line = br.readLine()) != null) {
                String[] params = line.split("\\s+");
                ArrayList<String> tags = new ArrayList<>(Arrays.asList(params).subList(2, 2 + Integer.parseInt(params[1])));
                if (params[0].equals("H")) {
                    Photo photo = new Photo(indexRead, false, tags);
                    ArrayList<Photo> photos = new ArrayList<>();
                    photos.add(photo);
                    unordered.add(new Slide(photos));
                } else {
                    vertical.add(new Photo(indexRead, true, tags));
                }

                indexRead++;
            }
            print(total);
            print(unordered);
            print(vertical);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeToOutput(String outputString) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("out/production/googlehashcode2019/output/" + filename + ".out.txt"));
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
