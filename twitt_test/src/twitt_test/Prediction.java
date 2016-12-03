package twitt_test;

import java.awt.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.TokenFeatureExtractor;
import com.aliasi.tokenizer.TokenizerFactory;
import com.aliasi.util.FeatureExtractor;

public class Prediction {
	
	// (simple test) predict the most menu people express their feeling in each categories.
    public static TokenizerFactory tokFact = IndoEuropeanTokenizerFactory.INSTANCE;
    public static FeatureExtractor<CharSequence> tokenFeatureExtractor  = new TokenFeatureExtractor(tokFact);
	
    public static Map<String, ? extends Number > features = null;
    
    public static String[] cheeytots = {"cheesytot","chees","cheesytotsareback","tot","tots","cheesi"};
	
	public static String[] dynamite_fries = {"dynamit"};
	
	public static String[] napoleon = {"napoleon"};
	
	public static String[] chick = {"chick","chicken"};
	
	public static String[] whopper = {"whopper"};
	
	public static String[] nugget = {"nugget","nuggets"};
	
	public static void frequency_pos(String pos_data)
	{
		
		System.out.println("-----------pos--------------");
		features = tokenFeatureExtractor.features(pos_data);
		
		int pos_count_menu = 0;
		for(String s : features.keySet())
			for(String a : cheeytots)
				pos_count_menu += (Objects.equals(s,a))? (features.get(s)).intValue() : 0;
		System.out.println("Cheey tots : "+pos_count_menu);
				
		pos_count_menu = 0;
		for(String s : features.keySet())
			for(String a : dynamite_fries)
				pos_count_menu += (Objects.equals(s,a))? (features.get(s)).intValue() : 0;
		System.out.println("dynamite fries : "+pos_count_menu );
		
		pos_count_menu = 0;
		for(String s : features.keySet())
			for(String a : napoleon)
				pos_count_menu += (Objects.equals(s,a))? (features.get(s)).intValue() : 0;
		System.out.println("napoleon set : "+pos_count_menu );
		
		pos_count_menu = 0;
		for(String s : features.keySet())
			for(String a : chick)
				pos_count_menu += (Objects.equals(s,a))? (features.get(s)).intValue() : 0;
		System.out.println("chicken : "+pos_count_menu );
		
		pos_count_menu = 0;
		for(String s : features.keySet())
			for(String a : whopper)
				pos_count_menu += (Objects.equals(s,a))? (features.get(s)).intValue() : 0;
		System.out.println("whopper : "+pos_count_menu );
		
		pos_count_menu = 0;
		for(String s : features.keySet())
			for(String a : nugget)
				pos_count_menu += (Objects.equals(s,a))? (features.get(s)).intValue() : 0;
		System.out.println("nugget : "+pos_count_menu );
		
	}
	
	public static void frequency_neg(String neg_data)
	{
		
		System.out.println("-----------neg--------------");
		features = tokenFeatureExtractor.features(neg_data);
		
		int neg_count_menu = 0;
		for(String s : features.keySet())
			for(String a : cheeytots)
				neg_count_menu += (Objects.equals(s,a))? (features.get(s)).intValue() : 0;
		System.out.println("Cheey tots : "+neg_count_menu);
				
		neg_count_menu = 0;
		for(String s : features.keySet())
			for(String a : dynamite_fries)
				neg_count_menu += (Objects.equals(s,a))? (features.get(s)).intValue() : 0;
		System.out.println("dynamite fries : "+neg_count_menu );
		
		neg_count_menu = 0;
		for(String s : features.keySet())
			for(String a : napoleon)
				neg_count_menu += (Objects.equals(s,a))? (features.get(s)).intValue() : 0;
		System.out.println("napoleon set : "+neg_count_menu );
		
		neg_count_menu = 0;
		for(String s : features.keySet())
			for(String a : chick)
				neg_count_menu += (Objects.equals(s,a))? (features.get(s)).intValue() : 0;
		System.out.println("chicken : "+neg_count_menu );
		
		neg_count_menu = 0;
		for(String s : features.keySet())
			for(String a : whopper)
				neg_count_menu += (Objects.equals(s,a))? (features.get(s)).intValue() : 0;
		System.out.println("whopper : "+neg_count_menu );
		
		neg_count_menu = 0;
		for(String s : features.keySet())
			for(String a : nugget)
				neg_count_menu += (Objects.equals(s,a))? (features.get(s)).intValue() : 0;
		System.out.println("nugget : "+neg_count_menu );
		
	}
	
	public static void frequency_neutral(String neutral_data)
	{
		
		System.out.println("-----------neutral--------------");
		features = tokenFeatureExtractor.features(neutral_data);
		
		int neutral_count_menu = 0;
		for(String s : features.keySet())
			for(String a : cheeytots)
				neutral_count_menu += (Objects.equals(s,a))? (features.get(s)).intValue() : 0;
		System.out.println("Cheey tots : "+neutral_count_menu);
				
		neutral_count_menu = 0;
		for(String s : features.keySet())
			for(String a : dynamite_fries)
				neutral_count_menu += (Objects.equals(s,a))? (features.get(s)).intValue() : 0;
		System.out.println("dynamite fries : "+neutral_count_menu );
		
		neutral_count_menu = 0;
		for(String s : features.keySet())
			for(String a : napoleon)
				neutral_count_menu += (Objects.equals(s,a))? (features.get(s)).intValue() : 0;
		System.out.println("napoleon set : "+neutral_count_menu );
		
		neutral_count_menu = 0;
		for(String s : features.keySet())
			for(String a : chick)
				neutral_count_menu += (Objects.equals(s,a))? (features.get(s)).intValue() : 0;
		System.out.println("chicken : "+neutral_count_menu );
		
		neutral_count_menu = 0;
		for(String s : features.keySet())
			for(String a : whopper)
				neutral_count_menu += (Objects.equals(s,a))? (features.get(s)).intValue() : 0;
		System.out.println("whopper : "+neutral_count_menu );
		
		neutral_count_menu = 0;
		for(String s : features.keySet())
			for(String a : nugget)
				neutral_count_menu += (Objects.equals(s,a))? (features.get(s)).intValue() : 0;
		System.out.println("nugget : "+neutral_count_menu );
		
	}
  }

