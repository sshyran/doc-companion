/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.*;
import java.net.*;


/**
 *
 * @author Pegasus
 */
public class srvrsocket implements Runnable{
    private ServerSocket srvr;
    private boolean listen;
    private Thread srvrth;
    private javax.swing.JLabel error;
    private javax.swing.JTextArea connect;
    private String homedoc;
    public srvrsocket(javax.swing.JLabel err,javax.swing.JTextArea conn,String home) throws IOException
    {
        srvr=new ServerSocket(4000);       
        error=err;
        homedoc=home;
        connect=conn;
        error.setText("Server ready to start");
        error.paintImmediately(error.getX(), error.getY(), 100, 15);
    }
    public void listen()
    {
        listen=true;
        error.setText("Server started");
        error.paintImmediately(error.getX(), error.getY(), 100, 15);
    }
    public void cease()
    {
        listen=false;
        try{error.setText("Shutting threads");
            error.paintImmediately(error.getX(), error.getY(), 100, 15);
            srvrth.join();
        }catch(InterruptedException e){
            error.setText("Error in closing server");
            error.paintImmediately(error.getX(), error.getY(), 100, 15);
        }
        error.setText("Server down");
        error.paintImmediately(error.getX(), error.getY(), 100, 15);
    }

    public void run()
    {
        while(listen)
        {
            try {
                new srvrthread(srvr.accept(),connect,homedoc).start();
            } catch (IOException ex) {
                error.setText("Error in starting server");
            }
        }
        
    }
    public void strtsrvr() throws IOException,SocketException
    {
    
        srvrth=new Thread(this,"Server thread(main)");
        srvrth.start();//   Socket client=srvr.accept();
        //   System.out.println(client.getInetAddress()+" attached and listen"+client.getPort());
        //   BufferedReader bf=new BufferedReader(new InputStreamReader(client.getInputStream()));
          // while((ch=(char)bf.read())!=-1)
        //       System.out.println(bf.readLine());
      /*     System.out.println("after reading");
           PrintWriter out=new PrintWriter(client.getOutputStream(),true);
           out.println("hello back");
           out.flush();
           System.out.println("after writing");*/
    
    //    srvr.close();
    }
}
