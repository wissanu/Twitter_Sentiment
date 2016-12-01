package twitt_test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import com.aliasi.classify.BaseClassifier;
import com.aliasi.classify.BaseClassifierEvaluator;
import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.ConfusionMatrix;
import com.aliasi.classify.JointClassification;
import com.aliasi.classify.LMClassifier;
import com.aliasi.util.AbstractExternalizable;

import twitt_test.Preparation_process;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitt_test.Evaluate;

public class search_tweet {

	@SuppressWarnings({ "unchecked"})
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		//invoke API to collect tweet
		int count_pos = 0, count_neg  = 0, count_neutral = 0;
		ArrayList<String> Original_inputText = new ArrayList<String>();
		ArrayList<String> en_inputText = new ArrayList<String>();
		ArrayList<String> temp_inputText = new ArrayList<String>();
		ArrayList<String> evaluate_categorie = new ArrayList<String>();
		Twitter twitter = TwitterFactory.getSingleton();
		String queryString = "burgerking";
	    Query query = new Query(queryString + " -filter:retweets");
	    query.setLang("en");
		query.setCount(1000);
		query.setResultType(Query.RECENT);
	    QueryResult result = null;
		try {
			result = twitter.search(query);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	    for (Status status : result.getTweets()) {
	    	if(status.getLang().equals("en"))
	        //System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
	    	Original_inputText.add(status.getText());
	    }
	    // prepare data.
	    temp_inputText = Preparation_process.clean_text(Original_inputText);
	    en_inputText = Preparation_process.extractfeature(temp_inputText);
	    
	    // process the output of each tweet from classification
	    LMClassifier classifier = (LMClassifier)AbstractExternalizable.readObject(new File("model/restaurant.naive_bayes"));
	    for (int i = 0; i < en_inputText.size(); i++)
	    {
		    JointClassification classification = classifier.classify(en_inputText.get(i));
		    System.out.println("Text: " + Original_inputText.get(i));
		    String bestCategory = classification.bestCategory();
		    evaluate_categorie.add(bestCategory);
		    
		    System.out.println("Best Category: " + bestCategory);
		    count_pos += (Objects.equals(bestCategory,"pos")) ? 1 : 0;
		    count_neg += (Objects.equals(bestCategory,"neg")) ? 1 : 0;
		    count_neutral += (Objects.equals(bestCategory,"neutral")) ? 1 : 0;

		    System.out.println(classification);
	    }
	    
	    System.out.println("Total Positive : "+count_pos);
	    System.out.println("Total Negative : "+count_neg);
	    System.out.println("Total Neutral : "+count_neutral);
	    
	    // evaluation
	    //Evaluate.evaluate_test(en_inputText, evaluate_categorie);
	    
	    
	 
	}

}
