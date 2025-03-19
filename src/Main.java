import java.util.Scanner;

/**
 * @author Himath Helessage
 * the Main class allows for user interaction with the deque
 * user can provide comamnds to perform actions to the deque
 */
public class Main {
    /**
     * main method that runs the program
     * @param args command-line arguments - not used here
     */
    public static void main(String[] args) {
        //scanner to collect inputs
        Scanner input = new Scanner(System.in);

        //creates a new empty deque
        DequeOfCards dequeOfCards = new DequeOfCards();

        //prompts the user to enter cards for the deck seperate by a space
        System.out.println("Please indicate your deck:");
        System.out.print("> ");

        //the input is split from the spaces and lowercase cards are converted to uppercase to better handle input
        String deckLine = input.nextLine().toUpperCase();
        String[] cards = deckLine.split(" ");

        //loops through all the cards
        for (String card : cards) {
            //if condition checks for small rogue values during splitting and omits them
            if (card.length() > 1) {
                try {
                    //adds the card to the deque, where the first character is the suit and the following is the number value
                    dequeOfCards.addToTop(new Card(card.charAt(0), card.substring(1)));
                
                //catches bad cards
                } catch (Exception e) {
                    System.err.println("Invalid Card: " + card);
                }
            }
        }

        boolean exit = false;

        //checks for atleast one card in the deque to run the program
        if (dequeOfCards.size() < 1) {
            exit = true;
            System.out.println("\nDeck was not created");
        } else {
            //deck creation confirmation message
            System.out.println("\nDeck created successfully.");

        }

        //loops until user wants to exit
        while (!exit) {
            //main menu display
            System.out.println("\n Please type the desired operations:");
            System.out.println("- display: Print the deck");
            System.out.println("- sort: Sort the deck");
            System.out.println("- shuffle: Shuffle the deck");
            System.out.println("- search <card>: Search for a card");
            System.out.println("- exit: Exit the program");

            //user inputs choice
            System.out.print("\n> ");
            String operation = input.nextLine().trim().toLowerCase();
            System.out.println();

            //exit flag is set and program terminates
            if (operation.equals("exit")) {
                exit = true;
            
            //command to display the deque
            } else if (operation.equals("display")) {
                //prints the deque using its toString method
                System.out.println(dequeOfCards.toString());

            //command to sort the deque
            } else if (operation.equals("sort")) {
                System.out.print("Sorted Deck: ");

                //sorts the deque using merge sort
                Card[] sortedDeque = dequeOfCards.toSortedArray();

                //sorts the deque using quick sort (UNUSED)
                //Card[] sortedDeque = dequeOfCards.sortQuick();

                //prints the array of sorted cards
                for (Card card : sortedDeque) {
                    System.out.print(card.toString() + " ");
                }

                System.out.println();

            //command to shuffle the deque
            } else if (operation.equals("shuffle")) {
                System.out.println("Performing faro shuffle...");
                
                //performs the faro shuffle and displays the cards
                dequeOfCards.shuffle();
                System.out.println("Shuffled Deck: " + dequeOfCards.toString());
            
            //command to search for a card
            } else if (operation.startsWith("search")) {
                //splits the command on spaces
                String[] command = operation.split(" ");

                try {
                    //obtains the card to be searched for
                    command[1] = command[1].toUpperCase();

                    //places suit and number in variables
                    char suit = command[1].charAt(0);
                    String number = command[1].substring(1);

                    //performs a binary search
                    int i = dequeOfCards.binarySearch(suit, number);

                    //card was not found
                    if (i == -1) {
                        System.out.println("Card not found");
                    
                    //prints card location
                    } else {
                        System.out.println(command[1] + " is found at index " + i);
                    }

                //catches bad search commands
                } catch (Exception e){
                    System.err.println("Error: " + e.getMessage());
                }
            
            //catches rogue input commands
            } else {
                System.err.println("Command unrecognized, try again");
            }
        }

        //closes scanner used for input
        input.close();
    }
}
