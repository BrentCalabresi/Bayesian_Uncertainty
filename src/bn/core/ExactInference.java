package bn.core;

import bn.util.ArraySet;

/**
 * @author BAC on 3/30/2018.
 */
public class ExactInference {

        public Distribution enumerationAsk(ArraySet<RandomVariable> X, RandomVariable observedEvidence, BayesianNetwork bn) {
            Distribution Q = new Distribution(X.size());

            for (RandomVariable v: X){
                Q.put(v,enumerateAll(X,observedEvidence));
            }

            Q.normalize();
            return Q;
        }

        protected double enumerateAll(ArraySet<RandomVariable> vars, RandomVariable e) {

            if (vars.isEmpty())
                return 1;

            ArraySet<RandomVariable> Y = vars;

            if (Y.contains(e)){
                return
                }
            }

//    protected double enumerateAll(ArraySet<RandomVariable> vars, RandomVariable e) {
//        // if EMPTY?(vars) then return 1.0
//        if (vars.isEmpty()) {
//            return 1;
//        }
//        // Y <- FIRST(vars)
//        RandomVariable Y = (RandomVariable) vars.toArray()[0];
//        // if Y has value y in e
//        if (e.domain.contains(Y)) {
//            // then return P(y | parents(Y)) * ENUMERATE-ALL(REST(vars), e)
//
//            vars.remove(Y);
//            return e.posteriorForParents(Y) * enumerateAll(vars, e);
//        }
//
//        double sum = 0;
//        for (Object y : ((FiniteDomain) Y.getDomain()).getPossibleValues()) {
//            e.setExtendedValue(Y, y);
//            sum += e.posteriorForParents(Y) * enumerateAll(Util.rest(vars), e);
//        }
//
//        return sum;
//    }


}



