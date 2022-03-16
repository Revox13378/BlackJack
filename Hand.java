import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.Arrays;

/**
 * @author Frederik
 */
public class Hand
{
    List<Card> cards;
    boolean splitted;
    int bet;
    boolean finished;
    
    public Hand(List<Card> cards, int bet, boolean splitted, boolean finished) {
    	this.cards = cards; 
    	this.bet = bet;
    	this.splitted = splitted;
    	this.finished = finished;
    }

    public Hand split() {
        
    	// checks if the card values of the Cards are the same 
    	if (this.cards.stream().map(card -> card.getCardValue()).collect(Collectors.toSet()).size() == 1
    			&& this.cards.size() == 2) { // max 2 cards allowed in hand in order to split
    				return new Hand(Arrays.asList(cards.get(0)), this.bet, true, false);
		} else {
			return null;
		}
    }
    
    public int calculateValue() {
         Stream<Card.CardValue> cardValueStream = cards.stream().map(card -> card.getCardValue());
         
         int simpleValue = cardValueStream.mapToInt(cv -> cv.getValue()).sum();
         if (simpleValue <= 21) {
        	 return simpleValue;
         }
        //TODO 
         
         int aceAmount = (int) cardValueStream.filter(cv -> cv.equals(Card.CardValue.Ass)).count();
       
         
         return 0;
    }
    
    public List<Card> getCards() {
    	return cards;
    }
}