import java.nio.file.*;
import java.util.List;
import java.io.IOException;
import java.nio.charset.Charset;

public class Main {
    // Attributes
    private static char[][] arrPeta;

    public static void initPeta(String filePath) {
        try {
            final List<String> lines = Files.readAllLines(Paths.get(filePath), Charset.defaultCharset());
            int rows = lines.size(), columns = lines.get(0).length();   
            
            arrPeta = new char[rows][columns];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    arrPeta[i][j] = lines.get(i).charAt(j);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void printPeta() {
        for (int i = -1; i <= arrPeta.length; i++) {
            for (int j = -1; j <= arrPeta[0].length; j++) {
                if (i == -1 || j == -1 || i == arrPeta.length || j == arrPeta[0].length)
                    System.out.print("#");
                else
                    System.out.print(arrPeta[i][j]);
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        initPeta("input/contoh.txt");
        printPeta();
    }
}
