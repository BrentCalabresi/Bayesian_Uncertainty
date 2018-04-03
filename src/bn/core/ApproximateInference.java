package bn.core;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author BAC on 4/1/2018.
 */
public class ApproximateInference {

    public static void main(String[] args) {

    }

    public Distribution rejectionSampling(RandomVariable[] X, Domain e, BayesianNetwork bn, int samples) {

        ArrayList<Double> localN = new ArrayList<>();

        for (int j = 0; j < samples; j++) {
            Map<RandomVariable, Object> x = //prior sample of bn;
            // if <b>x</b> is consistent with e then
            if (isConsistent(X, e)) {
                // <b>N</b>[x] <- <b>N</b>[x] + 1
                // where x is the value of X in <b>x</b>
                N[ProbUtil.indexOf(X, x)] += 1.0;
            }
        }
        // return NORMALIZE(<b>N</b>)
        return new CPT(N, X).normalize();
    }

    private boolean isConsistent(Map<RandomVariable, Object> x,
			Assignment[] e) {

        		for (Assignment ap : e) {
            		if (!ap.getValue().equals(x.get(ap.getTermVariable()))) {
               				return false;
               			}
            		}
        		return true;
        	}

}
