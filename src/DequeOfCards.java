/**
 * @author Himath Helessage
 * the DequeOfCards class implements a deck with card objects where actions can be performed to it
 * methods to manipulate the deque are included here
 */
public class DequeOfCards {
    private Node head;  //first card in the deque (bottom card)
    private Node tail;  //last card in the deque (top card)

    /**
     * constructor to create an empty deque
     */
    public DequeOfCards() {
        head = null;
        tail = null;
    }

    /**
     * checks if the deque is empty
     * @return true/false for empty/not-empty respectively
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * finds the number of cards in the deque
     * @return size of the deque
     */
    public int size() {
        int size = 0;

        //this variable is used to point to the current node
        Node curCard = head;

        //traverses the deque until the last node is reached
        while (curCard != null) {
            //increments the size variable
            size++;

            //traverses to next node
            curCard = curCard.nextCard;
        }

        return size;
    }

    /**
     * adds a card to the bottom of the deque
     * @param card card to be added to the deque
     */
    public void addToBottom(Card card) {
        //places the card in a node
        Node newCard = new Node(card);

        //if the deque is empty, place points the head and tail to this card
        if (isEmpty()) {
            head = newCard;
            tail = newCard;
        
        } else {
            //points the new card's next node to the current head
            newCard.nextCard = head;

            //points the head to the new card
            head = newCard;
        }
    }

    /**
     * adds a card to the top of the deque
     * @param card card to be added to the deque
     */
    public void addToTop(Card card) {
        //places the card in a node
        Node newCard = new Node(card);

        //if the deque is empty, place points the head and tail to this card
        if (isEmpty()) {
            head = newCard;
            tail = newCard;

        } else {
            //points the current tail to the new card
            tail.nextCard = newCard;

            //points the tail to the new card
            tail = newCard;
        }
    }

    /**
     * removes the bottom card of the deque
     * @return card that was removed
     * @throws DequeException if no cards in deque
     */
    public Card removeBottom() {
        //checks for an empty deque
        if (isEmpty()) {
            throw new DequeException("Deque is empty");
        }

        //variable that stores the card to be removed
        Card removed = head.card;

        //points the head to the next card in the deque
        head = head.nextCard;

        //if after removal the deque is empty adjusts tail to null
        if (head == null) {
            tail = null;
        }

        return removed;
    }

    /**
     * removes the top card of the deque
     * @return card that was removed
     * @throws DequeException if no cards in deque
     */
    public Card removeTop() {
        //checks for an empty deque
        if (isEmpty()) {
            throw new DequeException("Deque is empty");
        }

        //variable that stores the card to be removed
        Card removed = tail.card;
        
        //traverses to the end of the deque
        Node curCard = head;
        while (curCard.nextCard != tail) {
            curCard = curCard.nextCard;
        }

        //sets the card before the last to point to null
        curCard.nextCard = null;

        //makes the card before the last the tail
        tail = curCard;

        return removed;
    }

    /**
     * clears all cards from the deque
     */
    public void clearDeque() {
        head = null;
        tail = null;
    }

    /**
     * converts the deque into an array
     * @return an array of cards
     */
    public Card[] toArray() {
        //the array of type Card that is the same size as the deque
        Card[] dequeArray = new Card[size()];

        //variable that will traverse through the deque
        Node curCard = head;

        //iterates through the deque, adding each card to the array
        for (int i = 0; i < size(); i++) {
            dequeArray[i] = curCard.card;
            curCard = curCard.nextCard;
        }

        return dequeArray;
    }

    /**
     * sorts the array using merge sort
     * @return sorted array of cards
     */
    public Card[] toSortedArray() {
        return mergeSort(toArray());
    }

    /**
     * sorts an array of cards using merge sort
     * @param array array to sort
     * @return sorted array
     */
    private Card[] mergeSort(Card[] array) {
        //base case if there is only 1 element in the array
        if (array.length == 1) {
            return array;
        
        } else {
            //mid point of the array based on it's size
            int mid = array.length / 2;

            //left part of the array
            Card[] left = new Card[mid];

            //assigns the values from the left side to the new array
            for (int i = 0; i < mid; i++) {
                left[i] = array[i];
            }

            //right part of the array
            Card[] right = new Card[array.length - mid];

            //assigns the values from the right side to the new array
            for (int i = mid; i < array.length; i++) {
                right[i - mid] = array[i];
            }

            //recursive calls for the divided arrays
            left = mergeSort(left);
            right = mergeSort(right);

            //merges the arrays while sorting the elements
            array = merge(left, right);
            return array;
        }
    }
    
    /**
     * helper method for merge sort that merges arrays by sorting
     * @param left first array
     * @param right second array
     * @return sorted array
     */
    private Card[] merge(Card[] left, Card[] right) {
        //result array that is the size of the first and second arrays combined
        Card[] result = new Card[left.length + right.length];

        int leftPtr = 0;    //left array index
        int rightPtr = 0;   //right array index
        int i = 0;          //result array index

        //loops while the end of the sub arrays are not reached
        while (leftPtr < left.length && rightPtr < right.length) {
            //compares cards and places them in the result array, smaller order first
            if (left[leftPtr].compareTo(right[rightPtr]) < 0) {
                result[i] = left[leftPtr];

                //increments the left index
                leftPtr++;
            
            } else {
                result[i] = right[rightPtr];

                //increments the right index
                rightPtr++;
            }

            //moves to the next result array index
            i++;
        }

        //if any elements of the left array were left they are placed in the result array
        while (leftPtr < left.length) {
            result[i] = left[leftPtr];

            //moves to next element in result array and left array
            i++;
            leftPtr++;
        }

        //if any elements of the right array were left they are placed in the result array
        while (rightPtr < right.length) {
            result[i] = right[rightPtr];

            //moves to next element in result array and right array
            i++;
            rightPtr++;
        }

        return result;
    }

