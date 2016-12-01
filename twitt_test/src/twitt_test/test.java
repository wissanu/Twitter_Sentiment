package twitt_test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.ConditionalClassification;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.LogisticRegressionClassifier;
import com.aliasi.classify.NaiveBayesClassifier;
import com.aliasi.classify.TradNaiveBayesClassifier;
import com.aliasi.corpus.ObjectHandler;
import com.aliasi.corpus.XValidatingObjectCorpus;
import com.aliasi.io.LogLevel;
import com.aliasi.io.Reporter;
import com.aliasi.io.Reporters;
import com.aliasi.lm.NGramBoundaryLM;
import com.aliasi.lm.TokenizedLM;
import com.aliasi.stats.AnnealingSchedule;
import com.aliasi.stats.RegressionPrior;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.TokenFeatureExtractor;
import com.aliasi.tokenizer.TokenizerFactory;
import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.FeatureExtractor;
import com.aliasi.util.ObjectToDoubleMap;
import twitt_test.Util;

import au.com.bytecode.opencsv.CSVReader;

import java.util.Objects;

public class test {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("data/neg/ne.txt"));
		String line;
		while( (line = in.readLine()) != null)
		{
			System.out.println(line);
		}
	}
}
