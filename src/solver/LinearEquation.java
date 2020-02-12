package solver;

import solver.Commands.*;

import java.util.List;

public class LinearEquation {

    public static Complex[] solve(Matrix matrix) {
        rowReduce(matrix);
        new CommandNormalize(matrix).execute();
        new CommandClear(matrix).execute();
        matrix.getRows().forEach(System.out::println);
        if (!checkOnCorrectness(matrix) || !checkOnInfinity(matrix)) {
            return null;
        }

        List<Row> rows = matrix.getRows();
        Complex[] result = new Complex[rows.size()];
        for (int i = rows.size() - 1; i >= 0; i--) {
            Row row = rows.get(i);
            result[i] = new Complex(row.get(row.size()-1)).divide(row.get(i));
            for (int j = i - 1; j >= 0; j--) {
                Row subRow = rows.get(j);
                subRow.set(subRow.size() - 1, new Complex(subRow.get(subRow.size() - 1)).subtract(new Complex(result[i]).multiply(subRow.get(i))));
                subRow.set(i, new Complex(0.0, 0.0));
            }
        }

        return normalizeOrder(result);
    }

    public static void rowReduce(Matrix matrix) {
        List<Row> rows = matrix.getRows();
        int count = 0;
        if (rows.size() > 0 && rows.get(0).size() > 0) {
            for (int i = 0; i < rows.size() - 1; i++) {
                for (int j = i + 1; j < rows.size(); j++) {
                    new CommandNormalize(matrix).execute();
                    System.out.println("\n\nNormalization: ");
                    matrix.getRows().forEach(System.out::println);
                    Row rowA = rows.get(i);
                    Row rowB = rows.get(j);
                    Complex firstA = rowA.get(i);
                    Complex firstB = rowB.get(i);
                    if (!firstA.isZero() && !firstB.isZero()) {
                        count += 1;
                        System.out.println("\nMultiply and subtract === " + firstA + " === " + firstB + " === " + i + " === " + j);
                        new CommandMultiplyByValue(rowB, new Complex(firstA).divide(firstB)).execute();
                        new CommandSubtract(rowB, rowA).execute();
                        matrix.getRows().forEach(System.out::println);
                    }
                }
            }
        }
        System.out.println(count);
        System.out.println(Main.commandHistory.size());
    }

    public static boolean checkOnCorrectness(Matrix matrix) {
        boolean correct = true;

        for (Row row : matrix.getRows()) {
            long countNonZero = row.getValues().stream().filter(e -> !e.isZero()).count();
            if (countNonZero == 1 && !row.getValues().get(row.getValues().size()-1).isZero()) {
                correct = false;
                break;
            }
        }
        if (!correct) {
            matrix.setVerdict("No Solutions");
        }
        return correct;
    }

    public static boolean checkOnInfinity(Matrix matrix) {
        boolean correct = true;

        if (matrix.size() > 0 && matrix.getRow(0).size() - 1 > matrix.size()) {
            matrix.setVerdict("Infinitely many solutions");
            correct = false;
        }
        return correct;
    }

    public static Complex[] normalizeOrder(Complex[] result) {
        for (Command command : Main.commandHistory) {
            if (command instanceof CommandSwapColumn) {
                CommandSwapColumn commandSwapColumn = (CommandSwapColumn) command;
                swap(result, commandSwapColumn.getIndexOfFirstColumn(), commandSwapColumn.getIndexOfSecondColumn());
            }
        }
        return result;
    }

    private static void swap(Complex[] d, int a, int b) {
        Complex temp = d[a];
        d[a] = d[b];
        d[b] = temp;
    }
}
