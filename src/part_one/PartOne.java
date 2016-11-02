package part_one;

import java.util.ArrayList;
import java.io.*;
import java.net.URISyntaxException;

/**
 * Created by jakeboucher on 11/1/16.
 */
public class PartOne {

    private int eCount;
    private int cCount;
    private int tCount;

    public PartOne(){}

    public void execute() {
        eCount = 0;
        cCount = 0;
        tCount = 0;
        parseFile();
        genCSV();
    }

    private void parseFile() {
        FileInputStream inp;
        BufferedReader br = null;
        String curLine;

        try {
            inp = new FileInputStream(new File(getClass().getResource("./20150801.as2types.txt").toURI()));
            br = new BufferedReader(new InputStreamReader(inp));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            while ((curLine = br.readLine()) != null){
                if(curLine.charAt(0) == '#') continue;

                String[] parts = curLine.split("\\|");
                if(parts[2].equals("Transit/Access")) {
                    tCount++;
                }
                else if(parts[2].equals("Enterpise")) {
                    eCount++;
                }
                else if(parts[2].equals("Content")) {
                    cCount++;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void genCSV() {

        try {
            PrintWriter pw = new PrintWriter(new File("partOne.csv"));
            StringBuilder sb = new StringBuilder();

            sb.append("Transit/Access");
            sb.append(',');
            sb.append("Enterprise");
            sb.append(',');
            sb.append("Content");
            sb.append('\n');

            sb.append(Integer.toString(tCount));
            sb.append(',');
            sb.append(Integer.toString(eCount));
            sb.append(',');
            sb.append(Integer.toString(cCount));
            sb.append('\n');

            pw.write(sb.toString());
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }



}
