/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.net.*;
import java.io.*;
import java.lang.Byte;

/**
 *
 * @author Pegasus
 */
public class srvrconnection {
   public BufferedReader in;
   private PrintWriter out;
   InputStream inst;
   OutputStream otst;
   int len;
    public srvrconnection(String srvrurl) throws MalformedURLException, UnknownHostException, IOException
   {
       InetAddress addr=InetAddress.getByName(srvrurl);
       Socket sc=new Socket(addr,4000);
       System.out.println("Connecting to server " +
              sc.getLocalAddress().toString() + " " + sc.getPort() );
       in=new BufferedReader(new InputStreamReader(sc.getInputStream()));
       out=new PrintWriter(sc.getOutputStream(),true);
       inst=sc.getInputStream();
       otst=sc.getOutputStream();
   }
    public void messageo(byte buf) throws IOException{
        otst.write(buf);
    }
    public void message(String msg) throws IOException
    {
        out.println(msg);
        out.flush();
        System.out.println(msg);//to be deleted
    }
    public void messagec(char msg) throws IOException
    {
        out.println(msg);
        out.flush();
        System.out.println(msg);//to be deleted
    }
    public byte replyi() throws IOException{
        byte buf;
        buf=(byte)inst.read();
        return buf;
    }

 
    public String reply() throws IOException
    {
        return in.readLine();
    }
}
