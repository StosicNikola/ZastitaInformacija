import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class KnapsackAndTigerHashEngine implements ActionListener{
	JFileChooser jfc;
	Projekat3 p3;
	StringBuffer buffer;
	
	public KnapsackAndTigerHashEngine(Projekat3 p){
		this.p3= p;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton but = null;
		JTextArea up = null;
		Object o = arg0.getSource();
		if(o instanceof JButton){
			but = (JButton)o;
			String valu = but.getText();
			if(valu == "Load file" || valu == "Save file"){
				this.jfc = new JFileChooser();
				int result;
				jfc.setCurrentDirectory(new File(System.getProperty("user.home")));
				if(valu == "Load file"){
				  this.p3.cleanTwoTextArea();
				  result = this.jfc.showOpenDialog(this.p3.kp);
				  this.jfc.setDialogTitle("Specify a file to load");
				}
				else{
					this.jfc.setDialogTitle("Specify a file to save");
					result = this.jfc.showSaveDialog(this.p3.kp);
				}
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = jfc.getSelectedFile();
				   try {
					   if(valu == "Load file"){
						   UploadFile(selectedFile.getAbsolutePath());
					   }
					   else{
					        SaveFile(selectedFile.getAbsolutePath());
					   }
				   } catch (IOException e) {
					e.printStackTrace();
				   }
				}
		    	if(valu == "Load file"){
				    this.p3.afterLoad();	
			    }
			}
		    else if(valu == "Kript"){
		    	int m = Integer.parseInt(p3.getMultiplikator());
		    	int n =Integer.parseInt(p3.getN());
		    	Knapsack kp = new Knapsack(p3.getPrivateKey(),m,n);
		    	String s = p3.getTextUpload();
	    		p3.setTextResul(kp.encrypt(s));
		    }
		    else if(valu == "Dekript"){
		    	int m = Integer.parseInt(p3.getMultiplikator());
		    	int n =Integer.parseInt(p3.getN());
		    	Knapsack kp = new Knapsack(p3.getPrivateKey(),m,n);
		    	String s = p3.getTextUpload();
		    	p3.setTextResul(kp.decrypt(s));
		    }
		 }
	}
	
	public void UploadFile(String s) throws IOException{
	    this.buffer = new StringBuffer();
		try{
		 FileInputStream myFile = new FileInputStream(s);
		 InputStreamReader isr = new InputStreamReader(myFile,"UTF8");
		 Reader reader = new BufferedReader(isr);
		 int ch;
		 while((ch=reader.read())>-1){
	       this.buffer.append((char)ch);
		 }
		 this.p3.setTextUpload(this.buffer.toString());
		}
		catch (IOException ioe){
			JOptionPane.showConfirmDialog(null,"Could not read file "+ ioe.toString(),"Warning",JOptionPane.ERROR_MESSAGE);
		}
	}
	
    public void SaveFile(String s){
		try{
		 FileOutputStream myFile = new FileOutputStream(s);
		 try(OutputStreamWriter osw = new OutputStreamWriter(myFile,StandardCharsets.UTF_8)){
			 osw.write(this.p3.getTextResul());
		 }
		 catch (IOException ioe){
				JOptionPane.showConfirmDialog(null,"Could not write file "+ ioe.toString(),"Warning",JOptionPane.ERROR_MESSAGE);
		 }
		 this.p3.cleanTwoTextArea();
		 this.p3.fistState();
		}
		catch (IOException ioe){
			JOptionPane.showConfirmDialog(null,"Could not write file "+ ioe.toString(),"Warning",JOptionPane.ERROR_MESSAGE);
		}
    }

}
