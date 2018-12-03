import java.util.ArrayList;

public class ShapleyCalc {
    private double[] costs;

    private int[] costsIndices;

    private double[] shVector;


    ShapleyCalc(ArrayList<Double> costs){
        costs.trimToSize();
        this.costs = new double[costs.size()];
        this.costsIndices = new int[costs.size()];
        this.shVector = new double[costs.size()];
        for(int i = 0; i < costs.size(); i++){
            this.costs[i] = costs.get(i);
            this.costsIndices[i] = i;
        }
    }

    public double[] calcShapleyVector(){
        double indWithMaxCost = -1;
        double paid = 0;

        printPerm();
        for(int i = 0; i < costs.length; i++){
            if(costsIndices[i] > indWithMaxCost){
                indWithMaxCost = costsIndices[i];
                double payment = costs[costsIndices[i]] - paid;
                shVector[costsIndices[i]] += payment;
                paid += payment;
            }
        }

        printShVector();
        while (nextPermutation()) {
            indWithMaxCost = -1;
            paid = 0;
            printPerm();
            for(int i = 0; i < costs.length; i++){
                if(costsIndices[i] > indWithMaxCost){
                    indWithMaxCost = costsIndices[i];
                    double payment = costs[costsIndices[i]] - paid;
                    shVector[costsIndices[i]] += payment;
                    paid += payment;
                }
            }
            printShVector();
        }

        int fact = factorial(costs.length);
        for(int i = 0; i < shVector.length; i++){
            shVector[i] /= fact;
        }
        return shVector;
    }

    private boolean nextPermutation(){
        int j = costsIndices.length - 2;
        while (j != -1 && costsIndices[j] >= costsIndices[j + 1]){
            j--;
        }

        if(j == -1){
            //no more permutations
            return false;
        }

        int k = costsIndices.length - 1;
        while (costsIndices[j] >= costsIndices[k]){
            k--;
        }

        swapCostsIndicesElems(j, k);

        //sort remaining part
        int l = j + 1;
        int r = costsIndices.length - 1;
        while (l < r){
            swapCostsIndicesElems(l++, r--);
        }
        return true;
    }

    private int factorial(int n) {
        if (n == 0) return 1;
        if (n < 0) return 0;
        return n * factorial(n-1);
    }

    private void swapCostsIndicesElems(int i, int j){
        int tmp = costsIndices[i];
        costsIndices[i] = costsIndices[j];
        costsIndices[j] = tmp;
    }

    public void printShVector(){
        System.out.printf("Shapley vector is: {");
        for(int i = 0; i < shVector.length - 1; i++){
            System.out.printf("%f; ", shVector[i]);
        }
        System.out.printf("%f", shVector[shVector.length - 1]);
        System.out.printf("}\n");
    }

    private void printPerm(){
        System.out.printf("perm is: [");
        for(int i = 0; i < costsIndices.length - 1; i++){
            System.out.printf("%d; ", costsIndices[i]);
        }
        System.out.printf("%d", costsIndices[costsIndices.length - 1]);
        System.out.printf("]\n");
    }
}
