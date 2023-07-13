package com.example.demo1;
import javafx.util.Pair;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Romeo extends Thread {
    private ServerSocket ownServerSocket = null;
    private Socket serviceMailbox = null;
    private double currentLove = 0;
    private double a = 0;

    public Romeo(double initialLove) {
        currentLove = initialLove;
        a = 0.02;
        try {
            ownServerSocket = new ServerSocket(7778, 500, InetAddress.getByName("127.0.0.1"));
            System.out.println("Romeo: What lady is that, which doth enrich the hand\n" +
                    "       Of yonder knight?");
        } catch(Exception e) {
            System.out.println("Romeo: Failed to create own socket " + e);
        }
   }

    public Pair<InetAddress,Integer> getAcquaintance() {
        System.out.println("Romeo: Did my heart love till now? forswear it, sight! For I ne'er saw true beauty till this night.");
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
            if (x == 'J') {
                break;
            }
            stringBuffer.append(x);
        }
        tmp = Double.parseDouble(stringBuffer.toString());
        System.out.println("Romeo: O sweet Juliet... (<-" + tmp + ")");
        return tmp;
    }

    public void declareLove() throws IOException{
        String a = "I would I were thy bird";
        OutputStream outputStream = serviceMailbox.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        outputStreamWriter.write(a+ "R" + currentLove + "#");
        outputStreamWriter.flush();
        serviceMailbox.close();
    }

    public double renovateLove(double partnerLove){
        System.out.println("Romeo: But soft, what light through yonder window breaks?\n" +
                "       It is the east, and Juliet is the sun.");
        currentLove = currentLove+(a*partnerLove);
        return currentLove;
    }
    public void run () {
        try {
            while (!this.isInterrupted()) {
                double JulietLove = this.receiveLoveLetter();
                this.renovateLove(JulietLove);
                this.declareLove();
            }
        }catch (Exception e){
            System.out.println("Romeo: " + e);
        }
        if (this.isInterrupted()) {
            System.out.println("Romeo: Here's to my love. O true apothecary,\n" +
                    "Thy drugs are quick. Thus with a kiss I die." );
        }
    }
}
