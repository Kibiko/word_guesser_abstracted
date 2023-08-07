package com.example.word_guesser.models;

import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;

public class Game {

    private static int nextId = 1;

    private int id;
    private String word;
    private int guesses;
    private boolean complete;
    private String currentWord;
    private ArrayList<String> guessedLetters;

    public Game(String word) {
        this.id = this.nextId;
        this.nextId ++;
        this.word = word;
        this.guesses = 0;
        this.complete = false;
        this.currentWord = Strings.repeat("*",word.length());
        this.guessedLetters = new ArrayList<>();
    }

    public Game() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getGuesses() {
        return guesses;
    }

    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
