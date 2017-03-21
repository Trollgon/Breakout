package de.tudarmstadt.informatik.fop.breakout.managers;

import java.io.*;

/**
 * @author Matthias Spoerkmann
 */
public class CheckPointManager {

    public static void setCheckpoint(Integer checkpoint) {
        try{
            PrintWriter writer = new PrintWriter("save.txt", "UTF-8");
            writer.println(checkpoint);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer getCheckpoint() {
        File file = new File("save.txt");
        BufferedReader reader = null;

        int checkpoint = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text;

            while ((text = reader.readLine()) != null) {
                checkpoint = Integer.parseInt(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return checkpoint;
    }
}
