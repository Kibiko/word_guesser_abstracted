package com.example.word_guesser.services;

import com.example.word_guesser.models.Game;
import com.example.word_guesser.models.Guess;
import com.example.word_guesser.models.Reply;
import com.example.word_guesser.repositories.GameList;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GameService {

    @Autowired
    WordService wordService;

    @Autowired
    GameList gameList;

//    private String currentWord;
//    private ArrayList<String> guessedLetters;

    public GameService(){ //default constructor for Spring to instantiate this

    }

    public Reply startNewGame(){
        String targetWord = this.wordService.getRandomWord();
        Game game = new Game(targetWord);
        this.gameList.addGame(game);
//        this.currentWord = Strings.repeat("*",targetWord.length());
//        this.guessedLetters = new ArrayList<>();
        Reply reply = new Reply(false,
                game.getCurrentWord(),
                String.format("New game started with id %d", game.getId()));
        return reply;
    }

    public Reply processGuess(Guess guess, int id){
        Reply reply;

        Game game = gameList.getGameById(id);
        // Check if game has started
        if (game == null) {
            reply = new Reply(
                    false,
                    game.getCurrentWord(),
                    String.format("Game has not been started"));
            return reply;
        }

        // check if letter has already been guessed
        if (game.getGuessedLetters().contains(guess.getLetter())) {
            reply = new Reply(
                    false,
                    game.getCurrentWord(),
                    String.format("Already guessed %s", guess.getLetter()));
            return reply;
        }
        // add letter to previous guesses
        game.getGuessedLetters().add(guess.getLetter());
        // check for incorrect guess
        if (!game.getWord().contains(guess.getLetter())) {
            reply = new Reply(
                    false,
                    game.getCurrentWord(),
                    String.format("%s is not in the word", guess.getLetter()));
            return reply;
        }
        // process correct guess
        String runningResult = game.getWord();

        for (Character letter : game.getWord().toCharArray()) {
            if (!game.getGuessedLetters().contains(letter.toString())) {
                runningResult = runningResult.replace(letter, '*');
            }
        }

        game.setCurrentWord(runningResult);

        reply = new Reply(
                true,
                game.getCurrentWord(),
                String.format("%s is in the word", guess.getLetter()));

        return reply;
    }


//  Getters & Setters

//    public String getCurrentWord() {
//        return currentWord;
//    }
//
//    public void setCurrentWord(String currentWord) {
//        this.currentWord = currentWord;
//    }

//    public ArrayList<String> getGuessedLetters() {
//        return guessedLetters;
//    }
//
//    public void setGuessedLetters(ArrayList<String> guessedLetters) {
//        this.guessedLetters = guessedLetters;
//    }
}
