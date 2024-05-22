
//REMEMBER THAT ALL OF THE VARIABLES USED HERE WILL BE FROM LOGIN JAVA SO REPLACEMENT WILL BE DONE
import java.lang.Math;

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class playerStats {
	String usernames[] = new String[1000];
	int playerWins[] = new int[1000];
	int playerGames[] = new int[1000];
	float playerWR[] = new float[1000];
	float playerMMR[] = new float[1000];
	String sortArray[] = new String[5];

	public static void calculateMMR() {
		float expectedPlayerWR = (float) (1 / (1 + Math.pow(10, ((MDAA.opponentMMR - MDAA.playerMMR) / 400))));
		MDAA.playerMMR = (float) (MDAA.playerMMR + (20 * (MDAA.outcome - expectedPlayerWR)));
		System.out.println(MDAA.playerMMR);
		float expectedOpponentWR = (float) (1
				/ (1 + Math.pow(10, ((MDAA.playerMMR - MDAA.opponentMMR) / 400))));
		MDAA.opponentMMR = (float) (MDAA.opponentMMR + (20 * ((-1 * MDAA.outcome) + 1 - expectedOpponentWR)));
		System.out.println(MDAA.opponentMMR);
	}

	public void preLeaderboard() {
		try {
			File file = new File("users.txt");

			if (file.exists()) {
				FileReader reader = new FileReader("users.txt");
				BufferedReader bf = new BufferedReader(reader);

				String line = bf.readLine();
				int i = 0;

				while (line != null) {
					usernames[i] = line;
					line = bf.readLine();
					i++;
				}

				bf.close();
			} else {
				System.out.println("Users file does not exist.");
			}
		} catch (Exception e) {
			System.out.println("Something went wrong");
		}

		try {
			int j = 0;
			for (String user : usernames) {
				File file = new File(user + ".txt");
				if (file.exists()) {
					FileReader reader = new FileReader(user + ".txt");
					BufferedReader bf = new BufferedReader(reader);

					String line = bf.readLine();
					int i = 0;

					while (line != null) {
						if (i == 1) {
							playerWins[j] = Integer.parseInt(line);
						} else if (i == 2) {
							playerGames[j] = Integer.parseInt(line);
						} else if (i == 3) {
							playerMMR[j] = Float.parseFloat(line);
						}
						i++;
					}
					playerWR[j] = (float) playerWins[j] / playerGames[j];

					bf.close();
				} else {
					System.out.println("User file might not exist.");
				}
				j++;
			}
			int k = 0;

			while (k >= j) {
				for (int i = 0; i <= j; i++) {
					if (playerMMR[i] < playerMMR[i + i]) {
						k = 0;
						sortArray[0] = usernames[i];
						sortArray[1] = Integer.toString(playerWins[i]);
						sortArray[2] = Integer.toString(playerGames[i]);
						sortArray[3] = Float.toString(playerWR[i]);
						sortArray[4] = Float.toString(playerMMR[i]);

						usernames[i] = usernames[i + i];
						playerWins[i] = playerWins[i + i];
						playerGames[i] = playerGames[i + i];
						playerWR[i] = playerWR[i + i];
						playerMMR[i] = playerMMR[i + i];

						usernames[i + i] = sortArray[0];
						playerWins[i + i] = Integer.parseInt(sortArray[0]);
						playerGames[i + i] = Integer.parseInt(sortArray[0]);
						playerWR[i + i] = Float.parseFloat(sortArray[0]);
						playerMMR[i + i] = Float.parseFloat(sortArray[0]);
					} else if (playerMMR[i] >= playerMMR[i + i]) {
						k++;
						if (k >= j) {
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("User file might not exist.");
		}
	}
}