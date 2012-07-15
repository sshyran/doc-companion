/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Pegasus
 */
public class srvrthread extends Thread{
 private Socket client;
 PrintWriter out;
 BufferedReader in;
 dbconnect db;
 String home;
 String pwd;
 ResultSet rs;
 OutputStream otst;
 InputStream inst;
    srvrthread(Socket client,javax.swing.JTextArea conn,String ho) throws IOException
    {
        super("ServerThread");
        this.client=client;
        home=ho;
        conn.append("client at"+client.getInetAddress().toString()+" at port:"+client.getPort()+"\n");
        out=new PrintWriter(client.getOutputStream(),true);
        in=new BufferedReader(new InputStreamReader(client.getInputStream()));
        db=new dbconnect();
        pwd="doccompanion";
        otst=client.getOutputStream();
        inst=client.getInputStream();
        //System.out.println(client.getInetAddress()+" attached and listen"+client.getPort());
    }
    public void run()
    {
    boolean rep=true;
    boolean rswasnull=false;
    String s;
    conwhi:while(rep){
        try {
            s=in.readLine();            
            System.out.println(s);
            if(s.equals("login"))
            {rs=db.exeqcommand("select * from usr_info where login_id=\""+in.readLine()+"\"");
            rswasnull=true;
             while(rs.next())
             {
                 out.println(rs.getString("password"));
                 out.flush();
                 rswasnull=false;
             }
            if(rswasnull){
                out.println("1wrongpass@23");
                out.flush();
            }
            else
            {
                continue conwhi;
            }
            
            }
            else
                if(s.equals("forgot password"))
                {
                    String log_in=in.readLine();
                    if(!log_in.equals("pass update fail")){
                        rswasnull=true;
                        rs=db.exeqcommand("select sec_qst,sec_ans,login_id from usr_info where login_id=\""+log_in+"\"");
                        while(rs.next())
                        {
                            rswasnull=false;
                            if(log_in.equals(rs.getString("login_id")))
                                    {
                                        out.println("true");
                                        out.println(rs.getString("sec_qst"));
                                        out.println(rs.getString("sec_ans"));
                                        String ans=in.readLine();
                                        System.out.println(ans);
                                        if(ans.equals("update")){
                                            int repl=db.updt("update usr_info set password = '"+in.readLine()+"'"+" where login_id=\""+log_in+"\"");
                                            out.println(repl);
                                        }
                                        else
                                            continue conwhi;
                                    }
                            else
                            {
                                 if(rs.wasNull()){
                                   out.print("false");
                                   continue conwhi;
                                  }
                            }
                        }  
                    }
                    else{
                        continue conwhi;
                    }
                    
                }
            else
                    if(s.equals("upload")){
                        byte buf;
                        char c;
                        File f=new File(home+System.getProperty("file.separator")+pwd+System.getProperty("file.separator")+in.readLine());
                        try{
                            if(!f.exists())
                            f.createNewFile();
                        }catch(IOException e){
                            e.printStackTrace();
                        }catch(SecurityException e){
                            e.printStackTrace();
                        }
                        long loop=new Long(in.readLine()).longValue();
                        System.out.print(loop);
                        FileWriter fw=new FileWriter(f);
                   
                        for(long count=1;count<=loop;count++){
                            fw.write(in.readLine());
                            System.out.println(count);                            
                    }
                    //buf=(byte) inst.read();
                        db.updt("insert into filebrowser values(\""+f.getName()+"\",\""+pwd+"\" )");
                        fw.close();
                    
                    }
            else
                        if(s.equals("files")){
                            rs=db.exeqcommand("select count(filename) as co from filebrowser");
                       while(rs.next()){
                          out.println(rs.getString("co"));
                             }
                         rs=db.exeqcommand("select filename from filebrowser");
                        while(rs.next()){
                             out.println(rs.getString("filename"));
                         }
                        }
            else
                            if(s.equals("download")){
                              File f=new File(home+System.getProperty("file.separator")+pwd+System.getProperty("file.separator")+in.readLine());
                                out.println(f.length());
                                FileReader fi=new FileReader(f);  
                              int i;
                              byte b;
                              //String ch;
            while(( i=fi.read())!=-1){
                /*for(int q=0;q<20;q++) {
                   b[q*2] = (byte) (cbuf[q] >> 8);
                   b[q*2+1] = (byte) cbuf[q];
                     }
                srvr.messageo(b);*/
                out.println((char)i); 
            }   
            fi.close();
            
                            }
            else
                                if(s.equals("delete file")){
                                    String t=in.readLine();
                                    File f=new File(home+System.getProperty("file.separator")+pwd+System.getProperty("file.separator")+t);
                                    db.updt("delete from filebrowser where filename=\""+t+"\"");
                                    f.delete();
                                }
            else
                                    if(s.equals("send mail")){
                                        String sdr,rcvr,date,subj,mail;
                                        sdr=in.readLine();
                                        rcvr=in.readLine();
                                        mail=in.readLine();
                                        date=in.readLine();
                                        subj=in.readLine();
                                        int ro=db.updt("insert into maildb values(\"" +sdr+ "\" , \"" +rcvr+ "\" , \"" +mail+"\" , \"" +date+ "\" , \""+subj+"\")");
                                        out.println(ro);
                                    }
            else
                                        if(s.equals("retrieve subject")){
                                            String logid=in.readLine();
                                            rs=db.exeqcommand("select subject from maildb where receiverid=\""+logid+"\"");
                                            int count=0;
                                            while(rs.next()){
                                                count++;
                                            }
                                            out.println(count);
                                            rs=db.exeqcommand("select subject from maildb where receiverid=\""+logid+"\"");
                                            while(rs.next()){
                                                out.println(rs.getString("subject"));
                                            }
                                        }
            else
                                            if(s.equals("firstname")){
                                                rs=db.exeqcommand("select first_name from usr_info where login_id=\""+in.readLine()+"\"");
                                                while(rs.next()){
                                                    out.println(rs.getString("first_name"));
                                                }
                                            }
            else
                                                if(s.equals("message retrieve")){
                                                    String logid=in.readLine();
                                                    rs=db.exeqcommand("select * from maildb where receiverid=\""+logid+"\" and subject=\""+in.readLine()+"\"");
                                                    while(rs.next()){
                                                        out.println(rs.getString("senderid"));
                                                        out.println(rs.getString("msg"));
                                                    }
                                                }
                                //here
            /*System.out.println();
             out.println("hello back");
           out.flush();
           System.out.println("after writing");*/
        
        } catch(SocketException e){
            rep=false;
        } catch (SQLException ex) {
            out.print("false");
            continue conwhi;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
    
}
