package com.example.word_guesser.services;

import com.example.word_guesser.models.Game;
import com.example.word_guesser.models.Guess;
import com.example.word_guesser.models.Reply;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GameService {

    private Game game;
    private String currentWord;
    private ArrayList<String> guessedLetters;

    public GameService(){ //default constructor for Spring to instantiate this

    }

    public Reply startNewGame(){
        this.game = new Game("hello");
        this.currentWord = "*****";
        this.guessedLetters = new ArrayList<>();
        Reply reply = new Reply(false,
                currentWord,
                "New game started");
        return reply;
    }

    public Reply processGuess(Guess guess){
        Reply reply;

        // Check if game has started
        if (game == null) {
            reply = new Reply(
                    false,
                    this.currentWord,
                    String.format("Game has not been started"));
            return reply;
        }

        // check if letter has already been guessed
        if (this.guessedLetters.contains(guess.getLetter())) {
            reply = new Reply(
                    false,
                    this.currentWord,
                    String.format("Already guessed %s", guess.getLetter()));
            return reply;
        }
        // add letter to previous guesses
        this.guessedLetters.add(guess.getLetter());
        // check for incorrect guess
        if (!game.getWord().contains(guess.getLetter())) {
            reply = new Reply(
                    false,
                    this.currentWord,
                    String.format("%s is not in the word", guess.getLetter()));
            return reply;
        }
        // process correct guess
        String runningResult = game.getWord();

        for (Character letter : game.getWord().toCharArray()) {
            if (!this.guessedLetters.contains(letter.toString())) {
                runningResult = runningResult.replace(letter, '*');
            }
        }

        this.currentWord = runningResult;

        reply = new Reply(
                true,
                this.currentWord,
                String.format("%s is in the word", guess.getLetter()));

        return reply;
    }


//  Getters & Setters
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public ArrayList<String> getGuessedLetters() {
        return guessedLetters;
    }

    public void setGuessedLetters(ArrayList<String> guessedLetters) {
        this.guessedLetters = guessedLetters;
    }
}
