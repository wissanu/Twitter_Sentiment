package twitt_test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.aliasi.classify.BaseClassifier;
import com.aliasi.classify.BaseClassifierEvaluator;
import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.JointClassifier;
import com.aliasi.classify.JointClassifierEvaluator;
import com.aliasi.corpus.XValidatingObjectCorpus;
import com.aliasi.util.AbstractExternalizable;

public class Evaluate {

	@SuppressWarnings("unchecked")
	public static void evaluate_test(ArrayList<String> data, ArrayList<String> category) throws ClassNotFoundException, IOException{
		String[] categories = new String[category.size()];
		for (int i = 0; i < category.size(); i++)
			categories[i] = category.get(i);
        JointClassifierEvaluator<CharSequence> classifier = (JointClassifierEvaluator<CharSequence>) AbstractExternalizable.readObject(new File("model/restaurant.naive_bayes"));
		boolean storeInputs = false;
		JointClassifierEvaluator<CharSequence> evaluator = new JointClassifierEvaluator<CharSequence>((JointClassifier<CharSequence>) classifier,categories, storeInputs);
		for (int i = 0; i < data.size(); i++) {
			String temp_categories = categories[i];
			Classification classification = new Classification(temp_categories);
			Classified<CharSequence> classified = new Classified<CharSequence>(data.get(i),classification);
			evaluator.handle(classified);
		}
		Util.printConfusionMatrix(evaluator.confusionMatrix());
		//System.out.println(evaluator.toString());
		//Util.printPrecRecall(evaluator);
	}
}
