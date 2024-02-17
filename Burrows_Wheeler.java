import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

public class q4_Yuan_Bo {
    public static void main(String[] args) throws IOException {
        File file = new File("test_1.txt");
        BufferedReader input = new BufferedReader(new FileReader(file));
        String firstLine = input.readLine();
        String secondLine = input.readLine();
        String[] secondLineArray = secondLine.trim().split("\\s+");
        String result = Burrows_Wheeler(firstLine, secondLineArray);
        FileWriter writer = new FileWriter("output_q4_Yuan_Bo.txt");
        writer.write(result);
        writer.close();
    }
    public static String Burrows_Wheeler(String input, String[] match) {
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        String sortedString = new String(chars);
        int[] output = new int[match.length];

        HashMap<Character, Integer> inputMap = new HashMap<>();
        HashMap<Character, Integer> sortedMap = new HashMap<>();
        String[] inputArray = new String[input.length()];
        String[] sortedArray = new String[input.length()];

        System.out.println(input.length() + ", " + sortedString.length());

        for (int i = 0; i < input.length(); i++) {
            if (!inputMap.containsKey(input.charAt(i))) {
                inputMap.put(input.charAt(i), 0);
                inputArray[i] = input.charAt(i) + "0";
            }
            else {
                inputMap.put(input.charAt(i) , inputMap.get(input.charAt(i)) + 1);
                inputArray[i] = input.charAt(i) + String.valueOf(inputMap.get(input.charAt(i)));
            }

            if (!sortedMap.containsKey(sortedString.charAt(i))) {
                sortedMap.put(sortedString.charAt(i), 0);
                sortedArray[i] = sortedString.charAt(i) + "0";

            }
            else {
                sortedMap.put(sortedString.charAt(i) , sortedMap.get(sortedString.charAt(i)) + 1);
                sortedArray[i] = sortedString.charAt(i) + String.valueOf(sortedMap.get(sortedString.charAt(i)));
            }
        }

        for (int i = 0; i < match.length; i++) {
            int[] resultArray = new int[input.length()];
            Arrays.fill(resultArray, 0);
            for (int k = 0; k < input.length(); k++) {
                if (inputArray[k].charAt(0) == match[i].charAt(0)) {
                    resultArray[k]++;
                }
            }
            for (int k = 1; k < match[i].length(); k++) {
                int[] nextArray = new int[input.length()];
                Arrays.fill(nextArray, 0);
                for (int x = 0; x < input.length(); x++) {
                    if (resultArray[x] == 1) {
                        char currentChar = sortedArray[x].charAt(0);
                        if (currentChar == match[i].charAt(k)) {
                            int index = 0;
                            while (!sortedArray[x].equals(inputArray[index])) {
                                index++;
                            }
                            nextArray[index] = 1;
                        }
                    }
                }
                for (int y = 0; y < resultArray.length; y++) {
                    resultArray[y] = nextArray[y];
                }
            }
            output[i] = IntStream.of(resultArray).sum();
        }

        String result = "";
        for (int i = 0; i < match.length; i++) {
            result += (output[i] + " ");
        }
        return result;
    }
}


