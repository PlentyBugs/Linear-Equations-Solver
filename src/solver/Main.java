package solver;

import solver.Commands.Command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static Deque<Command> commandHistory = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        String fileInput = "test2.txt";
        String fileOutput = "output.txt";
        if (args.length == 4) {
            if (args[0].equals("-in")) {
                fileInput = args[1];
                fileOutput = args[3];
            } else {
                fileInput = args[3];
                fileOutput = args[1];
            }
        }

        Scanner sc = new Scanner(new File(fileInput));

        int n = sc.nextInt();
        int m = sc.nextInt();

        Matrix matrix = new Matrix();
        for (int i = 0; i < m; i++) {
            Row row = new Row();
            for (int j = 0; j < n + 1; j++) {
                String val = sc.next();
                double real;
                double image;
                String[] values = new String[2];
                Matcher matcher = Pattern.compile("(-?\\d.?\\d*+)(-\\d.?\\d*i)").matcher(val);
                if (val.contains("+")) {
                    values = val.split("\\+");
                } else if (matcher.find()) {
                    values[0] = matcher.group(1);
                    values[1] = matcher.group(2);
                } else if (val.contains("i")) {
                    values[0] = "0";
                    values[1] = val;
                } else {
                    values[0] = val;
                    values[1] = "0i";
                }
                if ("i".equals(values[1])) {
                    values[1] = "1i";
                } else if ("-i".equals(values[1])) {
                    values[1] = "-1i";
                }
                real = Double.parseDouble(values[0]);
                image = Double.parseDouble(values[1].substring(0, values[1].length()-1));
                row.add(new Complex(real, image));
            }
            matrix.addRow(row);
        }
        sc.close();

        Complex[] output =  LinearEquation.solve(matrix);

        FileWriter writer = new FileWriter(new File(fileOutput));
        if (output == null) {
            writer.write(matrix.getVerdict());
        } else {
            for (Complex d : output) {
                writer.write(d + "\n");
            }
        }
        writer.flush();
        writer.close();
    }
}
