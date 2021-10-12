import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class OneTimePad {
	private static final ActionListener l = null;
	//Panels
	private JPanel windowContent;
	//TextAreas
	private JTextArea txtUpload;
	private JTextArea TxtResul;
	//Buttons
	private JButton btnUploadFile;
	private JButton btnSaveFile;
	
	private JButton btnKripDeOTP;
	
	private JButton btnLoadBM;
	private JButton btnSaveBM;
	
	private JButton btnKripFSC;
	private JButton btnDecFSC;
	//TextFields
	private JTextField TFkeysquare1;
	private JTextField TFkeysquare2;
	//Frame
	public JFrame otp;
	
	public String getTextUpload(){
		return this.txtUpload.getText();
	}
	
	public void setTextUpload(String val){
		this.txtUpload.setText(val);
	}
	
	public String getTextResul(){
		return this.TxtResul.getText();
	}
	
	public void setTextResul(String val){
		this.TxtResul.setText(val);
	}
	public void fistState(){
	//	this.btnDekrip.setEnabled(false);
	//	this.btnKrip.setEnabled(false);
		this.btnSaveFile.setEnabled(false);
	}
	public void afterLoad(){
		//this.btnDekrip.setEnabled(true);
		this.btnKripDeOTP.setEnabled(true);
		this.btnSaveFile.setEnabled(true);
	}
	
	public void cleanTwoTextArea(){
		this.setTextUpload(null);
		this.setTextResul(null);
	}
	
	public String getKey1(){
		return this.TFkeysquare1.getText();
	}
	
	public String getKey2(){
		return this.TFkeysquare2.getText();
	}
	
	public void setKey1(String s){
		this.TFkeysquare1.setText(s);
	}
	
	public void setKey2(String s){
		this.TFkeysquare2.setText(s);
	}
	
	public void cleanTwoTextField(){
		this.setKey1(null);
		this.setKey2(null);
	}
	
	public OneTimePad(){
	//prvi konrejner	
		windowContent = new JPanel();
		BorderLayout bl = new BorderLayout();
		this.windowContent.setLayout(bl);
		windowContent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Encryption methods"));
		this.txtUpload = new JTextArea(50,33);
		JScrollPane textareaScrollPane1 = new JScrollPane(this.txtUpload);
		this.windowContent.add("West",textareaScrollPane1);
		this.TxtResul = new JTextArea(50,33);
		JScrollPane textareaScrollPane2 = new JScrollPane(this.TxtResul);
		this.windowContent.add("East",textareaScrollPane2);
		
		// drugi kontejner
		JPanel centarpanel = new JPanel();
		FlowLayout gl = new FlowLayout();
		centarpanel.setLayout(gl);
		
		//File load/save
		JPanel LSFilePanel = new JPanel();
		LSFilePanel.setLayout(gl);
		LSFilePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"File"));
		LSFilePanel.setPreferredSize(new Dimension(250,75));
		
		//Bitmap load/save
		JPanel LSBMPanel = new JPanel();
		LSBMPanel.setLayout(gl);
		LSBMPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"BitMap 24"));
		LSBMPanel.setPreferredSize(new Dimension(250,75));
		
		//One time pad
		JPanel OTPPanel = new JPanel();
		OTPPanel.setLayout(gl);
		OTPPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"One time pad"));
		OTPPanel.setPreferredSize(new Dimension(250,75));
		
		//Foursquare cipher
		JPanel FSCPanel = new JPanel();
		FSCPanel.setLayout(gl);
		FSCPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Foursquare cipher "));
		FSCPanel.setPreferredSize(new Dimension(250,200));
		
		this.btnUploadFile = new JButton("Load file");
		this.btnUploadFile.setPreferredSize(new Dimension(100,40));
		this.btnSaveFile = new JButton("Save file");
		this.btnSaveFile.setPreferredSize(new Dimension(100,40));
		this.btnKripDeOTP = new JButton("Encrypt/Decrypt");
		this.btnKripDeOTP.setPreferredSize(new Dimension(130,40));
		
		this.btnLoadBM = new JButton("Load BM24");
		this.btnLoadBM.setPreferredSize(new Dimension(100,40));
		this.btnSaveBM = new JButton("Save BM24");
		this.btnSaveBM.setPreferredSize(new Dimension(100,40));
		
		this.btnKripFSC = new JButton("EncryptFSC");
		this.btnKripFSC.setPreferredSize(new Dimension(100,40));
		this.btnDecFSC = new JButton("DecryptFSC");
		this.btnDecFSC.setPreferredSize(new Dimension(100,40));
		
		this.TFkeysquare1 = new JTextField(18);
		this.TFkeysquare1.setDocument(new JTextFieldLimit(25));
		this.TFkeysquare2 = new JTextField(18);
		this.TFkeysquare2.setDocument(new JTextFieldLimit(25));
		
		LSFilePanel.add(this.btnUploadFile);
		LSFilePanel.add(this.btnSaveFile);
		centarpanel.add(LSFilePanel);
		
		LSBMPanel.add(this.btnLoadBM);
		LSBMPanel.add(this.btnSaveBM);
		centarpanel.add(LSBMPanel);
		
		
		OTPPanel.add(this.btnKripDeOTP);
		centarpanel.add(OTPPanel);
		JLabel l1 = new JLabel("keysquare 1");
		JLabel l2 = new JLabel("keysquare 2");
		
		FSCPanel.add(l1);
		FSCPanel.add(this.TFkeysquare1);
		FSCPanel.add(l2);
		FSCPanel.add(this.TFkeysquare2);
		FSCPanel.add(this.btnKripFSC);
		FSCPanel.add(this.btnDecFSC);
		centarpanel.add(FSCPanel);
		//centarpanel.add(this.btnDekrip);
		
		this.windowContent.add("Center",centarpanel);
		
		
		
//		Prozor programa
		otp = new JFrame("Cryptography");
		otp.setBounds(500,300,0,0);
		otp.setPreferredSize(new Dimension(1050,520));
		otp.add(windowContent);
		otp.setResizable(false);
		otp.pack();
		otp.setVisible(true);
		otp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//ActionListener
		OneTimePadEngine otpe = new OneTimePadEngine(this);
		this.btnUploadFile.addActionListener(otpe);
		this.btnSaveFile.addActionListener(otpe);
		this.btnKripDeOTP.addActionListener(otpe);
		this.btnLoadBM.addActionListener(otpe);
		this.btnSaveBM.addActionListener(otpe);
		this.btnKripFSC.addActionListener(otpe);
		this.btnDecFSC.addActionListener(otpe);
	}
	
	public static void main(String[] args){	
		
		OneTimePad o1 = new OneTimePad();
		o1.fistState();
	}
}
	
	
