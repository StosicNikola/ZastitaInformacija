import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.*;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.image.BufferedImage;
public class OneTimePadEngine implements ActionListener {
	//One time pad
	JFileChooser jfc;
	OneTimePad otp;
	char keyValue[];
	int valueOfCode;
	boolean signal;
	StringBuffer buffer ;
	
	//Four Square Cipher
	Square square1;
	Square square2;
	Square square3;
	Square square4;
	
	
	public OneTimePadEngine(OneTimePad a){
		this.otp = a;
		this.keyValue = new char[8];
		this.signal = false;
	}
	
	public void setSquare12(){
		this.square1 = new Square(5);
		this.square2 = new Square(5);
		this.square3 = new Square(5);
		this.square4 = new Square(5);
		this.square1.setKey(this.square1.abcd);
		this.square2.setKey(this.square2.abcd);
	}
	
	public void setKeypad(){
		if(signal == false){
			for(int i =0;i<8;i++){
				this.keyValue[i]= (i % 2 == 0) ? '1' : '0';
			}
			this.signal = true;
		}
		
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
				  this.otp.cleanTwoTextArea();
				  result = this.jfc.showOpenDialog(this.otp.otp);
				  this.jfc.setDialogTitle("Specify a file to load");
				}
				else{
					this.jfc.setDialogTitle("Specify a file to save");
					result = this.jfc.showSaveDialog(this.otp.otp);
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
				    this.otp.afterLoad();	
			    }
			}
		    else if(valu == "Encrypt/Decrypt"){
		    	encryptDecrypt(otp.getTextUpload());
		    	otp.afterLoad();
		    }
		    else if(valu == "EncryptFSC"){
		    	setFourSquareKeys();
		    	otp.setTextResul(encryptFSC(otp.getTextUpload()));
		    	otp.afterLoad();
		    }
		    else if(valu == "DecryptFSC"){
		    	setFourSquareKeys();
		    	otp.setTextResul(decryptFSC(otp.getTextUpload()));
		    }
		    else{
		    	if(valu == "Load BM24" || valu == "Save BM24"){
					this.jfc = new JFileChooser();
					int result;
					jfc.setCurrentDirectory(new File(System.getProperty("user.home")));
					if(valu == "Load BM24"){
					  this.otp.cleanTwoTextArea();
					  result = this.jfc.showOpenDialog(this.otp.otp);
					  this.jfc.setDialogTitle("Specify a file to load");
					}
					else{
						this.jfc.setDialogTitle("Specify a file to save");
						result = this.jfc.showSaveDialog(this.otp.otp);
					}
					if (result == JFileChooser.APPROVE_OPTION) {
					    File selectedFile = jfc.getSelectedFile();
					   if(valu == "Load BM24"){
						   LoadBM24(selectedFile.getAbsolutePath());
					   }
					   else{
					        SaveBM24(selectedFile.getAbsolutePath());
					   }
					}
			    	if(valu == "Load file"){
					    this.otp.afterLoad();	
				    }
				}
		    }
		}
	}
	
	public void LoadBM24(String s){
		 BufferedImage image = null;
			try{
			 FileInputStream myFile = new FileInputStream(s);
			 image = ImageIO.read(myFile);
			 ByteArrayOutputStream bos = new ByteArrayOutputStream();
			 ImageIO.write(image,"bmp", bos);
			 byte [] data = bos.toByteArray();
			 String res = chartobin(data.toString());
			 this.otp.setTextUpload(res); 
			}
			catch (IOException ioe){
				JOptionPane.showConfirmDialog(null,"Could not read file "+ ioe.toString(),"Warning",JOptionPane.ERROR_MESSAGE);
			}	
	}
	
	public void SaveBM24(String s){
//		try{
//			 FileOutputStream myFile = new FileOutputStream(s);
//			 String a = this.otp.getTextResul();
//			 byte[] data = a.getBytes();
//			 InputStream in = new ByteArrayInputStream(data);
//			 BufferedImage image = ImageIO.read(in);
//			 ImageIO.write(image, "", myFile);
//			 this.otp.cleanTwoTextArea();
//			 this.otp.fistState();
//			}
//		catch (IOException ioe){
//				JOptionPane.showConfirmDialog(null,"Could not write file "+ ioe.toString(),"Warning",JOptionPane.ERROR_MESSAGE);
//		}
	}
	
	public void UploadFile(String s) throws IOException{
	    this.buffer = new StringBuffer();
		try{
		 FileInputStream myFile = new FileInputStream(s);
		 InputStreamReader isr = new InputStreamReader(myFile,"UTF8");
		 Reader reader = new BufferedReader(isr);
		 int ch;
		 valueOfCode=0;
		 while((ch=reader.read())>-1){
	       this.buffer.append((char)ch);
		 }
		 this.otp.setTextUpload(this.buffer.toString());
		}
		catch (IOException ioe){
			JOptionPane.showConfirmDialog(null,"Could not read file "+ ioe.toString(),"Warning",JOptionPane.ERROR_MESSAGE);
		}
	}
	
    public void SaveFile(String s){
		try{
		 FileOutputStream myFile = new FileOutputStream(s);
		 try(OutputStreamWriter osw = new OutputStreamWriter(myFile,StandardCharsets.UTF_8)){
			 osw.write(this.otp.getTextResul());
		 }
		 catch (IOException ioe){
				JOptionPane.showConfirmDialog(null,"Could not write file "+ ioe.toString(),"Warning",JOptionPane.ERROR_MESSAGE);
		 }
		 this.otp.cleanTwoTextArea();
		 this.otp.fistState();
		}
		catch (IOException ioe){
			JOptionPane.showConfirmDialog(null,"Could not write file "+ ioe.toString(),"Warning",JOptionPane.ERROR_MESSAGE);
		}
    }
    
    public void encryptDecrypt(String s3){
    	int i, j;
    	String s = chartobin(s3);
    	this.setKeypad();
        char cipherChar[] = new char[(s.length())];
        int cnt = -1;
        for (i = 0; i < s.length(); i++)
        {
            if ((int) s.charAt(i) == 48 || (int) s.charAt(i) == 49
                    || (int) s.charAt(i) == 32)
            {
                cnt++;
                cipherChar[cnt] = s.charAt(i);
            }
        }
        String s1 = new String(cipherChar);
        String s2[] = s1.split(" ");
        int data[] = new int[s2.length];
        for (i = 0; i < s2.length; i++)
        {
            data[i] = Integer.parseInt(s2[i]);
        }
         
        BasicOperation b1 = new BasicOperation();
        String plain = new String("");
        // do the XOR Operation
        for (i = 0; i < s2.length; i++)
        {
            int xorlen = b1.xorop(s2[i], this.keyValue);
            int xor[] = new int[xorlen];
            for (j = 0; j < xorlen; j++)
            {
                xor[j] = b1.xorinArrayAt(j);
                plain += xor[j];
            }
            plain += " ";
        }
        this.otp.setTextResul(bintochar(plain));
    }
 
    public String bintochar(String s){
    	String p[] = s.split(" ");
    	int i;
    	BasicOperation b1 = new BasicOperation();
        int decryptedChar[] = new int[p.length];
        char plainTextChar[] = new char[p.length];
        for (i = 0; i < p.length; i++)
        {
            decryptedChar[i] = b1.binaryToDecimal(Integer.parseInt(p[i]));
            plainTextChar[i] = (char) decryptedChar[i];
        }
    	return (new String(plainTextChar));
    }
    
    public String chartobin(String s){
//    	
    	StringBuilder result = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar))  
                            .replaceAll(" ", "0")                        
            );
            result.append(" ");
        }
        return result.toString();
    }
    public boolean cheak25characters(String s){
    	if(s.length() == 25){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public void setFourSquareKeys(){
    	String key1 = otp.getKey1();
    	String key2 = otp.getKey2();
    	JFrame frame = new JFrame();
    	if(!cheak25characters(key1)){
    		
    		JOptionPane.showMessageDialog(frame,
    			    "The key1 must have 25 characters.",
    			    "Warning message",
    			    JOptionPane.WARNING_MESSAGE);
    		otp.setKey1(null);
    		otp.setKey2(null);
    	}
    	else if(!cheak25characters(key2)){
    		JOptionPane.showMessageDialog(frame,
    			    "The key2 must have 25 characters.",
    			    "Warning message",
    			    JOptionPane.WARNING_MESSAGE);
    		otp.setKey1(null);
    		otp.setKey2(null);
    	}
    	else{
    		setSquare12();
    		this.square3.setKey(otp.getKey1());
    		this.square4.setKey(otp.getKey2());
    		otp.setTextResul(encryptFSC(otp.getTextUpload()));
    		//encryptFSC(otp.getTextUpload());
    		
    	}
    }
    
    public String encryptFSC(String s){
    	String newS = s.replace(" ","");
    	newS = newS.replaceAll("([!-@])","");
      /*  newS = s.replace(",","");
        newS = s.replace(":","");*/
    	char[] pairs = new char[2];
    	String encryptedText = "";
    	String[] EncryptText = new String[newS.length() / 2];
    	EncryptText = getPairs(newS.toUpperCase());
    	//System.out.println("Pairs: " + Arrays.toString(EncryptText));
    	for(int i=0;i<EncryptText.length;i++){
    		int a_i = 0;
    		int a_j = 0;
    		int b_i = 0;
    		int b_j = 0;
    		int aPos = 0;
    		pairs[0] = EncryptText[i].charAt(0);
    		pairs[1] = EncryptText[i].charAt(1);
    		
    		aPos = this.square1.getPosition(pairs[0]);   	
    		a_i = aPos/10;
    		a_j = aPos%10;
    		aPos = this.square2.getPosition(pairs[1]);
    		b_i = aPos/10;
    		b_j = aPos%10;
    		encryptedText += Character.toString(this.square3.getCharacter(a_i, b_j)) + Character.toString(this.square4.getCharacter(b_i, a_j));
    	}
    	return encryptedText;
    }
    
    public String decryptFSC(String s){
    	char[] pairs = new char[2];
    	String decryptedText = "";
    	String[] EncryptText = new String[s.length() / 2];
    	EncryptText = getPairs(s);
    	//System.out.println("Pairs: " + Arrays.toString(EncryptText));
    	for(int i=0;i<EncryptText.length;i++){
    		int a_i = 0;
    		int a_j = 0;
    		int b_i = 0;
    		int b_j = 0;
    		int aPos = 0;
    		pairs[0] = EncryptText[i].charAt(0);
    		pairs[1] = EncryptText[i].charAt(1);
    		
    		aPos = this.square3.getPosition(pairs[0]);   	
    		a_i = aPos/10;
    		a_j = aPos%10;
    		aPos = this.square4.getPosition(pairs[1]);
    		b_i = aPos/10;
    		b_j = aPos%10;
    		decryptedText += Character.toString(this.square1.getCharacter(a_i, b_j)) + Character.toString(this.square2.getCharacter(b_i, a_j));
    	}
    	return decryptedText;
    }
    
    
    public String[] getPairs(String s){
    	 String[] EncryptText = new String[s.length() / 2];

    	 int cursor = 0;
         for (int i = 0; i < EncryptText.length; i++) {
             EncryptText[i] = "" + s.charAt(cursor) + s.charAt(cursor + 1);
             // step by two
             cursor = cursor + 2;
         }
         return EncryptText ;
    }
    
}
