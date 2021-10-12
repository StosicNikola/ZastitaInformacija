import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Projekat3 {
	//Listener
	private static final ActionListener l= null;
	//JPanel
	private JPanel windowContent;
	//JFrame
	public JFrame kp;
	//JButton
	public JButton btnLoad;
	public JButton btnSave;
	public JButton btnTigerHash;
	public JButton btnKript;
	public JButton btnDekript;
	//TextAreas
	private JTextArea txtUpload;
	private JTextArea TxtResul;
	//TextFields
	private JTextField privateKey;
	private JTextField n;
	private JTextField multiplikator;
		
	
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
	
	public void cleanTwoTextArea(){
		this.setTextUpload(null);
		this.setTextResul(null);
	}
	
	public void fistState(){
		this.btnSave.setEnabled(false);
	}
	
	public void afterLoad(){
		this.btnSave.setEnabled(true);
	}
	
	public String getPrivateKey(){
		return this.privateKey.getText();
	}
	
	public String getMultiplikator(){
		return this.multiplikator.getText();
	}
	
	public String getN(){
		return this.n.getText();
	}
	
	public void setPrivateKey(String s){
		this.privateKey.setText(s);
	}
	
	public void setMultiplikator(String s){
		this.multiplikator.setText(s);
	}
	
	public void setN(String s){
		this.n.setText(s);
	}
	
	public Projekat3(){
		windowContent = new JPanel();
		BorderLayout bl = new BorderLayout();
		this.windowContent.setLayout(bl);
		this.txtUpload = new JTextArea(50,25);
		JScrollPane textareaScrollPane1 = new JScrollPane(this.txtUpload);
		this.windowContent.add("West",textareaScrollPane1);
		this.TxtResul = new JTextArea(50,25);
		JScrollPane textareaScrollPane2 = new JScrollPane(this.TxtResul);
		this.windowContent.add("East",textareaScrollPane2);
		
		//Panel centar
		JPanel centarpanel = new JPanel();
		FlowLayout gl = new FlowLayout();
		centarpanel.setLayout(gl);
		
		//File load/save panel
		JPanel LSFilePanel = new JPanel();
		LSFilePanel.setLayout(gl);
		LSFilePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"File"));
		LSFilePanel.setPreferredSize(new Dimension(250,75));
		
		//TigerHash panel
		JPanel TigerHashPanel = new JPanel();
		TigerHashPanel.setLayout(gl);
		TigerHashPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"TigerHash"));
		TigerHashPanel.setPreferredSize(new Dimension(250,75));
		
		//Knapsack panel
		JPanel KnapsackPanel = new JPanel();
		KnapsackPanel.setLayout(gl);
		KnapsackPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Knapsack"));
		KnapsackPanel.setPreferredSize(new Dimension(250,160));
		
		//Create buttons
		this.btnLoad = new JButton("Load file");
		this.btnLoad.setPreferredSize(new Dimension(100,40));
		this.btnSave = new JButton("Save file");
		this.btnSave.setPreferredSize(new Dimension(100,40));
		this.btnTigerHash = new JButton("TigerHash");
		this.btnTigerHash.setPreferredSize(new Dimension(100,40));
		this.btnKript = new JButton("Kript");
		this.btnKript.setPreferredSize(new Dimension(100,40));
		this.btnDekript = new JButton("Dekript");
		this.btnDekript.setPreferredSize(new Dimension(100,40));
		
		this.privateKey = new JTextField(18);
		this.privateKey.setDocument(new JTextFieldLimit(40));
		this.n = new JTextField(3);
		this.n.setDocument(new JTextFieldLimit(4));
		this.multiplikator = new JTextField(3);
		this.multiplikator.setDocument(new JTextFieldLimit(3));
		
		JLabel l1 = new JLabel("Private key: ");
		JLabel l2 = new JLabel("N: ");
		JLabel l3 = new JLabel("M: ");
		
		//add button to panel
		LSFilePanel.add(btnLoad);
		LSFilePanel.add(btnSave);
		centarpanel.add(LSFilePanel);
		
		TigerHashPanel.add(btnTigerHash);
		centarpanel.add(TigerHashPanel);
		
		KnapsackPanel.add(l1);
		KnapsackPanel.add(this.privateKey);
		JPanel mn = new JPanel();
		FlowLayout gl1 = new FlowLayout();
		mn.setLayout(gl1);
		mn.add(l2);
		mn.add(this.n);
		mn.add(l3);
		mn.add(this.multiplikator);
		
		KnapsackPanel.add(mn);
		KnapsackPanel.add(btnKript);
		KnapsackPanel.add(btnDekript);
		centarpanel.add(KnapsackPanel);
		
		this.windowContent.add("Center",centarpanel);
		
		//ActionListener
		KnapsackAndTigerHashEngine kathe = new KnapsackAndTigerHashEngine(this);
		this.btnLoad.addActionListener(kathe);
		this.btnSave.addActionListener(kathe);
		this.btnTigerHash.addActionListener(kathe);
		this.btnKript.addActionListener(kathe);
		this.btnDekript.addActionListener(kathe);
		
		//Frame
		this.kp = new JFrame("Knapsack");
		this.kp.setBounds(550,300,0,0);
		this.kp.setPreferredSize(new Dimension(850,370));
		this.kp.add(windowContent);
		this.kp.setResizable(false);
		this.kp.pack();
		this.kp.setVisible(true);
		this.kp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Projekat3  p3 = new Projekat3();

	}

}
