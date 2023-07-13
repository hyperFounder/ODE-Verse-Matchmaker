package com.example.demo1;
import javafx.util.Pair;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class PlayWriter {

    private Romeo myRomeo  = null;
    private InetAddress RomeoAddress = null;
    private int RomeoPort = 0;
    private Socket RomeoMailbox = null;

    private Juliet myJuliet = null;
    private InetAddress JulietAddress = null;
    private int JulietPort = 0;
    private Socket JulietMailbox = null;

    double[][] theNovel = null;
    int novelLength = 0;

    public PlayWriter()
    {
        novelLength = 500;
        theNovel = new double[novelLength][2];
        theNovel[0][0] = 0;
        theNovel[0][1] = 1;
    }

    public void createCharacters() {

        System.out.println("PlayWriter: Romeo enters the stage.");
        myRomeo = new Romeo(0);
        myRomeo.start();

        System.out.println("PlayWriter: Juliet enters the stage.");
        myJuliet = new Juliet(1);
        myJuliet.start();

    }
    public void charactersMakeAcquaintances() {
        try
        {
            Pair<InetAddress,Integer> RomeoPair = myRomeo.getAcquaintance();
            RomeoAddress = InetAddress.getByName("127.0.0.1");
            RomeoPort = 7778;
            System.out.println("PlayWriter: I've made acquaintance with Romeo");

            Pair<InetAddress,Integer> JulietPair = myJuliet.getAcquaintance();
            JulietAddress = InetAddress.getByName("127.0.0.1");
            JulietPort = 7779;
            System.out.println("PlayWriter: I've made acquaintance with Juliet");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void requestVerseFromRomeo(int verse) {

        System.out.println("PlayWriter: Requesting verse " + verse + " from Romeo. -> (" + theNovel[verse-1][1] + ")");
        try
        {
            RomeoMailbox = new Socket(InetAddress.getByName("127.0.0.1"), 7778);
            OutputStream requestStream = RomeoMailbox.getOutputStream();
            OutputStreamWriter requestStreamWriter = new OutputStreamWriter(requestStream);
            requestStreamWriter.write((theNovel[verse-1][1]+"J"));
            requestStreamWriter.flush(); //Service request sent
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void requestVerseFromJuliet(int verse) {
        try
        {
            System.out.println("PlayWriter: Requesting verse " + verse + " from Juliet. -> (" + theNovel[verse-1][0] + ")");
            JulietMailbox = new Socket(InetAddress.getByName("127.0.0.1"), 7779);
            OutputStream outputStream = JulietMailbox.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.write((theNovel[verse-1][0]+"R"));
            outputStreamWriter.flush();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void receiveLetterFromRomeo(int verse) {
        try {
            System.out.println("PlayWriter: Romeo's verse " + verse + " -> " + theNovel[verse - 1][0]);
            InputStream inputStream = RomeoMailbox.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            StringBuilder sb = new StringBuilder();
            char x;
            int index = 0;
            while (true){
                x = (char) inputStreamReader.read();
                if (x == '#'){
                    break;
                }
                sb.append(x);
                if (x == 'R'){
                    index = sb.length();
                }
            }
            theNovel[verse][0] = Double.parseDouble(sb.substring(index));
            RomeoMailbox.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void receiveLetterFromJuliet(int verse) {

        try {
            System.out.println("PlayWriter: Juliet's verse " + verse + " -> " + theNovel[verse][1]);
            InputStream inputStream = JulietMailbox.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            StringBuilder sb = new StringBuilder();
            char x;
            int index = 0;
            while (true){
                x = (char) inputStreamReader.read();
                if (x == '#'){
                    break;
                }
                sb.append(x);
                if (x == 'J'){
                    index = sb.length();
                }
            }
            theNovel[verse][1] = Double.parseDouble(sb.substring(index));
            JulietMailbox.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }



    public void storyClimax() {
        for (int verse = 1; verse < novelLength; verse++) {
            System.out.println("PlayWriter: Writing verse " + verse + ".");
            requestVerseFromRomeo(verse);
            requestVerseFromJuliet(verse);
            receiveLetterFromRomeo(verse);
            receiveLetterFromJuliet(verse);
            System.out.println("PlayWriter: Verse " + verse + " finished.");
        }
    }
    public void charactersDeath() {
        myRomeo.interrupt();
        myJuliet.interrupt();
    }
    public void writeNovel() {
        System.out.println("PlayWriter: The Most Excellent and Lamentable Tragedy of Romeo and Juliet.");
        System.out.println("PlayWriter: A play in IV acts.");
        System.out.println("PlayWriter: Act I. Introduction.");
        this.createCharacters();
        System.out.println("PlayWriter: Act II. Conflict.");
        this.charactersMakeAcquaintances();
        System.out.println("PlayWriter: Act III. Climax.");
        this.storyClimax();
        System.out.println("PlayWriter: Act IV. Denouement.");
        this.charactersDeath();
    }
    public void dumpNovel() {
        FileWriter Fw = null;
        try {
            Fw = new FileWriter("RomeoAndJuliet.csv");
        } catch (IOException e) {
            System.out.println("PlayWriter: Unable to open novel file. " + e);
        }

        System.out.println("PlayWriter: Dumping novel. ");
        StringBuilder sb = new StringBuilder();
        for (int act = 0; act < novelLength; act++) {
            String tmp = theNovel[act][0] + ", " + theNovel[act][1] + "\n";
            sb.append(tmp);
        }

        try {
            BufferedWriter br = new BufferedWriter(Fw);
            br.write(sb.toString());
            br.close();
        } catch (Exception e) {
            System.out.println("PlayWriter: Unable to dump novel. " + e);
        }
    }
    public static void main (String[] args) {
        PlayWriter Shakespeare = new PlayWriter();
        Shakespeare.writeNovel();
        Shakespeare.dumpNovel();
        System.exit(0);
    }


}
