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
        int N=0;

        for (int i = 1; i<N;i++){
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
