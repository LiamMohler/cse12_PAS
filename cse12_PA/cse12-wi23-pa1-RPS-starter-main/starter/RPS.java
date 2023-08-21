import java.util.Scanner;

public class RPS extends RPSAbstract {
    // Messages for the game.
    protected static final String GAME_NOT_IMPLEMENTED =
            "Game not yet implemented.";

    /**
     * Construct a new instance of RPS with the given possible moves.
     * @param moves all possible moves in the game.
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }


    public int determineWinner(String playerMove, String cpuMove) {
        // TODO

        if(!isValidMove(playerMove)){
            return INVALID_INPUT_OUTCOME;
        }
        if(!isValidMove(cpuMove)){
            return INVALID_INPUT_OUTCOME;
        }
        if(cpuMove.equals(playerMove)){
            return TIE_OUTCOME;
        }
        int playerIndex = 0;
        int cpuIndex = 0;
        for(int i = 0; i < possibleMoves.length;i++){
            if(playerMove.equals(possibleMoves[i])){
                playerIndex = i;
            }
            if(cpuMove.equals(possibleMoves[i])){
                cpuIndex = i;
            }
        }
        if(playerIndex-cpuIndex == 1 || (cpuIndex == possibleMoves.length-1 && playerIndex == 0)){
            return CPU_WIN_OUTCOME;
        }
        else if(cpuIndex-playerIndex == 1 || (playerIndex == possibleMoves.length-1 && cpuIndex == 0)){
            return PLAYER_WIN_OUTCOME;
        }
        return TIE_OUTCOME;
    }

    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            for (int i = 0; i < args.length; i++) {
                moves[i] = args[i];
            }
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);

        // While user does not input "q", play game

        while (true) {
            System.out.println(PROMPT_MOVE);
            String input = in.nextLine();
            if (input.equals(QUIT)) {
                game.displayStats();
                in.close();
                return;
            }
            game.playRPS(input, game.genCPUMove());
        }
        // TODO: Insert the code to play the game. 
        // See the writeup for an example of the game play.
        // Hint: call the methods we/you have already written 
        // to do most of the work! And don't forget Javadoc.
    }
}
