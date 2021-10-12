
public class Square {
	private int n;
	private char[][] skey;
	public String abcd = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	//konstruktor
	public Square(int brEl){
		this.n = brEl;
		skey = new char[brEl][brEl];
	}
	
	
	public void setKey(String s){
		char[] chars = s.toCharArray();
		int k = 0;
		for(int i=0;i<this.n;i++){
			for(int j=0;j<this.n;j++){
				this.skey[i][j] = chars[k];
				k++;
			}
		}
	}
	public void print(){
		for(int i=0;i<this.n;i++){
			System.out.println("|");
			for(int j=0;j<this.n;j++){
				
				System.out.println(this.skey[i][j]);
			}
			System.out.println("|");
		}
	}
	
	//Pronadji slovo i vrati njegovu poziciju npr. ske 41%10=1 ; 41/10 = 4 ; i = 4 ; j = 1
	public int getPosition(char c){
		int retValu = 0;
		for(int i=0;i<this.n;i++){
			for(int j=0;j<this.n;j++){
				if(this.skey[i][j] == c){
					retValu = i*10 + j;
				}
			}
		}
		return retValu;
	}
	
	public char getCharacter(int i,int j){
		return this.skey[i][j];
	}
	
	
	public static void main(String[] args){	
		Square s1  = new Square(5);
		//s1.setKey("ABCDEFGHIJKLMNOPQRSTVWZYX");
		s1.setKey(s1.abcd);
		//s1.print();
//		int a = s1.getPosition('A');
//		int i = a/10;
//		int j = a%10;
//		System.out.println(a);
//		System.out.println("i : " + i);
//		System.out.println("j : " + j);
//		System.out.println(s1.getCharacter(0,0));
		
	}
}
