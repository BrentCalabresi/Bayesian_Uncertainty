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


    }

    public Distribution rejectionSampling(RandomVariable r, Assignment e, BayesianNetwork bn, int samples) {
        //double[] xCounts = new double[X.getDomain().size()];
        LinkedHashMap<Object,Integer> rvCounts = new LinkedHashMap<>();

        for (int i = 0; i < samples;i++){
            Assignment priorSample = bn.priorSample();
            if (isConsistent(priorSample, e)) {
                Object rvSample = priorSample.get(r);
                rvCounts.put(rvSample, rvCounts.get(rvSample) + 1);
            }
        }
        Distribution d = new Distribution(rvCounts);
        d.normalize();
        return d;
    }

    private boolean isConsistent(Assignment x, Assignment e) {

            for (RandomVariable rv : e.keySet()) {
//            		
                if (!e.get(rv).equals(x.get(rv))) return false;

            }
            return true;
        }

}
