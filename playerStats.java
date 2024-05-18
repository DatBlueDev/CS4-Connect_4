//REMEMBER THAT ALL OF THE VARIABLES USED HERE WILL BE FROM LOGIN JAVA SO REPLACEMENT WILL BE DONE
public class playerStats {
    public void calculateMMR() {
        float expectedPlayerWR = MDAA.outcome - (1 / (1 + (-(MDAA.opponentMMR - MDAA.playerMMR) / 50)));
        MDAA.playerMMR = MDAA.playerMMR + (20 * expectedPlayerWR);
        float expectedOpponentWR = ((-1 * MDAA.outcome) + 1) - (1 / (1 + (-(MDAA.playerMMR - MDAA.opponentMMR) / 50)));
        MDAA.opponentMMR = MDAA.opponentMMR + (20 * expectedOpponentWR);
    }

    public void calculateWRGamesPlayed() {
        MDAA.playerHistory[0] = MDAA.outcome == 1 ? MDAA.playerHistory[0] + 1 : MDAA.playerHistory[0]; // win
        MDAA.playerHistory[1] = MDAA.outcome == 0.5 ? MDAA.playerHistory[1] + 1 : MDAA.playerHistory[1]; // draw
        MDAA.playerHistory[2] = MDAA.outcome == 0 ? MDAA.playerHistory[2] + 1 : MDAA.playerHistory[2]; // loss
        MDAA.playerWR = (float) MDAA.playerHistory[0]
                / (MDAA.playerHistory[0] + MDAA.playerHistory[1] + MDAA.playerHistory[2]);
        MDAA.playerNumGames++;
        MDAA.playerAverageMoves = ((MDAA.playerAverageMoves * (MDAA.playerNumGames - 1)) + ((int) MDAA.turnNumber / 2))
                / MDAA.playerNumGames;

        MDAA.opponentHistory[0] = (-1 * MDAA.outcome) + 1 == 1 ? MDAA.opponentHistory[0] + 1 : MDAA.opponentHistory[0]; // enemy
                                                                                                                        // win
        MDAA.opponentHistory[1] = (-1 * MDAA.outcome) + 1 == 0.5 ? MDAA.opponentHistory[1] + 1
                : MDAA.opponentHistory[1]; // draw
        MDAA.opponentHistory[2] = (-1 * MDAA.outcome) + 1 == 0 ? MDAA.opponentHistory[2] + 1 : MDAA.opponentHistory[2]; // enemy
                                                                                                                        // loss
        MDAA.opponentWR = (float) MDAA.opponentHistory[0]
                / (MDAA.opponentHistory[0] + MDAA.opponentHistory[1] + MDAA.opponentHistory[2]);
        MDAA.opponentNumGames++;
        MDAA.opponentAverageMoves = ((MDAA.opponentAverageMoves * (MDAA.opponentNumGames - 1))
                + ((int) MDAA.turnNumber / 2)) / MDAA.opponentNumGames;
    }

}