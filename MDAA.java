
import javax.swing.border.Border;

import java.awt.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.*;

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

    // PUT THESE VARIABLES IN LOGIN
    static float outcome;
    static float playerMMR;
    static float opponentMMR;
    static int playerHistory;
    static int opponentHistory;
    static float playerWR;
    static float opponentWR;
    static int playerNumGames;
    static int opponentNumGames;
    static float playerAverageMoves;
    static float opponentAverageMoves;

    public static void mainFunction() {

        JFrame frame = new JFrame("Wolfhound HQ");
        JPanel boardPanel = new JPanel(new GridLayout(boardHeight, boardLength));
        frame.setSize(800, 600); // Set the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardLength; j++) {
                buttonGrid[i][j] = new JButton("");
                buttonGrid[i][j].setBackground(Color.BLACK);
                buttonGrid[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton clickedButton = (JButton) e.getSource();
                        int col = 0;
                        for (int j = 0; j < boardLength; j++) {
                            for (int k = 0; k < boardHeight; k++) {
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
                                    int playerWon = 0;
                                    try (BufferedReader reader = new BufferedReader(new FileReader("session.txt"))) {
                                        String firstLine = reader.readLine();
                                        String secondLine = reader.readLine();
                                        String winnerName = "";
                                        String loserName = "";
                                        switch (currentPlayer) {
                                            case 1:
                                                JOptionPane.showMessageDialog(null,
                                                        ("Player " + firstLine + " (P1) won!"), "WINNER!!!",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                                winnerName = firstLine;
                                                loserName = secondLine;
                                                outcome = 1f;
                                                break;
                                            case 2:
                                                JOptionPane.showMessageDialog(null,
                                                        ("Player " + secondLine + " (P2) won!"), "WINNER!!!",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                                winnerName = secondLine;
                                                loserName = firstLine;
                                                outcome = 0f;
                                                break;
                                        }
                                        String filename = winnerName + ".txt";
                                        File fileWin = new File("users/", filename);

                                        if (!fileWin.exists() || !fileWin.canRead()) {
                                            System.out.println("Error: File not found or cannot be read.");
                                        } else {

                                            BufferedReader readerWin = new BufferedReader(new FileReader(fileWin));
                                            StringBuilder updatedContent = new StringBuilder();

                                            String line;
                                            int lineCount = 1;
                                            while ((line = readerWin.readLine()) != null) {
                                                if (lineCount == 2 || lineCount == 3) {
                                                    int value = Integer.parseInt(line) + 1;
                                                    updatedContent.append(value).append("\n");
                                                } else if (lineCount == 4) {
                                                    switch (currentPlayer) {
                                                        case 1:
                                                            playerMMR = Float.parseFloat(line);
                                                            System.out.println(playerMMR);
                                                            updatedContent.append(line).append("\n");
                                                            break;
                                                        case 2:
                                                            opponentMMR = Float.parseFloat(line);
                                                            System.out.println(opponentMMR);
                                                            updatedContent.append(line).append("\n");
                                                            break;
                                                    }
                                                } else {
                                                    updatedContent.append(line).append("\n");
                                                }
                                                lineCount++;
                                            }

                                            readerWin.close();

                                            // Write updated content back to the file
                                            BufferedWriter writerWin = new BufferedWriter(new FileWriter(fileWin));
                                            writerWin.write(updatedContent.toString());
                                            writerWin.close();
                                        }

                                        filename = loserName + ".txt";
                                        File fileLose = new File("users/", filename);

                                        if (!fileLose.exists() || !fileLose.canRead()) {
                                            System.out.println("Error: File not found or cannot be read.");
                                        } else {

                                            BufferedReader readerLose = new BufferedReader(new FileReader(fileLose));
                                            StringBuilder updatedContent = new StringBuilder();

                                            String line;
                                            int lineCount = 1;
                                            while ((line = readerLose.readLine()) != null) {
                                                if (lineCount == 3) {
                                                    int value = Integer.parseInt(line) + 1;
                                                    updatedContent.append(value).append("\n");
                                                } else if (lineCount == 4) {
                                                    switch (currentPlayer) {
                                                        case 2:
                                                            playerMMR = Float.parseFloat(line);
                                                            System.out.println(playerMMR);
                                                            updatedContent.append(line).append("\n");
                                                            break;
                                                        case 1:
                                                            opponentMMR = Float.parseFloat(line);
                                                            System.out.println(opponentMMR);
                                                            updatedContent.append(line).append("\n");
                                                            break;
                                                    }
                                                } else {
                                                    updatedContent.append(line).append("\n");
                                                }
                                                lineCount++;
                                            }

                                            readerLose.close();

                                            // Write updated content back to the file
                                            BufferedWriter writerLose = new BufferedWriter(new FileWriter(fileLose));
                                            writerLose.write(updatedContent.toString());
                                            writerLose.close();
                                        }

                                        System.out.println("hello");
                                        playerStats.calculateMMR();

                                        if (!fileWin.exists() || !fileWin.canRead()) {
                                            System.out.println("Error: File not found or cannot be read.");
                                        } else {

                                            BufferedReader readerWin2 = new BufferedReader(new FileReader(fileWin));
                                            StringBuilder updatedContent = new StringBuilder();
                                            String line;
                                            int lineCount = 1;
                                            while ((line = readerWin2.readLine()) != null) {
                                                if (lineCount == 4) {
                                                    switch (currentPlayer) {
                                                        case 1:
                                                            updatedContent.append(playerMMR).append("\n");
                                                            break;
                                                        case 2:
                                                            updatedContent.append(opponentMMR).append("\n");
                                                            break;
                                                    }
                                                } else {
                                                    updatedContent.append(line).append("\n");
                                                }
                                                lineCount++;
                                            }

                                            readerWin2.close();

                                            BufferedWriter writerWin2 = new BufferedWriter(new FileWriter(fileWin));
                                            writerWin2.write(updatedContent.toString());
                                            writerWin2.close();
                                        }

                                        if (!fileLose.exists() || !fileLose.canRead()) {
                                            System.out.println("Error: File not found or cannot be read.");
                                        } else {

                                            BufferedReader readerLose2 = new BufferedReader(new FileReader(fileLose));
                                            StringBuilder updatedContent = new StringBuilder();

                                            String line;
                                            int lineCount = 1;
                                            while ((line = readerLose2.readLine()) != null) {
                                                if (lineCount == 4) {
                                                    switch (currentPlayer) {
                                                        case 2:
                                                            updatedContent.append(playerMMR).append("\n");
                                                            break;
                                                        case 1:
                                                            updatedContent.append(opponentMMR).append("\n");
                                                            break;
                                                    }
                                                } else {
                                                    updatedContent.append(line).append("\n");
                                                }
                                                lineCount++;
                                            }

                                            readerLose2.close();

                                            // Write updated content back to the file
                                            BufferedWriter writerLose2 = new BufferedWriter(new FileWriter(fileLose));
                                            writerLose2.write(updatedContent.toString());
                                            writerLose2.close();
                                        }
                                    } catch (IOException err) {
                                        System.err.println("Error reading file: " + err.getMessage());
                                    }

                                    System.exit(0);
                                    running = true;

                                }
                                currentPlayer = (currentPlayer % 2) + 1; // Switch player
                                turnNumber++;
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
                    visualBoard[i][j] = 0; // Empty cell
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