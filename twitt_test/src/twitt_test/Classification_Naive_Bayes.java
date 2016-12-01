package twitt_test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.corpus.ObjectHandler;
import com.aliasi.lm.NGramProcessLM;
import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.Compilable;

import twitt_test.Preparation_process;
public class Classification_Naive_Bayes {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// perform naive baye classification
		   String[] categories = new String[3];
		   categories[0] = "neg";
		   categories[1] = "neutral";
		   categories[2] = "pos";
		   int nGramSize = 3;
		   ArrayList<String> original = new ArrayList<String>();
		   ArrayList<String> tempo = new ArrayList<String>();
		   DynamicLMClassifier<NGramProcessLM> classifier = DynamicLMClassifier.createNGramProcess(categories, nGramSize);
		   
		   //train neg
		   BufferedReader in_neg = new BufferedReader(new FileReader("data/neg/ne.txt"));
		   Classification classification_neg = new Classification(categories[0]);
		   String content_neg;
		   while( (content_neg = in_neg.readLine()) != null)
			  original.add(content_neg);
           tempo = Preparation_process.clean_text(original);
           tempo = Preparation_process.extractfeature(tempo);
           for (int i = 0; i < tempo.size(); i++)
           {
        	   Classified<CharSequence> classified = new Classified<>(tempo.get(i), classification_neg);
               ((ObjectHandler<Classified<CharSequence>>) classifier).handle(classified);
           }
           in_neg.close();
           original.clear();
           tempo.clear();
           
		 //train neutral
           BufferedReader in_neutral = new BufferedReader(new FileReader("data/neutral/n.txt"));
		   Classification classification_neutral = new Classification(categories[1]);
		   String content_neutral;
		   while( (content_neutral = in_neutral.readLine()) != null)
			   original.add(content_neutral);
		   tempo = Preparation_process.clean_text(original);
           tempo = Preparation_process.extractfeature(tempo);
           for (int i = 0; i < tempo.size(); i++)
           {
        	   Classified<CharSequence> classified = new Classified<>(tempo.get(i), classification_neutral);
               ((ObjectHandler<Classified<CharSequence>>) classifier).handle(classified);
           }
           in_neutral.close();
           original.clear();
           tempo.clear();
           
		 //train pos
           BufferedReader in_pos = new BufferedReader(new FileReader("data/pos/p.txt"));
		   Classification classification_pos = new Classification(categories[2]);
		   String content_pos;
		   while( (content_pos = in_pos.readLine()) != null)
			   original.add(content_pos);
		   tempo = Preparation_process.clean_text(original);
           tempo = Preparation_process.extractfeature(tempo);
           for (int i = 0; i < tempo.size(); i++)
           {
        	   Classified<CharSequence> classified = new Classified<>(tempo.get(i), classification_pos);
               ((ObjectHandler<Classified<CharSequence>>) classifier).handle(classified);
           }
           in_pos.close();
           original.clear();
           tempo.clear();
		   
		   AbstractExternalizable.compileTo((Compilable) classifier, new File("model/restaurant.naive_bayes"));	
		   //Util.consoleInputPrintClassification(classifier);
		   

	}

}
