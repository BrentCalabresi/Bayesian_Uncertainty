package bn.core;

import bn.parser.XMLBIFParser;
import bn.util.ArraySet;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @author BAC on 3/30/2018.
 */
public class ExactInference {
    String file;

    public ExactInference(String file){
        this.file = file;
    }

    public static void main(String[] args) {
        BayesianNetwork network = null;

        try {
             network = new XMLBIFParser().readNetworkFromFile(args[0]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        // ---- network is read and stored ----

        System.out.println(network.nodes);
    }

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

            RandomVariable Y = (RandomVariable) vars.toArray()[0];

//            if (){
//                return 1.0;
//                }
            return -1.0;
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



