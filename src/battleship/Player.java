package battleship;

public class Player {
    private int score = 0;
    private int radarCount = 4;
    private int hits = 0;
    private int misses = 0;
    private int missileCount = 50;
    private final char hit = 'H';
    private final char miss = 'M';
    // Constructor
    public Player(){
    }
    // Resets player stats, used when restarting game
    public void reset() {
        score = 0;
        radarCount = 4;
        hits = 0;
        misses = 0;
        missileCount = 50;
    }
    // Displays winning message
    public void wins() {
        System.out.printf("Congratulations! You have won with a score of %s..." +
                          "%nAlong with a hit to miss ratio of %s:%s%n", score, hits, misses);
    }
    // Displays loss message
    public void loses() {
        System.out.printf("You have lost!%nYou've ended with a score of %s" +
                          "%nAlong with a hit to miss ratio of %s:%s%n", score, hits, misses);
    }
    // Displays play statistics
    public String playerStatistics() {
        return "Score: " + score + "\nMissiles left: " + missileCount;
    }
    // Adds to score
    public void addScore(int value) {
        score += value;
    }
    // Penalty for missing a shot
    private void missPenalty() {
        score--;
        misses++;
    }
    // Compares user's shot to determine if it was a hit or miss
    public char compareShot(char target, char sea){
        System.out.println();
        String message;
        if(target != sea && target != hit && target != miss) {
            message = "My ship was hit! +1 Point";
            target = hit;
            score++;
            hits++;
        }
        else if(target == sea) {
            message = "You missed! -1 Point\n";
            target = miss;
            missPenalty();
        }
        else {
            message = "Already hit! -1 Point\n";
            missPenalty();
        }
        missileCount--;
        System.out.println(message);
        return target;
    }
    // Radar functionality, uses the user's guess and compares it to positions around it
    public String radar(int[] guess, char[][] board, char sea) {
        String message = null;
        int row = guess[0];
        int column = guess[1];
        char target = board[row][column];
        try {
            char west = board[row - 1][column];
            char east = board[row + 1][column];
            char north = board[row][column + 1];
            char south = board[row][column - 1];
            char NEdiagonal = board[row + 1][column + 1];
            char SWdiagonal = board[row - 1][column - 1];
            char NWdiagonal = board[row + 1][column - 1];
            char SEdiagonal = board[row - 1][column + 1];
            char[] directions = {target, west, east, north, south, NEdiagonal, SWdiagonal, NWdiagonal, SEdiagonal};
            for(char dir : directions) {
                if(dir != sea && dir != hit && dir != miss) {
                    message = "There is a ship within a 1-square distance.";
                    break;
                }
                else {
                    message = "There are no ships within a 1-square distance.";
                }
            }
            radarCount--;
        }
        catch (Exception e) {
            message = "Radar has been placed out of bounds!";
        }
        System.out.println();
        System.out.println("You have " + radarCount + " radars left!");
        System.out.println();
        return message;
    }
    // Getters
    public int getMissileCount() {
        return missileCount;
    }
    public int getScore(){
        return score;
    }
    public int getRadarCount() {
        return radarCount;
    }
    public char getHit() {
        return hit;
    }
    public char getMiss() {
        return miss;
    }
    public int getHits() {
        return hits;
    }
}