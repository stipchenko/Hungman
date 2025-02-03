# Hangman Game

This is a simple Java implementation of the classic Hangman game. The game selects a random word from a file and the player must guess the word by entering letters. Each incorrect guess draws a part of the hangman figure. The game ends either when the player guesses the word correctly or when they run out of tries.

## Features

- **Game Menu**: The player can choose to start a new game or exit.
- **Random Word Selection**: The game selects a random word from a text file (`file.txt`), which should contain one word per line.
- **Letter Guessing**: The player enters a letter, and the program checks if the letter exists in the word. Correct letters are revealed, and incorrect guesses result in a part of the hangman being drawn.
- **Hangman Drawing**: The program draws the hangman figure after each incorrect guess, and the game continues until the player either guesses the word or runs out of tries.
- **Input Validation**: The player is informed of repeated guesses and tries to ensure valid input.

## Game Flow

1. **Start Menu**: The user is prompted with two options:
   - Option 1: Start a new game.
   - Option 2: Exit the game.
   
2. **Word Selection**: A random word is chosen from the `file.txt` located in the `package` directory.

3. **Gameplay**:
   - The word is hidden with asterisks (e.g., `*****` for a 5-letter word).
   - The player guesses a letter.
   - If the letter is correct, the letter is revealed in the word.
   - If the letter is incorrect, a part of the hangman figure is drawn, and the number of attempts decreases.
   
4. **End of Game**:
   - The player wins if the word is fully guessed before running out of attempts.
   - The player loses if they run out of attempts (6 incorrect guesses).
   
5. **Hangman Figure**: The game draws different stages of the hangman figure as the player makes incorrect guesses.


