import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static Card[] cards;	
	public static Scanner in = new Scanner(System.in);
    public static ArrayList<Set>  total_sets = new ArrayList<Set>();
    public static ArrayList<Set> disjoint_sets = new ArrayList<Set>();

    public static Character checkSymbol(Character ch) 
    {
        if (ch == 'A' || ch == 'a' || ch == '@')
            return 'A';
        else if (ch == 'S' || ch == 's' || ch == '$')
            return 'S';
        else
            return 'H';        
    }

    public static String checkShading (Character ch) 
    {
        if (ch == 'A' || ch == 'S' || ch == 'H')
            return "upper";
        else if (ch == 'a' || ch == 's' || ch == 'h')
            return "lower";
        else
            return "symbol";
    }
    
    public static void getInput() 
    {
        int numcards = in.nextInt();
        in.nextLine();
        cards = new Card[numcards];
        
        for (int i = 0; i < numcards; i++) 
        {
            String input = in.nextLine();
            String[] info = input.split(" ");
            String colour = info[0];
            Character symbol = checkSymbol(info[1].charAt(0));
            String shading = checkShading(info[1].charAt(0));
            int number = info[1].length();
    
            cards[i] = new Card(colour, symbol, shading, number, input);
        }
    }
    
    public static ArrayList<Set> findDisjointSets(Set set) 
    {
        boolean flag;
        ArrayList<Set> disjoints = new ArrayList<Set>();
        disjoints.add(set);
        
        for (int i = 0; i < total_sets.size(); i++) 
        {
            flag = true;
            
            for (int j = 0; j < disjoints.size(); j++) 
            {           
                if(total_sets.get(i).one.isCardMatching(disjoints.get(j).one) 		|| total_sets.get(i).one.isCardMatching(disjoints.get(j).two) 	||
                		total_sets.get(i).one.isCardMatching(disjoints.get(j).three)|| total_sets.get(i).two.isCardMatching(disjoints.get(j).one) 	||
                		total_sets.get(i).two.isCardMatching(disjoints.get(j).two) 	|| total_sets.get(i).two.isCardMatching(disjoints.get(j).three) ||
                		total_sets.get(i).three.isCardMatching(disjoints.get(j).one)|| total_sets.get(i).three.isCardMatching(disjoints.get(j).two) ||
                		total_sets.get(i).three.isCardMatching(disjoints.get(j).three))
                {
                	flag = false;
                	break;
                }
                
            }
            
            if (flag) 
                disjoints.add(total_sets.get(i));           
        }
        
        return disjoints;
    }
    
    public static int getDisjointSets() 
    {
        int max = 0;
        for (int i = 0; i < total_sets.size(); i++)
        {
        	disjoint_sets = findDisjointSets(total_sets.get(i));
        	max = Math.max(max, disjoint_sets.size());
        }

        return max;         
    }
    
    public static int getTotalSets() 
    {
        int ans = 0;
        for (int i = 0; i < cards.length; i++) 
        {
            for (int j = i+1; j < cards.length; j++) 
            {
                for (int k = j+1; k < cards.length; k++) 
                {
                    if (cards[i].isSet(cards[j], cards[k])) 
                    {
                        total_sets.add(new Set(cards[i], cards[j], cards[k]));
                        ans++;
                    }
                }
            }
        }
        
        return ans;        
    }
   
    
	public static void main(String[] args) {
		
		getInput();
		
		System.out.println(getTotalSets());
		
		System.out.println(getDisjointSets());
	
		System.out.println();
    	
    	for (int i = 0; i < disjoint_sets.size(); i++) 
    	{
            System.out.println(disjoint_sets.get(i).one.input);
            System.out.println(disjoint_sets.get(i).two.input);
            System.out.println(disjoint_sets.get(i).three.input);
            System.out.println();
        }		
		
	}

}
