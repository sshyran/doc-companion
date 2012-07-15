/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * txteditor.java
 *
 * Created on 15 Nov, 2011, 7:50:01 PM
 */
package client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Ayush
 */
public class txteditor extends javax.swing.JFrame implements ActionListener , WindowListener{

    /** Creates new form txteditor */
    public txteditor() {
        super("Text Editor");
        initComponents();
        JMenuBar menuBar = new JMenuBar();
    
    JMenu fileMenu = new JMenu("File");
    JMenuItem saveMenuItem = new JMenuItem("Save");
    saveMenuItem.addActionListener(this);
    fileMenu.add(saveMenuItem);
    JMenuItem openMenuItem = new JMenuItem("Open");
    openMenuItem.addActionListener(this);
    fileMenu.add(openMenuItem);
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    exitMenuItem.addActionListener(this);
    fileMenu.add(exitMenuItem);

    JMenu editMenu = new JMenu("Edit");
    JMenu chngeFtClrMenuItem = new JMenu("Font Color");
    JMenuItem redMenuItem = new JMenuItem("Red");
    redMenuItem.addActionListener(this);
    chngeFtClrMenuItem.add(redMenuItem);
    JMenuItem blackMenuItem = new JMenuItem("Black");
    blackMenuItem.addActionListener(this);
    chngeFtClrMenuItem.add(blackMenuItem);
    JMenuItem blueMenuItem = new JMenuItem("Blue");
    blueMenuItem.addActionListener(this);
    chngeFtClrMenuItem.add(blueMenuItem);
    JMenuItem greenMenuItem = new JMenuItem("Green");
    greenMenuItem.addActionListener(this);
    chngeFtClrMenuItem.add(greenMenuItem);
    editMenu.add(chngeFtClrMenuItem);

    
    JMenuItem clearMenuItem = new JMenuItem("Clear");
    clearMenuItem.addActionListener(this);
    editMenu.add(clearMenuItem);

  
    menuBar.add(fileMenu);
    menuBar.add(editMenu);

   
    setJMenuBar(menuBar);
   addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt){try {
                    save();
                } catch (IOException ex) {
                    Logger.getLogger(txteditor.class.getName()).log(Level.SEVERE, null, ex);
                }
}       });
   
    }
     protected void save() throws IOException 
   {
   JFileChooser dialog = new JFileChooser();
   File file = new File("enter the name for the file");
   dialog.setSelectedFile(file);
   if (dialog.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) 
   {
   file = dialog.getSelectedFile();
   FileWriter writer = new FileWriter(file);
   writer.append(jTextPane1.getText());
   writer.close();
   }
   else if(dialog.showSaveDialog(this) == JFileChooser.CANCEL_OPTION)
   {
       System.out.println("nothing will be saved");
       dispose();
   }
   } 
     protected void open() throws IOException
    {
         JFileChooser chooser=new  JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        File f = chooser.getSelectedFile();
        BufferedReader br=new BufferedReader(new FileReader(f));
        String st="";
        while((st=br.readLine())!=null){
        //  FileWriter writer = new FileWriter(f);
        jTextPane1.setText(st);
            System.out.println(st);
        }
    }
    }
    @Override
     public void windowActivated(WindowEvent e)
     {
         
     }
    @Override
      public void windowClosed(WindowEvent e)
     {
         
     }
    
    @Override
       public void windowClosing(WindowEvent e) 
     {
        try
        {save();
        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }
     }
        
    @Override
       public void windowDeactivated(WindowEvent e)
     {
         
     }
    @Override
         public void windowIconified(WindowEvent e)
     {
         
     }
    @Override
     public void windowDeiconified(WindowEvent e)
     {
         
     }
    @Override
           public void windowOpened(WindowEvent e)
     {
         
     }
      public void actionPerformed(ActionEvent e) {
	String action = e.getActionCommand();
	if ("Save".equals(action)) {
	try {
	   save();
	   JOptionPane.showMessageDialog(null, "File has been saved.");
	} catch (IOException e1) {
           JOptionPane.showMessageDialog(null, "Could not save the file.");
  	  }

    } 
       else if ("Open".equals(action)) {
	try {
	   open();
	 
	} catch (IOException e1) {
          e1.printStackTrace();
  	  }

    } 
        else if ("Exit".equals(action)) {
            if (JOptionPane.showConfirmDialog(new JFrame(),
        "Do you want to quit this application ?", "Title",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
      System.exit(0);
          

      } 
	else if ("Red".equals(action)) {
	  jTextPane1.setForeground(Color.red);

       }
        else if ("Black".equals(action)) {
	  jTextPane1.setForeground(Color.black);
        }

        else if ("Blue".equals(action)) {
          jTextPane1.setForeground(Color.blue);

	} 

	else if ("Green".equals(action)) {
   	   jTextPane1.setForeground(Color.green);
	}

	else if ("Clear".equals(action)) {
	   jTextPane1.setText(null);
        }

   }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jScrollPane1.setViewportView(jTextPane1);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(txteditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(txteditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(txteditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(txteditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new txteditor().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
