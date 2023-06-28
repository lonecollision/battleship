package battleship;

public class Main {
    public static void main (String[] args){
        final UserInputHelper input = new UserInputHelper();
        final Game play = new Game();
        System.out.println(play.generateWelcome());
        play.shipSetup();
        play.game();
        input.cleanup();
    }
}