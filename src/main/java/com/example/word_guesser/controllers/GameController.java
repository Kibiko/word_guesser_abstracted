package com.example.word_guesser.controllers;

import com.example.word_guesser.models.Game;
import com.example.word_guesser.models.Guess;
import com.example.word_guesser.models.LetterList;
import com.example.word_guesser.models.Reply;
import com.example.word_guesser.repositories.GameList;
import com.example.word_guesser.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    GameList gameList;

    @PostMapping
    public ResponseEntity<Reply> startNewGame() {
        Reply reply = gameService.startNewGame();
        return new ResponseEntity<>(reply, HttpStatus.CREATED);
    }

    @GetMapping (value = "/{gameId}")
    public ResponseEntity<Reply> getGameStatus(@PathVariable int gameId) {
        Reply reply;
        // Check if game has started
        if (gameList.getGameById(gameId) == null) {
            reply = new Reply(
                    false,
                    gameList.getGameById(gameId).getCurrentWord(),
                    "Game has not been started");
        } else {
            reply = new Reply(false,
                    gameList.getGameById(gameId).getCurrentWord(),
                    "Game in progress.");
        }
        return new ResponseEntity<>(reply, HttpStatus.OK);
    }

    @PatchMapping(value = "/{gameId}") // PATCH localhost:8080/games/2 <- id
    public ResponseEntity<Reply> handleGuess(@RequestBody Guess guess, @PathVariable int gameId) {
        Reply reply = gameService.processGuess(guess, gameId);
        return new ResponseEntity<>(reply, HttpStatus.OK);
    }

    @GetMapping(value = "{gameId}/guessed")
    public ResponseEntity<LetterList> checkGuesses(@PathVariable int gameId) {
        ArrayList<String> guesses = gameList.getGameById(gameId).getGuessedLetters();
        LetterList letters = new LetterList(guesses);
        return new ResponseEntity<>(letters, HttpStatus.OK);
    }
}
