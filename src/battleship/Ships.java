package battleship;

public class Ships {
    private final char identifier;
    private final int shipLength;
    private boolean status;
    private final String name;
    private int shipLives;
    // Ship constructor
    public Ships(String name, char identifier, int shipLength){
        this.name = name;
        this.identifier = identifier;
        this.shipLength = shipLength;
    }
    // Displays ship statistics
    public void shipStatistics() {
        System.out.println(status ? name + ": Alive" : name + ": Destroyed");
    }
    /*
     * Places ship randomly, horizontally or vertically
     */
    public void placeShip(char[][] board, int shipLength, char shipIdentifier, char sea) {
        boolean added = false;
        while(!added) {
            // Random 1 or 0, where 1 is true, 0 is false (used to determine whether to place ship horizontally or not)
            boolean horizontal = (int)Math.round(Math.random()) % 2 == 0;
            // Random value within limits of the X axis
            int x_axis = (int)(board.length * Math.random());
            // Random value within limits of the Y axis
            int y_axis = (int)(board.length * Math.random());
            // Boolean to determine whether there is enough space for a ship to be placed in the direction its going
            boolean hasSpace = true;
            // If horizontal is true
            if(horizontal){
                // Check axis to see if there is enough vertical space, if not hasSpace is set to false
                for(int i = 0; i < shipLength; i++) {
                    if (y_axis + i >= board[0].length) {
                        hasSpace = false;
                        break;
                    }
                    if (board[x_axis][y_axis+i] != sea) {
                        hasSpace = false;
                        break;
                    }
                }
                // If there is no space, skip the current iteration
                if(!hasSpace){
                    continue;
                }
                // If there is space on the axis place ship
                else {
                    for (int i = 0; i< shipLength; i++) {
                        board[x_axis][y_axis+i] = shipIdentifier;
                    }
                }
            }
            // If horizontal is false
            else {
                // Check axis to see if there is enough horizontal space, if not hasSpace is set to false
                for(int i = 0; i < shipLength; i++) {
                    if (x_axis + i >= board[0].length) {
                        hasSpace = false;
                        break;
                    }
                    if (board[x_axis+i][y_axis] != sea) {
                        hasSpace = false;
                        break;
                    }
                }
                // If there is no space, skip the current iteration
                if(!hasSpace){
                    continue;
                }
                // If there is space on the axis place ship
                else {
                    for (int i = 0; i< shipLength; i++) {
                        board[x_axis+i][y_axis] = shipIdentifier;
                    }
                }
            }
            // Ends while loop
            added = true;
        }
    }
    // Detects whether a ship is down or not by determining whether it's lives are below or equal to one
    public int shipDownDetector() {
        if(shipLives <= 1) {
            int bonusScore = shipLength*2;
            System.out.printf("You sank my " + name + "! +%s Point%n", bonusScore);
            status = false;
            return bonusScore;
        }
        else {
            shipLives--;
            return 0;
        }
    }
    // Getters/setters
    public int getShipLength() {
        return shipLength;
    }
    public char getShipIdentifier() {
        return identifier;
    }
    public void setStatus (boolean status) {
        this.status = status;
    }
    public void setShipLives() {
        this.shipLives = shipLength;
    }
}