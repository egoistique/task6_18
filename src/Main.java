import java.util.Locale;// 0.000000001
import java.util.Scanner;

public class Main {
    private static void printSolution(double e, int n, Solution solution, double mathSolution) {
        System.out.printf("Значение функции через math: %.010f\n" +
                        "Сумма %d членов: %.010f\n" +
                        "Сумма членов больших %.2e: %.010f\n" +
                        "Сумма членов больших %.2e: %.010f\n",
                mathSolution, n, solution.sumN, e, solution.sumE, e / 10, solution.sumE10);
    }

    public static double solveMath(double x) {
        return 1 / Math.pow(1 - x, 2);
    }

    public static Solution solve(double x, int n, double e) {
        double curPart = 1; //текущий эл.
        double preX = 1; //предыдущ. эл.

        double sumN = 0;
        double sumE = 0;
        double sumE10 = 0;

        for (int i = 0; i < n || Math.abs(curPart) > e; preX *= x, i++) {
            if (i < n) {
                sumN += curPart;
            }

            if (Math.abs(curPart) >= e) {
                sumE += curPart;
            }

            if (Math.abs(curPart) >= e / 10) {
                sumE10 += curPart;
            }

            curPart = nextPart(i + 2, preX, x);
        }

        return new Solution(sumN, sumE, sumE10);
    }

    public static double nextPart(double n, double preX, double x) {
        return n * preX * x;
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        Scanner sc = new Scanner(System.in);

        double x = sc.nextDouble();
        double e = sc.nextDouble();
        int n = sc.nextInt();

        Solution solution = solve(x, n, e);
        double mathSolution = solveMath(x);

        printSolution(e, n, solution, mathSolution);

    }
}

    class Solution {
        double sumN;
        double sumE;
        double sumE10;

        public Solution(double sumN, double sumE, double sumE10) {
            this.sumN = sumN;
            this.sumE = sumE;
            this.sumE10 = sumE10;
        }
    }
