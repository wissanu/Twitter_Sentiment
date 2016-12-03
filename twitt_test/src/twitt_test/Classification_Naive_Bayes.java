package twitt_test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.ConfusionMatrix;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.JointClassification;
import com.aliasi.classify.JointClassifier;
import com.aliasi.classify.JointClassifierEvaluator;
import com.aliasi.corpus.ObjectHandler;
import com.aliasi.lm.NGramProcessLM;
import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.Compilable;
import com.aliasi.util.Files;

import twitt_test.Preparation_process;
public class Classification_Naive_Bayes {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// perform naive baye classification
		   String[] categories = new String[3];
		   categories[0] = "neg";
		   categories[1] = "neutral";
		   categories[2] = "pos";
		   int nGramSize = 2;
		   ArrayList<String> original = new ArrayList<String>();
		   ArrayList<String> tempo = new ArrayList<String>();
		   DynamicLMClassifier<NGramProcessLM> classifier = DynamicLMClassifier.createNGramProcess(categories, nGramSize);
		   
		   //train neg
		   BufferedReader in_neg = new BufferedReader(new FileReader("data/Training_Set/neg/ne.txt"));
		   Classification classification_neg = new Classification(categories[0]);
		   String content_neg;
		   while( (content_neg = in_neg.readLine()) != null)
			  original.add(content_neg);
           tempo = Preparation_process.clean_text(original);
           tempo = Preparation_process.extractfeature(tempo);
           for (int i = 0; i < tempo.size(); i++)
           {
        	   Classified<CharSequence> classified = new Classified<CharSequence>(tempo.get(i), classification_neg);
               classifier.handle(classified);
           }
           in_neg.close();
           original.clear();
           tempo.clear();
           
		 //train neutral
           BufferedReader in_neutral = new BufferedReader(new FileReader("data/Training_Set/neutral/n.txt"));
		   Classification classification_neutral = new Classification(categories[1]);
		   String content_neutral;
		   while( (content_neutral = in_neutral.readLine()) != null)
			   original.add(content_neutral);
		   tempo = Preparation_process.clean_text(original);
           tempo = Preparation_process.extractfeature(tempo);
           for (int i = 0; i < tempo.size(); i++)
           {
        	   Classified<CharSequence> classified = new Classified<CharSequence>(tempo.get(i), classification_neutral);
               classifier.handle(classified);
           }
           in_neutral.close();
           original.clear();
           tempo.clear();
           
		 //train pos
           BufferedReader in_pos = new BufferedReader(new FileReader("data/Training_Set/pos/p.txt"));
		   Classification classification_pos = new Classification(categories[2]);
		   String content_pos;
		   while( (content_pos = in_pos.readLine()) != null)
			   original.add(content_pos);
		   tempo = Preparation_process.clean_text(original);
           tempo = Preparation_process.extractfeature(tempo);
           for (int i = 0; i < tempo.size(); i++)
           {
        	   Classified<CharSequence> classified = new Classified<CharSequence>(tempo.get(i), classification_pos);
               classifier.handle(classified);
               
           }
           in_pos.close();
           original.clear();
           tempo.clear();

           // produce classification into file.
		   AbstractExternalizable.compileTo((Compilable) classifier, new File("model/restaurant.naive_bayes"));	
		   
		   // Evaluate classifier.
		   JointClassifier<CharSequence> compiledClassifier = (JointClassifier<CharSequence>)AbstractExternalizable.compile(classifier);
		   boolean storeCategories = true;
	       JointClassifierEvaluator<CharSequence> evaluator = new JointClassifierEvaluator<CharSequence>(compiledClassifier,categories,storeCategories);
		   
	       //test pos
           BufferedReader intest_pos = new BufferedReader(new FileReader("data/Test_Set/pos/p.txt"));
		   Classification classification_testpos = new Classification(categories[2]);
		   String content_testpos;
		   while( (content_testpos = intest_pos.readLine()) != null)
			   original.add(content_testpos);
		   tempo = Preparation_process.clean_text(original);
           tempo = Preparation_process.extractfeature(tempo);
           for (int i = 0; i < tempo.size(); i++)
           {
        	   Classified<CharSequence> classified = new Classified<CharSequence>(tempo.get(i), classification_testpos);
               classifier.handle(classified);
               evaluator.handle(classified);
           
               /*JointClassification jc = compiledClassifier.classify(tempo.get(i));
	           String bestCategory = jc.bestCategory();
	           String details = jc.toString();
	           System.out.println("Got best category of: " + bestCategory);
	           System.out.println(jc.toString());
	           System.out.println("---------------");*/
               
           }
           intest_pos.close();
           original.clear();
           tempo.clear();
           
           //test neg
           BufferedReader intest_neg = new BufferedReader(new FileReader("data/Test_Set/neg/ne.txt"));
		   Classification classification_testneg = new Classification(categories[0]);
		   String content_testneg;
		   while( (content_testneg = intest_neg.readLine()) != null)
			   original.add(content_testneg);
		   tempo = Preparation_process.clean_text(original);
           tempo = Preparation_process.extractfeature(tempo);
           for (int i = 0; i < tempo.size(); i++)
           {
        	   Classified<CharSequence> classified = new Classified<CharSequence>(tempo.get(i), classification_testneg);
               classifier.handle(classified);
               evaluator.handle(classified);
           
               /*JointClassification jc = compiledClassifier.classify(tempo.get(i));
	           String bestCategory = jc.bestCategory();
	           String details = jc.toString();
	           System.out.println("Got best category of: " + bestCategory);
	           System.out.println(jc.toString());
	           System.out.println("---------------");*/
               
           }
           intest_neg.close();
           original.clear();
           tempo.clear();
           
           //test neutral
           BufferedReader intest_neutral = new BufferedReader(new FileReader("data/Test_Set/neutral/n.txt"));
		   Classification classification_testneutral = new Classification(categories[1]);
		   String content_testneutral;
		   while( (content_testneutral = intest_neutral.readLine()) != null)
			   original.add(content_testneutral);
		   tempo = Preparation_process.clean_text(original);
           tempo = Preparation_process.extractfeature(tempo);
           for (int i = 0; i < tempo.size(); i++)
           {
        	   Classified<CharSequence> classified = new Classified<CharSequence>(tempo.get(i), classification_testneutral);
               classifier.handle(classified);
               evaluator.handle(classified);
           /*
               JointClassification jc = compiledClassifier.classify(tempo.get(i));
	           String bestCategory = jc.bestCategory();
	           String details = jc.toString();
	           System.out.println("Got best category of: " + bestCategory);
	           System.out.println(jc.toString());
	           System.out.println("---------------");*/
               
           }
           intest_neutral.close();
           original.clear();
           tempo.clear();
           
           ConfusionMatrix confMatrix = evaluator.confusionMatrix();
           
           // show accuracy in this model.
           System.out.println("Total Accuracy: " + confMatrix.totalAccuracy());
           System.out.println("FULL EVAL");
           System.out.println(evaluator.confusionMatrix());
           
           
           // print precision&recall in each categories
           //Util.printPrecRecall(evaluator);

	}

}
