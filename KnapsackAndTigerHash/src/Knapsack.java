import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.security.spec.AlgorithmParameterSpec;



public class Knapsack{
	public int []P;// = {2,3,7,14,30,57,120,251}; //privatni kljuc
	public int m; //multiplikator
	public int n; //logicki nastavak
	public int []J; //javni kljuc
	public int im; //inverzna vrednost broja m
	
	private int weightCount;
	private int maxValue;
	
	public Knapsack(String pk,int multiplikator,int n){
		this.J = new int[8];
		this.P = new int[8];
		this.m = multiplikator;
		this.n = n;
		this.generatePrivateKey(pk);
		for(int i=0;i<8;i++){
			this.J[i]= (this.P[i]*this.m)%this.n;
		}
		int j = 0;
		while((this.n*j + 1)%this.m != 0){
			j++;
		}
		this.im = (this.n*j + 1)/this.m;
	}
	
	public void generatePrivateKey(String s){
		String [] superIncreasing = s.split(",");
		for(int i = 0 ; i< superIncreasing.length;i++){
			this.P[i]=  Integer.parseInt(superIncreasing[i]);
		}
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
	
    public String encrypt(String message) {
       // message = message.replaceAll("[^a-zA-Z0-9]", "");
       // message = message.toUpperCase();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            String c = Character.toString(message.charAt(i));
        	char [] B = chartobin(c).toCharArray();
        	int C = 0;
        	for(int j=0; j<8; j++){
        		if((int)B[j] == 48)
        			C+=this.J[j] * 0;
        		else{
        			C+=this.J[j]*1;
        		}	
        	}
            result.append(C + " ");
        }
        return result.toString();
    }
 
    public String ksDecrypt(int C){
    	int tc = (C*im)%this.n;
    	boolean signal = false;
    	String M = "";
    	for (int i=7;i>=0;i--){
    		if(signal == true || tc == P[i]){
    			if(signal == false){
    				M = "1" + M;
    				signal = true;
    			}
    			else{
    				M = "0" + M;
    			}
    		}
    		else{
    			if(tc>P[i]){
    				M = "1" + M;
    				tc-=P[i];
    			}
    			else{
    				M = "0" + M;
    			}
    		}
    	}
    	return bintochar(M);
    }
    
    public String decrypt(String cipherText) {
       // cipherText = cipherText.trim().replaceAll("[^a-zA-Z0-9,\\s]", "");
        String[] texts = cipherText.split(" ");
        StringBuilder result = new StringBuilder();   
        
        for(int i=0;i<texts.length;i++){
        	String c = "";
        	c = c + ksDecrypt(Integer.parseInt(texts[i]));
        	result.append(c);
        }
       return result.toString();
       
    }
//	public static void main(String []args){
//		String s = "2,3,7,14,30,57,120,251";
//		Knapsack kp = new Knapsack(s,41,491);
//		String a = kp.encrypt("A");
//		System.out.println(a);
//		System.out.println(kp.decrypt(a));
//		for(int i =0; i<kp.P.length;i++){
//			System.out.println(kp.P[i]);
//		}
//	}

}
