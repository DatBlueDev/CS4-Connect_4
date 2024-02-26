public class checkWinCondition {
    static int[][] scanPos = new int[8][2];
    static int scanCounterV = 0;
    static int scanCounterH = 0;
    static int scanCounterND = 0;
    static int scanCounterPD = 0;
    static int winPlayer = 0;

    public static int checkWin() {
        scanPosGenerator();

        // scan coordinates
        for (int i = 0; i < 8; i++) {
            System.out.println(scanPos[i][0] + ", " + scanPos[i][1]);
        }

        winConditionScan();

        if (winPlayer != 0) {
            return winPlayer;
        } else {
            return 0;
        }
    }

    public static void scanPosGenerator() {
        scanPos[0][0] = MDAA.savePos[0];// first point scan vertical
        scanPos[0][1] = MDAA.savePos[1];
        scanPos[1][0] = MDAA.savePos[0] + 3 > 5 ? 5 : MDAA.savePos[0] + 3;// second point scan vertical
        scanPos[1][1] = MDAA.savePos[1];
        scanPos[2][0] = MDAA.savePos[0];// first point scan horizontal
        scanPos[2][1] = MDAA.savePos[1] - 3 < 0 ? 0 : MDAA.savePos[1] - 3;
        scanPos[3][0] = MDAA.savePos[0];// second point scan horizontal
        scanPos[3][1] = MDAA.savePos[1] + 3 > 6 ? 6 : MDAA.savePos[1] + 3;
        scanPos[4][0] = MDAA.savePos[0] - 3 < 0 ? 0 : MDAA.savePos[0] - 3;// first point scan diagonal negative slope
        scanPos[4][1] = MDAA.savePos[1] - 3 < 0 ? 0 : MDAA.savePos[1] - 3;
        if ((MDAA.savePos[0] - scanPos[4][0]) > (MDAA.savePos[1] - scanPos[4][1])) {
            scanPos[4][0] = scanPos[4][0] + ((MDAA.savePos[0] - scanPos[4][0]) - (MDAA.savePos[1] - scanPos[4][1]));
        } else if ((MDAA.savePos[0] - scanPos[4][0]) < (MDAA.savePos[1] - scanPos[4][1])) {
            scanPos[4][1] = scanPos[4][1] + ((MDAA.savePos[1] - scanPos[4][1]) - (MDAA.savePos[0] - scanPos[4][0]));
        }
        scanPos[5][0] = MDAA.savePos[0] + 3 > 5 ? 5 : MDAA.savePos[0] + 3;// second point scan diagonal negative slope
        scanPos[5][1] = MDAA.savePos[1] + 3 > 6 ? 6 : MDAA.savePos[1] + 3;
        if ((scanPos[5][0] - MDAA.savePos[0]) > (scanPos[5][1] - MDAA.savePos[1])) {
            scanPos[5][0] = scanPos[5][0] - ((scanPos[5][0] - MDAA.savePos[0]) - (scanPos[5][1] - MDAA.savePos[1]));
        } else if ((scanPos[5][0] - MDAA.savePos[0]) < (scanPos[5][1] - MDAA.savePos[1])) {
            scanPos[5][1] = scanPos[5][1] - ((scanPos[5][1] - MDAA.savePos[1]) - (scanPos[5][0] - MDAA.savePos[0]));
        }
        scanPos[6][0] = MDAA.savePos[0] + 3 > 5 ? 5 : MDAA.savePos[0] + 3;// first point scan diagonal positive slope
        scanPos[6][1] = MDAA.savePos[1] - 3 < 0 ? 0 : MDAA.savePos[1] - 3;
        if ((scanPos[6][0] - MDAA.savePos[0]) > (MDAA.savePos[1] - scanPos[6][1])) {
            scanPos[6][0] = scanPos[6][0] - ((scanPos[6][0] - MDAA.savePos[0]) - (MDAA.savePos[1] - scanPos[6][1]));
        } else if ((scanPos[6][0] - MDAA.savePos[0]) < (MDAA.savePos[1] - scanPos[6][1])) {
            scanPos[6][1] = scanPos[6][1] + ((MDAA.savePos[1] - scanPos[6][1]) - (scanPos[6][0] - MDAA.savePos[0]));
        }
        scanPos[7][0] = MDAA.savePos[0] - 3 < 0 ? 0 : MDAA.savePos[0] - 3;// second point scan diagonal positive slope
        scanPos[7][1] = MDAA.savePos[1] + 3 > 6 ? 6 : MDAA.savePos[1] + 3;
        if ((MDAA.savePos[0] - scanPos[7][0]) > (scanPos[7][1] - MDAA.savePos[1])) {
            scanPos[7][0] = scanPos[7][0] + (MDAA.savePos[0] - scanPos[7][0]) - (scanPos[7][1] - MDAA.savePos[1]);
        } else if ((MDAA.savePos[0] - scanPos[7][0]) < (scanPos[7][1] - MDAA.savePos[1])) {
            scanPos[7][1] = scanPos[7][1]
                    - ((scanPos[7][1] - MDAA.savePos[1]) - (MDAA.savePos[0] - scanPos[7][0]));
        }
    }

    public static void winConditionScan() {
        // vertical line scan
        if (!((scanPos[0][0]) > 2)) {
            for (int i = scanPos[0][0]; i <= scanPos[1][0]; i++) {
                scanCounterV = MDAA.boardArray[i][scanPos[0][1]] == MDAA.currentPlayer ? scanCounterV + 1 : 0;
                System.out.println(scanCounterV);
                if (scanCounterV >= 4) {
                    winPlayer = MDAA.currentPlayer;
                    break;
                }
            }
            System.out.println();
        }

        // horizontal line scan
        for (int i = scanPos[2][1]; i <= scanPos[3][1]; i++) {
            scanCounterH = MDAA.boardArray[scanPos[2][0]][i] == MDAA.currentPlayer ? scanCounterH + 1 : 0;
            System.out.println(scanCounterH);
            if (scanCounterH >= 4) {
                winPlayer = MDAA.currentPlayer;
                break;
            }
        }
        System.out.println();

        // negative slope diagonal line scan
        if (scanPos[5][0] - scanPos[4][0] > 2) {
            for (int i = scanPos[4][0]; i <= scanPos[5][0]; i++) {
                scanCounterND = MDAA.boardArray[i][scanPos[4][1] + (i - scanPos[4][0])] == MDAA.currentPlayer
                        ? scanCounterND + 1
                        : 0;
                System.out.println(scanCounterND);
                if (scanCounterND >= 4) {
                    winPlayer = MDAA.currentPlayer;
                    break;
                }
            }
            System.out.println();
        }

        // positive slope diagonal line scan
        if (scanPos[6][0] - scanPos[7][0] > 2) {
            for (int i = scanPos[6][0]; i >= scanPos[7][0]; i--) {
                scanCounterPD = MDAA.boardArray[i][scanPos[6][1] + (scanPos[6][0] - i)] == MDAA.currentPlayer
                        ? scanCounterPD + 1
                        : 0;
                System.out.println(scanCounterPD);
                if (scanCounterPD >= 4) {
                    winPlayer = MDAA.currentPlayer;
                    break;
                }
            }
            System.out.println();
        }
    }

}
