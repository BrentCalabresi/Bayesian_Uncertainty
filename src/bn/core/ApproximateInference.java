package bn.core;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author BAC on 4/1/2018.
 */
public class ApproximateInference {

    public static void main(String[] args) {

    }

    public Double rejectionSampling(BayesianNetwork.Node X, Assignment e, BayesianNetwork bn, int samples) {
        double[] xCounts = new double[samples];

        for (int i = 0; i < samples;i++){
            X = bn.priorSample();
//            if (X.isConsistent(e)){
//
//            }
        }
        return -1.0;
    }

    private boolean isConsistent(Assignment x,
			Assignment[] e) {

        		for (Assignment ap : e) {
//            		if (!ap.getValue().equals(x.get(ap.getTermVariable()))) {
//               				return false;
//               			}
            		}
        		return true;
        	}

}
