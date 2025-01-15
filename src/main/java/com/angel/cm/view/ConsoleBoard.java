package com.angel.cm.view;

import com.angel.cm.errors.ExplosionException;
import com.angel.cm.errors.OutException;
import com.angel.cm.model.Board;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class ConsoleBoard {

    private Board board;
    private Scanner s = new Scanner(System.in);

    public ConsoleBoard (Board board) {
        this.board = board;

        executeGame();
    }

    private void executeGame () {
        try {
            boolean continuee = true;

            while (continuee) {
                gameCycle();

                System.out.println("Outra partida? (S/n) ");
                String typed = s.nextLine();

                if ("no".equalsIgnoreCase(typed)) {
                    continuee = false;
                } else {
                    board.rebootGameBoard();
                }
            }

        } catch (OutException e) {
            System.out.println("OUT!");
        } finally {
            s.close();
        }
    }

    private void gameCycle () {
        try {

            while (!board.objectiveAchievedBoard()) {
                System.out.println(board);

                String typed = captureValueTyped("Insert (x,y): ");

                Iterator<Integer> xy = Arrays.stream(typed.split(",")).map(e -> Integer.parseInt(e.trim())).iterator();

                typed = captureValueTyped("1 - Open or 2 - Marked");

                if ("1".equalsIgnoreCase(typed)) {
                    board.openBoard(xy.next(), xy.next());
                } else if ("2".equalsIgnoreCase(typed)) {
                    board.markedBoard(xy.next(), xy.next());
                }
            }
            System.out.println(board);
            System.out.println("You win!");
        } catch (ExplosionException e) {
            System.out.println(board);
            System.out.println("You loose");
        }
    }

    private String captureValueTyped (String text) {
        System.out.println(text);
        String typed = s.nextLine();

        if ("out".equalsIgnoreCase(typed)) {
            throw new OutException();
        }

        return typed;

    }
}
