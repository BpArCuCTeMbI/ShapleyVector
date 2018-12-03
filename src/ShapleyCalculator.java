import java.util.ArrayList;
import java.util.Scanner;

public class ShapleyCalculator {
    public static void main(String[] args) {
        System.out.println("Enter the source data line by line. e.g.:\n1\n2\n3\nFinish it by an empty line.");
        Scanner scn = new Scanner(System.in);
        ArrayList<Double> costs = new ArrayList<>();

        String buf;
        while(!(buf = scn.nextLine()).isEmpty()){
            costs.add(Double.parseDouble(buf));
        }

        scn.close();
        ShapleyCalc shCalc = new ShapleyCalc(costs);
        shCalc.calcShapleyVector();

        shCalc.printShVector();
    }
}