    /**
     * performs a binary search for a card
     * @param suit suit of the card to search for
     * @param number number of the card to search for
     * @return index of the card, -1 if not found
     */
    public int binarySearch(char suit, String number) {
        //creates a sorted array to search in
        Card[] sortedDequeArray = toSortedArray();

        //constructs a card that needs to be searched for
        Card searchCard = new Card(suit, number);

        //start and end indexes for the array
        int start = 0;
        int end = size() - 1;

        //performs the search while start value doesn't reach the end
        while (start <= end) {
            //mid points the the middle index of the array
            int mid = (start + end) / 2;
            Card midCard = sortedDequeArray[mid];

            //compares the middle card to the search card
            if (midCard.compareTo(searchCard) == 0) {
                return mid;
            
            //if middle card is of lesser order, right side is searched
            } else if (midCard.compareTo(searchCard) < 0) {
                start = mid + 1;
            
            //if middle card is of higher order, left side is searched
            } else {
                end = mid - 1;
            }
        }

        //card was not found
        return -1;
    }

    /**
     * performs a faro shuffle on the deque of cards
     */
    public void shuffle() {
        //creates two new temporary deques to split the deque
        DequeOfCards firstHalf = new DequeOfCards();
        DequeOfCards secondHalf = new DequeOfCards();

        //variable for the deque size
        int size = size();

        //node that points to the current card
        Node curCard = head;

        //loops through the deque
        for (int i = 0; i < size; i++) {
            //places first half in the first deque
            if (i < size / 2) {
                firstHalf.addToTop(curCard.card);
            
            //places second half in the second deque
            } else {
                secondHalf.addToTop(curCard.card);
            }

            //traverses to the next card
            curCard = curCard.nextCard;
        }

        //empties the deque to place shuffled cards
        clearDeque();

        //flag that decides if the first half is bigger than second half to ensure that no card is left unshuffled for odd-numbered deques
        //the flag determines an "in-shuffle" or an "out-shuffle" to ensure that no card from the same half are next to each other
        boolean weaveSize = firstHalf.size() > secondHalf.size();

        for (int i = 0; i < size; i++) {
            //if first half is bigger then the card from the first half is placed first
            if (weaveSize) {
                //check done to ensure the deques aren't empty
                if (!firstHalf.isEmpty()) {
                    //removes the botttom card from the first half and places it on top of the deque
                    addToTop(firstHalf.removeBottom());
                }

                if (!secondHalf.isEmpty()) {
                    //removes the botttom card from the second half and places it on top of the deque
                    addToTop(secondHalf.removeBottom());
                }
            
            //if fsecondhalf is bigger then the card from the second half is placed first
            } else {
                //check done to ensure the deques aren't empty
                if (!secondHalf.isEmpty()) {
                    //removes the botttom card from the second half and places it on top of the deque
                    addToTop(secondHalf.removeBottom());
                }

                if (!firstHalf.isEmpty()) {
                    //removes the botttom card from the first half and places it on top of the deque
                    addToTop(firstHalf.removeBottom());
                }
            }
        }
    }

    /**
     * returns the deque as a string card by card
     * @return the deque as cards one after another
     */
    @Override
    public String toString() {
        Node curCard = head;
        String output = "";

        //traverses through the deque obtaining the string value of each card with space between each other
        while (curCard != null) {
            output += curCard.card.toString() + " ";
            curCard = curCard.nextCard;

        }
        
        return output;
    }

    /**
     * BONUS --unused
     * sorts the array using quick sort
     * @return sorted array of cards
     */
    public Card[] sortQuick() {
        Card[] DequeArray = toArray();
        quickSort(DequeArray, 0, size() - 1);

        return DequeArray;
    }

    /**
     * sorts an array of cards using quick sort
     * @param cards array of cards
     * @param start start index
     * @param end end index
     */
    private void quickSort(Card[] cards, int start, int end) {
        //base case return
        if (end <= start) {
            return;
        }

        //obtains the pivot
        int pivot = partition(cards, start, end);

        //recursively calls quick sort until fully sorted
        quickSort(cards, start, pivot - 1);
        quickSort(cards, pivot + 1, end);
    }

    /**
     * helper method for quick sort
     * @param cards array of cards
     * @param start start index
     * @param end end index
     * @return location of the pivot
     */
    private int partition(Card[] cards, int start, int end) {
        //end card is used as the pivot
        Card pivot = cards[end];

        //will hold the final location of the pivot
        int i = start - 1;

        //loops through the array from start to end index
        for (int j = start; j < end; j++) {
            //compares card at index j to pivot and performs a swap, smaller order first
            if (cards[j].compareTo(pivot) < 0) {
                //index is incremented to point to the next swap location
                i++;

                //swaps index i and j elements
                Card temp = cards[i];
                cards[i] = cards[j];
                cards[j] = temp;
            }
        }

        //final location of the pivot
        i++;

        //pivot is set in place with a swap
        Card temp = cards[i];
        cards[i] = cards[end];
        cards[end] = temp;

        return i;
    }
}
