import java.io.*;
import java.util.Arrays;

public class q3_Yuan_Bo {
    public static void main(String[] args) throws IOException {
        File file = new File("test_1.txt");
        BufferedReader input = new BufferedReader(new FileReader(file));
        String firstLine = input.readLine();
        String index = input.readLine();
        int inputInt = Integer.valueOf(index);

        int result = LF_mapping(firstLine, inputInt);
        FileWriter writer = new FileWriter("output_q3_Yuan_Bo.txt");
        writer.write(String.valueOf(result));
        writer.close();
    }
    public static int LF_mapping(String input, int index) {
        int line = index;

        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        String sortedString = new String(chars);
        String copy = input;

        char currentChar = input.charAt(line);
        int nextLine = -1;

        int counter = -1;
        for (int k = 0; k <= line; k++) {
            if (input.charAt(k) == currentChar) {
                counter++;
            }
        }
        for (int j = 0; j < sortedString.length(); j++) {
            if (sortedString.charAt(j) == currentChar) {
                if (counter == 0) {
                    nextLine = j;
                    counter--;
                }
                else {
                    counter--;
                }
            }
        }
        System.out.println(sortedString);
        System.out.println(input);

        return nextLine;
    }
}
