package battleship;

import java.util.ArrayList;

public class Game {
    private static final GameBoard board = new GameBoard(10);
    private static final ArrayList<Ships> ships = new ArrayList<>();
    private static final Player player = new Player();
    private static final UserInputHelper input = new UserInputHelper();
    // Constructor for ships
    public void shipSetup() {
        ships.add(new Ships("Patrol Boat", 'p', 2));
        ships.add(new Ships("Submarine", 's', 3));
        ships.add(new Ships("Destroyer", 'd', 3));
        ships.add(new Ships("Battleship", 'b', 4));
        ships.add(new Ships("Aircraft Carrier", 'a', 5));
    }
    // Determines whether to exit or play again
    private void playAgain() {
        String playAgain = input.continueInput("Y","N");
        if(playAgain.equals("Y")) {
            player.reset();
            game();
        }
        else {
            exitChoice();
        }
    }
    // Used in option 1, groups up X and Y input
    private int[] coordinates() {
        int x = input.boardInput(board.getBoardLength(), "X");
        int y = input.boardInput(board.getBoardLength(), "Y");
        return new int[] {y, x};
    }
    // Option 1, to enter coordinates
    private void coordinatesChoice(char[][] gameBoard) {
        int[] guess = coordinates();
        char target = gameBoard[guess[0]][guess[1]];
        char targetUpdate = player.compareShot(target, board.getSea());
        for (Ships shipType : ships) {
            if (target == shipType.getShipIdentifier()) {
                System.out.println();
                int bonusScore = shipType.shipDownDetector();
                player.addScore(bonusScore);
            }
        }
        board.updateBoard(guess, targetUpdate);
        board.printBoard(player.getHit(), player.getMiss());
        System.out.println(player.playerStatistics());
        for (Ships shipType : ships) {
            shipType.shipStatistics();
        }
    }
    // Option 2, checks if player has enough radars to use the radar functionality
    private void radarChoice(char[][] gameBoard) {
        if (player.getRadarCount() > 0) {
            System.out.println(player.radar(coordinates(), gameBoard, board.getSea()));
        } else {
            System.out.println("\nYou have used up all your radars!");
        }
    }
    // Guide string (Option 3)
    @SuppressWarnings("preview")
	private String guideChoice() {
        return """
                Hello and welcome to Battleship!
                On the board there are 5 ships.

                You play by guessing where the following types of ships are on the board:
                 - Aircraft carrier (5 units long)
                 - Battleship (4 units long)
                 - Submarine (3 units long)
                 - Destroyer (3 units long)
                 - Patrol Boat (2 units long)

                You have 4 radars that detect ships within a 1 unit radius, ensure that radars
                are placed within an 8x8 perimeter as anything 1-unit outside would also be detected.
                You are also equipped with 50 missiles, if you are unable to destroy all ships with these missiles you will lose, so use them wisely!
                Lastly, scoring anything under -20 will result in game over.
                """;
    }
    //Option 4, exits game
    private void exitChoice() {
        System.out.println("Thank you for playing Battleship! Have a lovely day :)\nExiting..");
        System.exit(0);
    }
    // String welcome message
    @SuppressWarnings("preview")
	public String generateWelcome() {
        return """
               Hello and welcome to Battleship Variant A!

               There are 5 hidden ships on the board!
               You must destroy them without reaching a score lower than -20.
               You have a total 50 missiles alongside 4 radars, use them wisely!
               """;
    }
    // String main menu
    private String generateMenu() {
        return "Choose an option:\n1) Coordinates\n2) Radar\n3) Guide\n4) Exit\n";
    }
    // Combines all functionalities,
    public void game() {
        board.createGameBoard();
        char[][] gameBoard = board.getBoard();
        for (Ships shipType : ships) {
            shipType.setStatus(true);
            shipType.setShipLives();
            shipType.placeShip(board.getBoard(), shipType.getShipLength(), shipType.getShipIdentifier(), board.getSea());
        }
        board.printBoard(player.getHit(),player.getMiss());
        while(player.getHits() != 17) {
            System.out.println(generateMenu());
            if(player.getMissileCount() <= 0 || player.getScore() < -20) {
                player.loses();
                playAgain();
            }
            int menuOption = input.menuInput(4);
            if (menuOption == 1) {
                coordinatesChoice(gameBoard);
                System.out.println();
            }
            else if(menuOption == 2) {
                radarChoice(gameBoard);
                System.out.println();
            }
            else if(menuOption == 3) {
                System.out.println(guideChoice());
            }
            else {
                exitChoice();
            }
        }
        player.wins();
        playAgain();
    }
}