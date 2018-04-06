
import bn.core.Assignment;
import bn.core.BayesianNetwork;
import bn.core.*;
import bn.core.Distribution;
import bn.core.RandomVariable;
import bn.parser.BIFParser;
import bn.parser.XMLBIFParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

/**
 * @author BAC on 4/1/2018.
 */
public class ApproximateInference {

    public static void main(String[] args) {


        BayesianNetwork network = null;

        try {

            if (args[1].contains(".xml"))
                network = new XMLBIFParser().readNetworkFromFile(args[1]);
            else if (args[1].contains(".bif")){
                BIFParser parser = new BIFParser(new FileInputStream(args[1]));
                network = parser.parseNetwork();
            }
            else
                System.out.println("Bad filename");

        } catch (IOException f) {
            f.printStackTrace();
        } catch (ParserConfigurationException f) {
            f.printStackTrace();
        } catch (org.xml.sax.SAXException e1) {
            e1.printStackTrace();
        }

        int samples = Integer.parseInt(args[0]);
//        network.print(System.out);
//        System.out.println(network.getVariableListTopologicallySorted());
//        System.out.println(network.getVariableByName("PCWP").getDomain());
        RandomVariable X = network.getVariableByName(args[2]);

        Assignment evidence = new Assignment();
        for (int i = 3; i < args.length; i += 2){
            RandomVariable e = network.getVariableByName(args[i]);
            evidence.put(e, args[i+1]);
        }

        Distribution rejectD = rejectionSampling(X, evidence, network, samples);
        Distribution likelihoodD = likelihoodWeighting(X, evidence, network, samples);
        System.out.println("rejectD: ------\n" + rejectD);
        System.out.println("likelihoodD: -------\n" + likelihoodD);
        System.out.println("Finished");



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

    public static Distribution likelihoodWeighting(RandomVariable X, Assignment e, BayesianNetwork bn, int N){
        Distribution W = new Distribution(X);
        for (Object o : X.getDomain()){
            W.put(o, 0);
        }
        for (int i = 0; i < N; i++){
            Object[] x_w = weightedSample(bn, e);
            Assignment x = (Assignment)x_w[0];
            Double w = (Double)x_w[1];
//    		System.out.println(i + " weight: " + w);
//    		System.out.println(x.get(X));
//    		if (x.get(X).equals("true")){
//    			throw new NullPointerException();
//    		}
            W.put(x.get(X),W.get(x.get(X)) + w);
        }
        W.normalize();
        return W;
    }

    public static Object[] weightedSample(BayesianNetwork bn, Assignment e) {
        Assignment assignment = e.copy();
        Double w = 1.0;
        for (RandomVariable X : bn.getVariableListTopologicallySorted()) {
            if (e.containsKey(X)) {
                Double prob = bn.getProb(X, assignment);
//    			System.out.println("prob(" + X.name + ") = " + prob);
                w *= prob;
            } else {
                Object o = bn.getNodeForVariable(X).randomSampleGivenParents(assignment);
//    			System.out.println(X.name + " = " + o);
                assignment.put(X, o);
            }
        }
        return new Object[]{assignment, w};
    }

}