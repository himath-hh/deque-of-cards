# Deque of Cards

## Overview

This project implements a **Deque of Cards** using a **Linked List** structure. It allows users to interact with the deck by adding, sorting, shuffling, and searching for cards. The program provides a command-line interface for user input and performs various operations on the deck.

## Features

- **Create a deck of cards** from user input
- **Display the deck**
- **Sort the deck** using Merge Sort
- **Shuffle the deck** using Faro Shuffle
- **Search for a card** using Binary Search
- **Exception Handling** for invalid inputs

## Classes

### 1. `Card`

- Represents a playing card with a **suit** (`C`, `H`, `S`, `D`) and **number** (`A`, `2-10`, `J`, `Q`, `K`).
- Implements `Comparable` to facilitate sorting.

### 2. `DequeException`

- Custom exception class to handle errors related to deque operations.

### 3. `Node`

- Represents a single node in the linked list, encapsulating a `Card` object.

### 4. `DequeOfCards`

- Manages the deck as a **double-ended queue (Deque)**.
- Supports adding cards, sorting, shuffling, and searching operations.

### 5. `Main`

- Provides a **CLI** interface for users to interact with the deck.
- Processes user commands and executes corresponding operations.

## How to Run

1. Compile all Java files:
   ```sh
   javac *.java
   ```
2. Run the program:
   ```sh
   java Main
   ```
3. Follow the prompts to enter your deck and execute commands.

## Usage

Upon running the program, you will be prompted to enter a deck of cards in the format:

```
C2 H5 S10 DQ
```

### Available Commands

- `display` → Prints the deck
- `sort` → Sorts the deck in order
- `shuffle` → Shuffles the deck using Faro Shuffle
- `search <card>` → Searches for a specific card (e.g., `search H10`)
- `exit` → Exits the program

## Example Interaction

```
Please indicate your deck:
> H10 S5 C3 DK

Deck created successfully.

Please type the desired operations:
- display
- sort
- shuffle
- search <card>
- exit

> display
H10 S5 C3 DK

> sort
Sorted Deck: C3 H10 S5 DK

> shuffle
Shuffled Deck: H10 DK S5 C3

> search S5
S5 is found at index 2

> exit
```

## Exception Handling

- **Invalid suits or numbers** will trigger `DequeException`.
- **Incorrect command usage** will return an error message.

## License

This project is licensed under the [MIT License](LICENSE.md).