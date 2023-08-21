import java.util.Random;

public abstract class RPSAbstract implements RPSInterface {
    protected static final int SEED = 12;
    protected final Random rand = new Random(SEED);

    // The moves allowed in this version of RPS
    protected String[] possibleMoves;

    // The number of games, player wins, CPU wins and ties
    protected int numGames;
    protected int numPlayerWins;
    protected int numCPUWins;
    protected int numTies;

    // The complete history of the moves
    protected String[] playerMoves;
    protected String[] cpuMoves;

    // The default moves.  Use for the basic implementation of the game.
    protected static final String[] DEFAULT_MOVES = {"scissors", "paper",
            "rock"};

    /* Important: Use the following constants to avoid output matching issues
       and magic numbers! */

    // Messages for the game.
    protected static final String INVALID_INPUT =
            "That is not a valid move. Please try again.";
    protected static final String PLAYER_WIN = "You win.";
    protected static final String CPU_WIN = "I win.";
    protected static final String TIE = "It's a tie.";
    protected static final String CPU_MOVE = "I chose %s. ";
    protected static final String THANKS =
            "Thanks for playing!\nOur most recent games were: ";
    protected static final String PROMPT_MOVE =
            "Let's play! What's your move? (Type the move or q to quit)";

    protected static final String OVERALL_STATS =
            "Our overall stats are:\n" +
                    "I won: %.2f%%\nYou won: %.2f%%\nWe tied: %.2f%%\n";
    protected static final String CPU_PLAYER_MOVES = "Me: %s, You: %s\n";


    // Game outcome constants.
    protected static final int CPU_WIN_OUTCOME = 2;
    protected static final int PLAYER_WIN_OUTCOME = 1;
    protected static final int TIE_OUTCOME = 0;
    protected static final int INVALID_INPUT_OUTCOME = -1;

    // Other game control constants.
    protected static final int MAX_GAMES = 100;
    protected static final int MIN_POSSIBLE_MOVES = 3;
    protected static final int NUM_ROUNDS_TO_DISPLAY = 10;
    protected static final int PERCENTAGE_RESIZE = 100;
    protected static final String QUIT = "q";


    public boolean isValidMove(String move) {
        // TODO
        // Use a loop here
        if(move == null){
                return false;
        }
        for (String s : possibleMoves) {
                //loop through possible moves and check if it contains move.
                if (move.equals(s))
                        return true;
        }

        return false;  // dummy return value so code compiles.
    }

    public void playRPS(String playerMove, String cpuMove) {
        // TODO

        // Use determineWinner to determine who won
        int gameState = determineWinner(playerMove, cpuMove);
        String print;
        if(gameState == -1){
                print = INVALID_INPUT;
        }
        else if(gameState == 0){
                print = CPU_MOVE + TIE;
        }
        else if(gameState == 1){
                print = CPU_MOVE + PLAYER_WIN;
        }
        else{
                print = CPU_MOVE + CPU_WIN;
        }
        System.out.printf(print, cpuMove);
        System.out.println();
        // Record the moves made
        if(gameState != INVALID_INPUT_OUTCOME){

                String[] tempPlayerMoves = new String[MAX_GAMES];
                for(int i = 0; i < numGames; i++){
                        tempPlayerMoves[i] = playerMoves[i];
                }


                String[] tempCpuMoves = new String[MAX_GAMES];
                for(int i = 0; i < numGames; i++){
                        tempCpuMoves[i] = cpuMoves[i];
                }

                tempPlayerMoves[numGames] = playerMove;
                playerMoves = tempPlayerMoves;
                tempCpuMoves[numGames] = cpuMove;
                cpuMoves = tempCpuMoves;
        }
        // Add one to the appropriate statistics
        if(gameState == PLAYER_WIN_OUTCOME){
                numPlayerWins++;
        }
        else if(gameState == CPU_WIN_OUTCOME){
                numCPUWins++;
        }
        else if(gameState ==TIE_OUTCOME){
                numTies++;
        }
        numGames = numTies + numPlayerWins + numCPUWins;

        // Add appropriate Javadoc method header
    }


    // The following methods have been already implemented. Do not change them.

    /**
     * Generates next cpu move
     *
     * @return representing the move, depending on random int
     */
    public String genCPUMove() {
        // Generate new random number
        int num = rand.nextInt(possibleMoves.length);
        // Get move based on random number
        return possibleMoves[num];
    }

    /**
     * Print out the end game stats: moves played and win percentages
     */
    public void displayStats() {
        float cpuWinPercent = (float) numCPUWins /
                (float) numGames * PERCENTAGE_RESIZE;
        float playerWinPercent = (float) numPlayerWins /
                (float) numGames * PERCENTAGE_RESIZE;
        float tiedPercent = (float) numTies /
                (float) numGames * PERCENTAGE_RESIZE;

        System.out.println(THANKS);

        // Get which index to print to
        int printTo = (numGames < NUM_ROUNDS_TO_DISPLAY)
                ? 0 : numGames - NUM_ROUNDS_TO_DISPLAY;

        // Print system and user moves
        for (int i = numGames - 1; i >= printTo; i--) {
            System.out.printf(CPU_PLAYER_MOVES, this.cpuMoves[i],
                    this.playerMoves[i]);
        }

        System.out.printf(OVERALL_STATS, cpuWinPercent, playerWinPercent,
                tiedPercent);
    }
}
