import java.io.*;

public class Main {
    private static String filename = "b_small";

    public static void main(String[] args) {
        boolean[][] map = new boolean[0][];
        int R, C, L, H;

        try (BufferedReader br = new BufferedReader(new FileReader("out/production/googlehashcode2019/" + filename + ".in"))) {
            String line;
            line = br.readLine();

            String[] params = line.split("\\s+");

            R = Integer.parseInt(params[0]);
            C = Integer.parseInt(params[1]);
            L = Integer.parseInt(params[2]);
            H = Integer.parseInt(params[3]);

            map = new boolean[R][C];

            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    map[lineCount][i] = line.charAt(i) == 'T'; // TOMATO = true, MUSHROOM = false
                }
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder output = new StringBuilder();
        for (boolean[] booleans : map) {
            for (boolean aBoolean : booleans) {
                output.append(aBoolean).append(" ");
            }
            output.append("\n");
        }
        writeToOutput(output.toString());

        String slices = getSlices(map);
        writeToOutput(slices);
    }

    private static String getSlices(boolean[][] map) {
        StringBuilder slices = new StringBuilder();

        for (boolean[] rows : map) {
            for (int j = 0; j < rows.length; j++) {

            }
        }

        return slices.toString();
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
}
