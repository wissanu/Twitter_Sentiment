package twitt_test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import com.aliasi.classify.BaseClassifier;
import com.aliasi.classify.Classification;
import com.aliasi.tokenizer.EnglishStopTokenizerFactory;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.PorterStemmerTokenizerFactory;
import com.aliasi.tokenizer.Tokenization;
import com.aliasi.tokenizer.Tokenizer;
import com.aliasi.tokenizer.TokenizerFactory;
import com.aliasi.util.AbstractExternalizable;

public class Preparation_process  {
	
	public static ArrayList<String> clean_text(ArrayList<String> data) throws ClassNotFoundException, IOException{
		// Remove non-english tweet, use 3Lang classifier to check every sentence is en or not if not discard.
	    String classifierPath = "model/3LangId.LMClassifier";
	    ArrayList<String> send_back = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		BaseClassifier<CharSequence> classifier = (BaseClassifier<CharSequence>)AbstractExternalizable.readObject(new File(classifierPath));
		for(String temp : data)
		{
			Classification classified = classifier.classify(temp);
			if(Objects.equals(classified.bestCategory(),"english"))
			{
				//Convert to lower case
			    temp = temp.toLowerCase();
			    
				// remove TinyURL
				temp = temp.replaceAll("((www\\.[^\\s]+)|(https://[^\\s]+))","");
				
				// remove @username
				temp = temp.replaceAll("(@[^\\s]+)|[@]","");
				
				// remove #hashtag
				temp = temp.replaceAll("#","");
				
				// change negative word before do StopWord
				temp = temp.replaceAll("(won't)","will not");
				temp = temp.replaceAll("(can't)","can not");
				temp = temp.replaceAll("(don't)","do not");
				temp = temp.replaceAll("(didn't)","did not");
				temp = temp.replaceAll("(shouldn't)","should not");
				temp = temp.replaceAll("('ll)"," will");
				temp = temp.replaceAll("(&)"," and ");
				temp = temp.replaceAll("(you're)"," you are ");
				temp = temp.replaceAll("(we're)"," we are ");
				temp = temp.replaceAll("(they're)"," they are ");
				temp = temp.replaceAll("(i'm)|(im)"," i am ");
				temp = temp.replaceAll("('s)"," is");
				
				// remove another punctuations
				temp = temp.replaceAll("[\\\\%=~_()|!:;*\\[\\]\\-\',<>./?\"]", "");
				
				// remove emotion icon
				temp = temp.replaceAll("[^\\x00-\\x7f-\\x80-\\xad]", "");
				
				// remove additional white spaces
				temp = temp.replaceAll("[\\s]+"," ");
				if(Objects.equals(temp.substring(0,1)," "))
					temp = temp.substring(1, temp.length());
				if(Objects.equals(temp.substring(temp.length()-1,temp.length())," "))
					temp = temp.substring(0, temp.length()-1);
				//temp = temp+".";
				
				//if(temp.length() >= 30)
				//System.out.println(temp);
				if(temp.length() >= 30)
				send_back.add(temp);
			}
		}
		return send_back;
	}
	
	public static ArrayList<String> extractfeature(ArrayList<String> data)
	{
		//do Steming and remove StopWord 
		 ArrayList<String> send_back = new ArrayList<String>();
		 TokenizerFactory factory = IndoEuropeanTokenizerFactory.INSTANCE;
		 factory = new EnglishStopTokenizerFactory(factory);
		 TokenizerFactory tokenizerFactory = IndoEuropeanTokenizerFactory.INSTANCE;
		 TokenizerFactory porterFactory = new PorterStemmerTokenizerFactory(tokenizerFactory);
		 for (String temp : data)
		 {
			 Tokenizer tokenizer_stopword = factory.tokenizer(temp.toCharArray(),0, temp.length());
			 String temp_input = "";
			 String[] stems = null;
			 for (String token : tokenizer_stopword) {
				 Tokenization tokenizer_stem = new Tokenization(token,porterFactory);
				 stems = tokenizer_stem.tokens();
				 for (String stem : stems) {
					 temp_input += stem+" ";
			       }
				 //temp_input += token+" ";
			}
			 if(Objects.equals(temp_input.substring(temp_input.length()-1,temp_input.length())," "))
				 temp_input = temp_input.substring(0, temp_input.length()-1);
			 send_back.add(temp_input);
		 }
		return send_back;
	}
}
