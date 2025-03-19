/**
 * @author Himath Helessage
 * the Card class represents a card with a suit and number
 * implements Comparable to order cards in a deque
 */
public class Card implements Comparable<Card> {
    //variables to represent suit, card number and comparison order
    private char suit;
    private String number;
    private int order;

    /**
     * constructor to create a Card with a suit and number
     * @param suit suit of the Card ('C', 'H', 'S', or 'D')
     * @param number value of the Card ('A', 2 - 10, 'J', 'Q', 'K')
     */
    public Card(char suit, String number) {
        this.suit = suit;

        //order is determined by giving a numeric value based on suit
        switch (suit) {
            //order 'C', 'H', 'S', 'D' in hundreds
            case 'C':
                this.order = 100;
                break;

            case 'H':
                this.order = 200;
                break;

            case 'S':
                this.order = 300;
                break;

            case 'D':
                this.order = 400;
                break;

            //throws an exception for an invalid suit character
            default:
                throw new DequeException("Suit is not recognized");
        }

        this.number = number;

        //value is determined by giving a smaller numeric value which does not overpower suit
        switch (number) {
            //value of A is 1
            case "A":
                this.order += 1;
                break;

            //'J', 'Q', 'K' are ordered 11, 12 and 13
            case "J":
                this.order += 11;
                break;

            case "Q":
                this.order += 12;
                break;

            case "K":
                this.order += 13;
                break;

            
            default:
                //if number is not within suit, throws exception
                if (Integer.parseInt(number) > 10) {
                    throw new DequeException("Card Value is not valid");
                }

                //parses the number as an integer and passes it into order
                this.order += Integer.parseInt(number);
                break;
        }
    }

    /**
     * getter for suit
     * @return suit
     */
    public char getSuit() {
        return suit;
    }

    /**
     * getter for card number/ value
     * @return number/ value
     */
    public String getNumber() {
        return number;
    }

    /**
     * getter for order value
     * @return order of card
     */
    public int getOrder() {
        return order;
    }
    
    /**
     * compares one card to another based on order
     * @param otherCard the card to compare against
     * @return order comparison result
     */
    @Override
    public int compareTo(Card otherCard) {
        //if this card is higher returns 1
        if (this.order > otherCard.order) {
            return 1;
        
        //if other card is higher returns -1
        } else if (this.order < otherCard.order) {
            return -1;
        
        //if equal returns 0
        } else {
            return 0;
        }
    }

    /**
     * returns string representation of a card
     * format: <suit><number> e.g.: H2
     * @return string formatted card
     */
    @Override
    public String toString() {
        return (suit + number);
    }
}
