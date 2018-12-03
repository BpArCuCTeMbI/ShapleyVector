import java.util.ArrayList;

public class ShapleyCalc {
    private double[] costs;

    ShapleyCalc(ArrayList<Double> costs){
        costs.trimToSize();
        this.costs = new double[costs.size()];
        for(int i = 0; i < costs.size(); i++){
            this.costs[i] = costs.get(i);
        }

        for (int i = 0; i < this.costs.length; i++) {
            System.out.println(this.costs[i]);
        }
    }

    public void calcShapleyVector(){
        
    }

}
