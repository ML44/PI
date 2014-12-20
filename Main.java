

import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.math.BigInteger;
	
public class Main {
	  
	  static BigInteger gcd(BigInteger x,BigInteger y) {
			BigInteger r0 = x, r1 = x, r2 = y;
			
			while(!(r2.equals(new BigInteger("0"))))
			{
				r0 = r1;
				r1 = r2;
				r2 = r0.remainder(r1);
			}
			return r1;
		}
	  
	  static List<BigInteger> textToList(String nom) throws IOException {
			BufferedReader in = new BufferedReader(new FileReader(""+nom));
			String line;
			String nombre;
			List<BigInteger> liste = new ArrayList<BigInteger>();
			while ((line = in.readLine()) != null)
			{
				nombre = "";
				for(int k =16;k<line.length();k++)
				{
					nombre = nombre + line.charAt(k);
				}
				liste.add(new BigInteger(nombre));
				
				//  System.out.println (nombre);
			}
			in.close();
			return liste;
	  }

	
		
	public static void main(String[] args)  throws IOException {

		List<BigInteger> liste = textToList("keys/keys100.txt");
		for(int i=0; i+1<liste.size(); i++)
		{
			BigInteger a = liste.get(i);
			
			for(int j=i+1; j<liste.size(); j++)
			{
				BigInteger b = liste.get(j);
				BigInteger d = gcd(a,b);
				if(d.toString().length()>5)
				{
					System.out.println("--------------");
					System.out.println("PGCD(key"+(i+1)+",key"+(j+1)+") = ");
					System.out.println(d.toString());
					System.out.println("--------------");
				}
				
			}
		}
		
			
	}

}
