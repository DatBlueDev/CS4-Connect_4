// 0 = empty cell
// 1 = red 
// 2 = yellow

import java.util.*;

public class MDAA{
    static final int boardLength = 7;
    static final int boardHeight = 6; 
    static int insertTokenIndex;
    static int turnNumber = 1;
    static int currentPlayer = 1;
    static boolean running = true;
    static int boardArray[][]
     = {{0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0}};
    static int [] savePos = new int[2];
    static int [][] scanPos = new int[8][2];

    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        int n;
        while (running){
            currentPlayer = turnNumber % 2 == 1? 1:2;
            System.out.println("It is player " + currentPlayer + "'s turn");
            displayBoard();
            n = reader.nextInt();
            dropTokens(n);
            System.out.println("");
            //checkWin wincondition
            turnNumber+=1;
        }

    }

    public static void resetBoard(){
        boardArray[0] = new int[] {0, 0, 0, 0, 0, 0, 0};
        boardArray[1] = new int[] {0, 0, 0, 0, 0, 0, 0};
        boardArray[2] = new int[] {0, 0, 0, 0, 0, 0, 0};
        boardArray[3] = new int[] {0, 0, 0, 0, 0, 0, 0};
        boardArray[4] = new int[] {0, 0, 0, 0, 0, 0, 0};
        boardArray[5] = new int[] {0, 0, 0, 0, 0, 0, 0};

    }

    public static void displayBoard(){
        for (int i = 0; i<boardHeight; i++){
            for (int j = 0; j<boardLength; j++){
                System.out.print(boardArray[i][j]);
            }
            System.out.println("");
        }
    }

    public static void dropTokens(int columnIndex){
        boolean allowToPlace = true;
        for (int i = 0; i < boardHeight; i++){
            if ((boardArray[i][columnIndex] != 0) && i > 0){
                insertTokenIndex = i-1;
                
                break;
            }
            else if ((boardArray[i][columnIndex] != 0) &&i == 0) {
                System.out.println("Column Full");
                allowToPlace = false;
                break;
            }
            else{
                insertTokenIndex=boardHeight-1;
            }
        }
        if (allowToPlace){
            boardArray[insertTokenIndex][columnIndex] = currentPlayer;
            savePos[0] = insertTokenIndex;
            savePos[1] = columnIndex;
        }


    } 

    public static int checkWinCondition(){
        scanPos[0] = {((savePos[0] - 3) < 0? 0:(savePos[0] - 3)), savePos[1]};//first point scan vertical
        scanPos[1] = {((savePos[0] + 3) > 5? 5:(savePos[0] + 3)), savePos[1]};//second point scan vertical
        scanPos[2] = {savePos[0], ((savePos[1] - 3) < 0? 0:(savePos[1] - 3))};//first point scan horizontal
        scanPos[3] = {savePos[0], ((savePos[1] + 3) > 6? 6:(savePos[1] + 3))};//second point scan horizontal
        scanPos[4] = {((savePos[0] - 3) < 0? 0:(savePos[0] - 3)), ((savePos[1] - 3) < 0? 0:(savePos[1] - 3))};//first point scan diagonal negative slope
        scanPos[5] = {((savePos[0] + 3) > 5? 5:(savePos[0] + 3)), ((savePos[1] + 3) > 6? 6:(savePos[1] + 3))};//second point scan diagonal negative slope
        scanPos[6] = {((savePos[0] + 3) > 5? 5:(savePos[0] + 3)), ((savePos[1] - 3) < 0? 0:(savePos[1] - 3))};//first point scan diagonal positive slope
        scanPos[7] = {((savePos[0] - 3) < 0? 0:(savePos[0] - 3)), ((savePos[1] + 3) > 6? 6:(savePos[1] + 3))};//second point scan diagonal positive slope
        return 0;
    }
}
    
