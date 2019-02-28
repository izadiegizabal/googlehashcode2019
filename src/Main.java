import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    // private static String filename = "a_example";
     // private static String filename = "b_lovely_landscapes";
    // private static String filename = "c_memorable_moments";
     private static String filename = "d_pet_pictures";
    // private static String filename = "e_shiny_selfies";

    public static void main(String[] args) {
        int total;
        ArrayList<Slide> unordered = new ArrayList<>();
        ArrayList<Slide> ordered = new ArrayList<>();
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        unordered.addAll(pairVerticals(vertical));
        saveResult(orderUnordered(unordered));
    }

    private static void saveResult(ArrayList<Slide> orderedSlides) {
        StringBuilder output = new StringBuilder();
        output.append(orderedSlides.size()).append("\n");
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

    private static ArrayList<Slide> pairVerticals(ArrayList<Photo> verticals) {
        ArrayList<Slide> unordered = new ArrayList<>();
        while (!verticals.isEmpty()) {
            int max = Integer.MAX_VALUE;
            int indexMin = -1;

            for (int i = 1; i < verticals.size(); i++) {

                int diff = checkDiff(verticals.get(0).getTags(), verticals.get(i).getTags());
                if (diff == 0) {
                    // se pueden juntar porque son diferentes
                    indexMin = i;
                    break;
                } else if (max > diff) {
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

    private static ArrayList<Slide> orderUnordered(ArrayList<Slide> unordered) {

        ArrayList<Slide> ordered = new ArrayList<>();
        ordered.add(unordered.get(0));
        unordered.remove(0);
        while (!unordered.isEmpty()) {
            int interest = Integer.MIN_VALUE;
            int indexMax = -1;

            for (int i = 0; i < unordered.size(); i++) {
                int minInterest = checkMin(ordered.get(ordered.size() - 1), unordered.get(i));
                if (interest < minInterest) {
                    interest = minInterest;
                    indexMax = i;
                }
                if ((interest >= ordered.get(ordered.size() - 1).getTags().size() -1) || interest > 5 ) {
                    break;
                }
            }
            // se pueden juntar porque son diferentes
            ordered.add(unordered.get(indexMax));
            unordered.remove(indexMax);
            System.out.println(unordered.size());
        }
        return ordered;

    }

    private static int checkDiff(ArrayList<String> one, ArrayList<String> two) {
        ArrayList<String> aux = new ArrayList<>(one);
        aux.removeAll(two);
        return one.size() - aux.size();
    }

    private static int checkMin(Slide one, Slide two) {
        int[] numbers = new int[3];
        ArrayList<String> aux = new ArrayList<>(one.getTags());
        aux.removeAll(two.getTags());
        // common tags
        numbers[0] = one.getTags().size() - aux.size();
        // in one but not in two
        numbers[1] = aux.size();
        // in two but not in one
        numbers[2] = two.getTags().size() - numbers[0];

        Arrays.sort(numbers);
        return numbers[0];
    }

    private static void print(Object object) {
        System.out.println(object);
    }

}
