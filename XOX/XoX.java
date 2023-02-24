package XOX;
public class XoX {

    public int boardSize = 3;
    private int moveCount;
    private String firstPlayer;
    private String secondPlayer;
    private boolean firstPlayerTurn = true;
    private boolean isFinished = false;
    /*
    You are going to use int variables on board to exploit the advantage of integer numbers
    to check win condition
    Initially all of the board filled with 0.
    X character represented with 1
    0 character represented with 2
     */
    private int board[][];

    public XoX(String firstPlayer, String secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.moveCount = 0;
        board = new int[boardSize][boardSize];
        cleanBoard();
    }

    //Copy constructor
    public XoX(XoX game) {
        this.firstPlayer = game.firstPlayer;
        this.secondPlayer = game.secondPlayer;
        this.moveCount = game.moveCount;
        this.isFinished = game.isFinished;
        this.board = new int[game.board.length][game.board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j <boardSize ; j++) {
                this.board[i][j] = game.board[i][j];
            }
        }
    }
    /*
     Mark the given position on board. Use the boolean variable to decide which player is going to mark.
     Add necessary statements to prevent illegal moves.
     */
    public void mark(int row, int column) {                                                       //çakışmaması lazım
        boolean validMove = (row>=0) && (row<boardSize) && (column<boardSize) && (column>=0) && (board[row][column] == 0);
        if (!isFinished) {
            if (validMove) {
                if(firstPlayerTurn){board[row][column] = 1;}
                else
                {
                    board[row][column] = 2;
                }
                int state = gameState();
                updateGameState(state);
            } else {
                System.out.println("You can not mark this area");
            }
        } else {
            System.out.println("Game Over ! You can not mark");
        }
    }

    public void printBoard() {
        System.out.println("---------");
        for (int row = 0; row <boardSize ; row++) {
            for (int column = 0; column<boardSize ; column++) {
                System.out.print("["+maskValues(board[row][column])+"]");
            }
            System.out.println();
        }
        System.out.println("---------");
    }

    // Fill the board with 0
    private void cleanBoard() {
        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                board[row][column]=0;
            }
        }
    }

    /* Since the players use "X" and "O" variables
       you have convert each integer number to corresponding character before print out
     */
    private char maskValues(int value) {
        char symbol;
                if (value == 1) {
                   symbol = 'X';
                } else if (value == 2) {
                   symbol = 'O';
                } else { symbol=' ' ;}
        return symbol;
    }

    /*
     Technically there are eight possible states you have to check (three rows, three columns, two diagonal).
     This function must return if:
       - player1 win 1
       - player2 win 8
       - Game continue 0
     */
    private int gameState() {

        int states[] = new int[8];
        states[0] = board[0][0]*board[0][1]*board[0][2];
        states[1] = board[1][0]*board[1][1]*board[1][2];
        states[2] = board[2][0]*board[2][1]*board[2][2];

        states[3] = board[0][0]*board[1][0]*board[2][0];      //non-dynamic
        states[4] = board[0][1]*board[1][1]*board[2][1];
        states[5] = board[0][2]*board[1][2]*board[2][2];

        states[6] = board[0][0]*board[1][1]*board[2][2];
        states[7] = board[0][2]*board[1][1]*board[2][0];
        for (int i = 0; i < states.length; i++) {
            if(states[i]==1){
                return 1;
            } else if (states[i] == 8) {
                return 8;
            }
        }
        return 0;
    }
    private void updateGameState(int gameState) {
                if(gameState == 0){
                    moveCount++;
                    firstPlayerTurn=!firstPlayerTurn;
                    if(moveCount == 9){
                        isFinished = true;
                        System.out.println("Draw!");
                }
                }
                if(gameState == 1){
                    isFinished = true;
                    System.out.println(firstPlayer + "won!");
                }else
                    if(gameState==8){
                        isFinished = true;
                        System.out.println(secondPlayer + "won!");
                    }
                    if(gameState!=0 && gameState!=1 && gameState!=8){
                    System.out.println("Corrupt Game State");}
            }
    }

