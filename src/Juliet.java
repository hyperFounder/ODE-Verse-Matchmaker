package com.example.demo1;
import javafx.util.Pair;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Juliet extends Thread {
    private ServerSocket ownServerSocket = null;
    private Socket serviceMailbox = null;
    private double currentLove = 0;
    private double b = 0;
    public Juliet(double initialLove) {
        currentLove = initialLove;
        b = 0.01;
        try {
            ownServerSocket = new ServerSocket(7779, 500, InetAddress.getByName("127.0.0.1"));
            System.out.println("Juliet: Good pilgrim, you do wrong your hand too much, ...");
        } catch(Exception e) {
            System.out.println("Juliet: Failed to create own socket " + e);
        }
    }

    public Pair<InetAddress,Integer> getAcquaintance() {
        System.out.println("Juliet: My bounty is as boundless as the sea,\n" +
                "       My love as deep; the more I give to thee,\n" +
                "       The more I have, for both are infinite.");
        return new Pair<InetAddress, Integer>(ownServerSocket.getInetAddress(), ownServerSocket.getLocalPort());
    }

    public double receiveLoveLetter() throws IOException
    {
        double tmp = 0;
        char x;
        serviceMailbox = ownServerSocket.accept();
        InputStream inputStream = serviceMailbox.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            x = (char) inputStreamReader.read();
            if (x == 'R') {
                break;
            }
            stringBuffer.append(x);
        }
        tmp = Double.parseDouble(stringBuffer.toString());
        System.out.println("Juliet: Romeo, Romeo! Wherefore art thou Romeo? (<-" + tmp + ")");
        return tmp;
    }

    public void declareLove() throws IOException{

        String message = "Good night, good night!\n" +
                "Parting is such sweet sorrow,\\n That I shall say good night till it be morrow.";
        OutputStream outputStream = serviceMailbox.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        outputStreamWriter.write( message + "J" + currentLove + "#");
        outputStreamWriter.flush();
    }

    public double renovateLove(double partnerLove){
        System.out.println("Juliet: Come, gentle night, come, loving black-browed night,\n" +
                "       Give me my Romeo, and when I shall die,\n" +
                "       Take him and cut him out in little stars.");
        currentLove = currentLove+(-b*partnerLove);
        return currentLove;
    }

    public void run () {
        try {
            while (!this.isInterrupted()) {
                double RomeoLove = this.receiveLoveLetter();
                this.renovateLove(RomeoLove);
                this.declareLove();
            }
        }catch (Exception e){
            System.out.println("Juliet: " + e);
        }
        if (this.isInterrupted()) {
            System.out.println("Juliet: I will kiss thy lips.\n" +
                    "Haply some poison yet doth hang on them\n" +
                    "To make me die with a restorative.");
        }
    }
}
