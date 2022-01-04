
public class Card {

	String colour;
    Character symbol;
    String shading;
    int number;
    String input;
    
    
	public Card(String c, Character sym, String shade, int num, String in) 
	{		
		colour = c;
		symbol = sym;
		shading = shade;
		number = num;		
		input = in;
	}

	public boolean isCardMatching(Card one) {
        return (colour.equals(one.colour) && number == one.number && shading.equals(one.shading) && symbol == one.symbol);
    }
	
	public boolean isSet(Card one, Card two) 
	{
        return (isColourMatching(one,two) && isSymbolMatching(one,two) && isShadingMatching(one,two) && isNumberMatching(one,two));
    }

	private boolean isNumberMatching(Card one, Card two) {
		return ((number == one.number) && (number == two.number)) || ((number != one.number) && (number != two.number) && (one.number != two.number));
	}

	private boolean isShadingMatching(Card one, Card two) {
		return (shading.equals(one.shading) && shading.equals(two.shading)) || (!shading.equals(one.shading) && !shading.equals(two.shading) && !one.shading.equals(two.shading));
	}

	private boolean isSymbolMatching(Card one, Card two) {
		return (symbol.equals(one.symbol) && symbol.equals(two.symbol)) || (!symbol.equals(one.symbol) && !symbol.equals(two.symbol) && !one.symbol.equals(two.symbol));
	}

	private boolean isColourMatching(Card one, Card two) {
		return (colour.equals(one.colour) && colour.equals(two.colour)) || (!colour.equals(one.colour) && !colour.equals(two.colour) && !one.colour.equals(two.colour));
	}
	
}
