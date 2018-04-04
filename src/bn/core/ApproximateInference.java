package bn.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author BAC on 4/1/2018.
 */
public class ApproximateInference {

    public static void main(String[] args) {

        BayesianNetwork bn = new BayesianNetwork();
        bn.createDefault();
        RandomVariable A = bn.getVariableByName("A");
        RandomVariable B = bn.getVariableByName("B");
        RandomVariable C = bn.getVariableByName("C");

        Assignment e = new Assignment();
        e.set(B, B.getDomain().get(0));
        e.set(C, C.getDomain().get(1));

        int sampleNum = 10000;

        Distribution d = rejectionSampling(A, e, bn, sampleNum);
        System.out.println(d);

        e.set(A, A.getDomain().get(0));
        System.out.println("actual a1: " + bn.getNodeForVariable(A).cpt.get(e));


    }

    public static Distribution rejectionSampling(RandomVariable r, Assignment e, BayesianNetwork bn, int samples) {
        //double[] xCounts = new double[X.getDomain().size()];
        LinkedHashMap<Object,Integer> rvCounts = new LinkedHashMap<>();

        for (int i = 0; i < samples;i++){
            Assignment priorSample = bn.priorSample();
            if (isConsistent(priorSample, e)) {
                Object rvSample = priorSample.get(r);
                //System.out.println("count number: " + rvCounts.get(rvSample));
                if (rvCounts.keySet().contains(rvSample)) {
                    rvCounts.put(rvSample, rvCounts.get(rvSample) + 1);
                } else {
                    rvCounts.put(rvSample, 1);
                }

            }
        }
        Distribution d = new Distribution(rvCounts);
        d.normalize();
        return d;
    }

    private static boolean isConsistent(Assignment x, Assignment e) {

            for (RandomVariable rv : e.keySet()) {
//
                if (!e.get(rv).equals(x.get(rv))) return false;

            }
            return true;
        }

}
