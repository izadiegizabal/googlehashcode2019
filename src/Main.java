import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static String filename = "a_example";

    public static void main(String[] args) {
        int total;
        ArrayList<Slide> unordered = new ArrayList<>();
        ArrayList<Photo> vertical = new ArrayList<>();
        ArrayList<Slide> orderedSlides = new ArrayList<>();

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

        pairVerticals(vertical).forEach(slide -> unordered.add(slide));

        saveResult(unordered);
    }

    private static void saveResult(ArrayList<Slide> orderedSlides) {
        StringBuilder output = new StringBuilder();
        output.append(orderedSlides.size() + "\n");
        orderedSlides.forEach((slide -> {
            AtomicInteger num = new AtomicInteger();
            slide.getPhotos().forEach(photo -> {
                output.append(photo.getIndex());
                if (num.get() == 0) {
                    output.append(" ");
                }
                num.getAndIncrement();
            });
            output.append("\n");
        }));


        writeToOutput(output.toString());
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

    private static ArrayList<Slide> pairVerticals(ArrayList<Photo> verticals){
        ArrayList<Slide> unordered = new ArrayList<>();
        while (!verticals.isEmpty()){
            int max = Integer.MAX_VALUE;
            int indexMin = -1;

            for (int i = 1; i < verticals.size(); i++) {

                int diff = checkDiff(verticals.get(0).getTags(), verticals.get(i).getTags());
                if(diff == 0){
                    // se pueden juntar porque son diferentes
                    indexMin = i;
                    break;
                } else if(max > diff) {
                    max = diff;
                    indexMin = i;
                }

            }
            // se pueden juntar porque son diferentes
            ArrayList<Photo> photos = new ArrayList<>();
            photos.add(verticals.get(indexMin));
            photos.add(verticals.get(0));
            verticals.remove(indexMin);
            verticals.remove(0);
            unordered.add(new Slide(photos));
        }
        return unordered;
    }

    private static int checkDiff(ArrayList<String> one, ArrayList<String> two){
        ArrayList<String> aux = new ArrayList<>(one);
        aux.removeAll(two);
        return one.size() - aux.size();
    }

    private static void print(Object object) {
        System.out.println(object);
    }

}
