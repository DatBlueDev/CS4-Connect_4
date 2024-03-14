import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MDAA extends JFrame {
    static final int boardLength = 7;
    static final int boardHeight = 6;
    static int insertTokenIndex;
    static int turnNumber = 1;
    static int currentPlayer = 1;
    static int[] savePos = new int[2];
    static boolean running = true;
    static JButton buttonGrid[][] = new JButton[boardHeight][boardLength];
    static int visualBoard[][] = new int[boardHeight][boardLength]; 
    static int boardArray[][] = { { 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0 } };
    public static void mainFunction() {

      JFrame frame = new JFrame("Wolfhound HQ");
      JPanel boardPanel = new JPanel(new GridLayout(boardHeight, boardLength));
      frame.setSize(800,600); // Set the size of the frame
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      for (int i = 0; i < boardHeight; i++) {
        for (int j = 0; j < boardLength; j++) {
            buttonGrid[i][j] = new JButton("");
            buttonGrid[i][j].setBackground(Color.BLACK);  
            buttonGrid[i][j].addActionListener(new ActionListener(){    
              @Override
              public void actionPerformed(ActionEvent e) {
                  JButton clickedButton = (JButton) e.getSource();
                  int col = 0;
                  for (int j = 0; j < boardLength; j++) {
                    for (int k = 0; k < boardHeight; k++){
                        if (clickedButton == buttonGrid[k][j]) {
                            col = j;
                            break;
                        }
                      }
                  }

                  if (col != -1) {
                    if (dropTokens(col)) {
                        running = checkWinCondition.checkWin() == 0 ? true : false;
                        if (!running) {
                            resetBoard();
                            displayBoard();
                            JOptionPane.showMessageDialog(null, ("Player " + currentPlayer + " won!"),
                                    "WINNER!!!", JOptionPane.INFORMATION_MESSAGE);
                            System.exit(0);
                            running = true; 

                        }
                        currentPlayer = (currentPlayer % 2) + 1; // Switch player
                        displayBoard(); // Update visual representation after each turn
                    } else {
                        JOptionPane.showMessageDialog(null, "Column Full! Choose another column.",
                                "Invalid Move", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

            boardPanel.add(buttonGrid[i][j]);
        }
        }

      frame.add(boardPanel);
      frame.setVisible(true);

    }

    public static void resetBoard() {
        boardArray[0] = new int[] { 0, 0, 0, 0, 0, 0, 0 };
        boardArray[1] = new int[] { 0, 0, 0, 0, 0, 0, 0 };
        boardArray[2] = new int[] { 0, 0, 0, 0, 0, 0, 0 };
        boardArray[3] = new int[] { 0, 0, 0, 0, 0, 0, 0 };
        boardArray[4] = new int[] { 0, 0, 0, 0, 0, 0, 0 };
        boardArray[5] = new int[] { 0, 0, 0, 0, 0, 0, 0 };

    }

    public static void displayBoard() {
      for (int i = 0; i < boardHeight; i++) {
        for (int j = 0; j < boardLength; j++) {
            if (boardArray[i][j] == 0) {
                visualBoard[i][j] = 0;  // Empty cell
                buttonGrid[i][j].setText("");
                buttonGrid[i][j].setBackground(Color.BLACK);
            } else if (boardArray[i][j] == 1) {
                visualBoard[i][j] = 1;  
                buttonGrid[i][j].setText("Blood Moon");
                buttonGrid[i][j].setBackground(Color.RED);
            } else {
                visualBoard[i][j] = 2; 
                buttonGrid[i][j].setText("Blue Moon");
                buttonGrid[i][j].setBackground(Color.BLUE);
            }
        }
    }
    }

    public static boolean dropTokens(int columnIndex) {
        boolean allowToPlace = true;
        for (int i = 0; i < boardHeight; i++) {
            if ((boardArray[i][columnIndex] != 0) && i > 0) {
                insertTokenIndex = i - 1;

                break;
            } else if ((boardArray[i][columnIndex] != 0) && i == 0) {
                System.out.println("Column Full");
                allowToPlace = false;
                break;
            } else {
                insertTokenIndex = boardHeight - 1;
            }
        }
        if (allowToPlace) {
            boardArray[insertTokenIndex][columnIndex] = currentPlayer;
            savePos[0] = insertTokenIndex;
            savePos[1] = columnIndex;
        }
        return allowToPlace; 
    }
}