
public class BasicOperation {
	int bin[] = new int[100];
	int xor[] = new int[100];
	int temp1[] = new int[100];
	int temp2[] = new int[100];
	int len;
	int xorlen;
	
	public int binaryToDecimal(int myNum)
    {
        int dec = 0, no, i, n = 0;
        no = myNum;
        // Find total digit of no of inupted number
        while (no > 0)
        {
            n++;
            no = no / 10;
        }
        // Convert inputed number into decimal
        no = myNum;
        for (i = 0; i < n; i++)
        {
            int temp = no % 10;
            dec = dec + temp * ((int) Math.pow(2, i));
            no = no / 10;
        }
        return dec;
    }
 
    public int decimalToBinary(int myNum)
    {
        int j, i = -1, no, temp = 0;
        no = myNum;
        int t[] = new int[100];
        while (no > 0)
        {
            i++;
            temp = no % 2;
            t[i] = temp;
            no = no / 2;
        }
        len = (i + 1);
        j = -1;
        for (i = len; i >= 0; i--)
        {
            j++;
            bin[j] = t[i];
        }
        return len;
    }
 
    // find the specific bit value of binary number at given position
    public int binaryArrayAtPosition(int pos)
    {
        return bin[pos];
    }
 
    public int xorinArrayAt(int pos)
    {
        return xor[pos];
    }
 
    // perform the binary X-OR operation
    public int xorop(int a[], int b[], int arrlen)
    {
        int i;
        for (i = 0; i < arrlen; i++)
        {
            xor[i] = (a[i] == b[i]) ? 0 : 1;
        }
        xorlen = i;
        return xorlen;
    }
 
    // perform the binary X-OR operation
    public int xorop(String s, char c[])
    {
        int i = -1;
        for (i = 0; i < s.length(); i++)
        {
            xor[i] = (s.charAt(i) == c[i]) ? 0 : 1;
        }
        xorlen = i;
        return xorlen;
    }
 
    public int getLen()
    {
        return len + 1;
    }
 
    // display binary bit pattern or the array
    public void displayBinaryArray()
    {
        for (int i = 0; i <= len; i++)
        {
            System.out.println("\n Binary Array :" + bin[i]);
        }
    }
}
