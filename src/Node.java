/**
 * @author Himath Helessage
 * the Node class encompasses a card as a node in a linked list to implement a deck
 * a node holds a reference to a card object and the next object (single link)
 */
public class Node {
    //variable for a card object
    Card card;

    //reference to the next node in the deque/linked list
    Node nextCard;

    /**
     * constructor for a node with a card
     * @param card card object to encompass in the node
     */
    public Node(Card card) {
        this.card = card;
    }
}
