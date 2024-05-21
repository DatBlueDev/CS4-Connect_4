import java.io.*;

public class getStats {
    static String usernames[] = new String[1000];

    public static void main(String args[]) {
        try {
            File file = new File("playerNames.txt");
            if (file.exists()) {
                FileReader reader = new FileReader(file);
                BufferedReader breader = new BufferedReader(reader);
                String line = breader.readLine();
                int i = 0;
                while (line != null) {
                    usernames[i] = line;
                    i++;
                    breader.readLine();
                }
                breader.close();
            }
        } catch (Exception e) {
            System.out.println("Error opening the file playerName.txt. It may not exist.");
        }
    }
}
