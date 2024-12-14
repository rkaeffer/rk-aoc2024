package fr.rk.aoc.challenge;


import java.util.ArrayList;
import java.util.List;

public final class Day13 {

    public static long getNbTokenToWin(List<String> input, boolean lessThan100) {
        List<ClawMachine> clawMachines = new ArrayList<>();
        for(int i=0; i<(input.size() + 1) / 4; i++) {
            List<String> description = new ArrayList<>(3);
            description.add(input.get(i * 4));
            description.add(input.get((i  * 4) + 1));
            description.add(input.get((i  * 4) + 2));
            clawMachines.add(new ClawMachine(description, lessThan100));
        }
        return clawMachines.stream().map(cm -> cm.getNbTokenToWin(lessThan100)).reduce(0L, Long::sum);
    }

    static class ClawMachine {
        long xPrize;
        long yPrize;

        Equation xEq;

        Equation yEq;

        long nbTokenToWin;

        long[] solution;

        public ClawMachine(List<String> description, boolean lessThan100) {
            String[] prizeDesc = description.get(2).split("=");
            this.yPrize = Long.parseLong(prizeDesc[2]);
            this.xPrize = Long.parseLong(prizeDesc[1].substring(0, prizeDesc[1].length() - 3));
            if(!lessThan100) {
                long toAdd = 10000000000000L;
                this.yPrize += toAdd;
                this.xPrize += toAdd;
            }
            String[] firstDesc = description.get(0).split("\\+");
            String[] secondDesc = description.get(1).split("\\+");
            xEq = new Equation(
                    Long.parseLong(firstDesc[1].substring(0, firstDesc[1].length() - 3)),
                    Long.parseLong(secondDesc[1].substring(0, secondDesc[1].length() - 3)),
                    this.xPrize
            );
            yEq = new Equation(
                    Long.parseLong(firstDesc[2]),
                    Long.parseLong(secondDesc[2]),
                    this.yPrize
            );
            this.nbTokenToWin = -1;
            this.solution = null;
            this.solveEquation();
        }
        public void solveEquation() {
            long[] xCoeff = new long[]{xEq.a, yEq.a};
            long[] yCoeff = new long[]{xEq.b, yEq.b};
            long[] eq = new long[]{xEq.c, yEq.c};
            long[][] eliminator = new long[2][2];
            eliminator[0][0] = yCoeff[1] * xCoeff[0];
            eliminator[0][1] = yCoeff[1] * eq[0];

            eliminator[1][0] = yCoeff[0] * xCoeff[1];
            eliminator[1][1] = yCoeff[0] * eq[1];

            double x_var;
            double y_var;
            try {
                x_var = (double)(eliminator[0][1] - eliminator[1][1]) / (double)(eliminator[0][0] - eliminator[1][0]);
                y_var = (eq[0] - xCoeff[0] * x_var) / yCoeff[0];
            } catch (ArithmeticException e) {
                return;
            }
            if(x_var%1 == 0 && y_var%1 == 0) {
                this.solution = new long[]{(long)x_var, (long)y_var};
            }
        }

        public long getNbTokenToWin(boolean lessThan100) {
            if(this.solution == null) {
                return 0L;
            } else {
                if(lessThan100) {
                    if( this.solution[0] <=100 && this.solution[1]<=100) {
                        return 3 * this.solution[0] + this.solution[1];
                    }
                    return 0L;
                } else {
                    return 3 * this.solution[0] + this.solution[1];
                }
            }
        }
    }

    static class Equation {
        long a;
        long b;
        long c;

        Equation(long a, long b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
