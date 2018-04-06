

import bn.core.Assignment;
import bn.core.BayesianNetwork;
import bn.core.BayesianNetwork.Node;
import bn.core.Distribution;
import bn.core.RandomVariable;
import bn.parser.BIFParser;
import bn.parser.XMLBIFParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author BAC on 3/30/2018.
 */
public class ExactInference {
    String file = "/src/bn/examples/";
    static BayesianNetwork network = null;
    public ExactInference(String file){
        this.file += file;
//        System.out.println(file);
    }


    public static void main(String[] args) {
        try {

            if (args[0].contains(".xml"))
                network = new XMLBIFParser().readNetworkFromFile(args[0]);
            else if (args[0].contains(".bif")){
                BIFParser parser = new BIFParser(new FileInputStream(args[0]));
                network = parser.parseNetwork();
            }
            else
                System.out.println("Bad filename");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        // ---- network is read and stored ----
        ExactInference inference = new ExactInference(args[0]);
        Node query = network.getNodeForVariable(network.getVariableByName(args[1]));
        Assignment evidence = new Assignment();
//        System.out.println(network.getVariableListTopologicallySorted());
        for (int i=2; i< args.length;i+=2){
//            System.out.println(args[i]+" "+args[i+1]);
            evidence.put(network.getVariableByName(args[i]),args[i+1]);
        }


        System.out.println("You're asking about: "+query.variable+", given: "+evidence);

        Distribution d = inference.enumerationAsk(query,evidence,network);

        System.out.println(d);
        //System.out.println(network.nodes);
    }

    public Distribution enumerationAsk(Node X, Assignment observedEvidence, BayesianNetwork bn) {
        Distribution Q = new Distribution();

        //for each value xi that X can have
        for (Object v: X.variable.domain){
            Assignment newAss = observedEvidence.copy();
            newAss.put(X.variable, v);
            double num = enumerateAll(bn.getVariableListTopologicallySorted(), newAss);
            Q.put(v,num);
        }
        Q.normalize();
        return Q;
    }


    protected double enumerateAll(List<RandomVariable> vars, Assignment e) {
        if (vars.isEmpty()){
            return 1.0;
        }
        RandomVariable Y = (RandomVariable) vars.toArray()[0];
        if (e.containsKey(Y)){
            return network.getProb(Y,e) * enumerateAll(vars.subList(1,vars.size()),e);
        } else {
            double sum = 0;
            for (Object o: Y.domain){
                Assignment newAss = e.copy();
                newAss.put(Y, o);
                double prob = network.getProb(Y,newAss);
                double enumer = enumerateAll(vars.subList(1,vars.size()),newAss);
                sum +=  prob * enumer;
            }
            return sum;
        }
    }


}


