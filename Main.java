
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.math.BigInteger;

//import sun.security.util.BigInt;
	
public class Main {
	  
	  static BigInteger gcd(BigInteger x,BigInteger y) 
	  //PGCD de 2 BigInteger (par soustraction)
	  {
			BigInteger r0 = x, r1 = x, r2 = y;
			
			while(!(r2.equals(new BigInteger("0"))))
			{
				r0 = r1;
				r1 = r2;
				r2 = r0.remainder(r1);
			}
			return r1;
		}
	  
	  static List<BigInteger> textToList(String nom) throws IOException 
	  //Transformer le fichier .txt en liste de BigInteger
	  {
			BufferedReader in = new BufferedReader(new FileReader(""+nom));
			String line;
			String nombre;
			List<BigInteger> liste = new ArrayList<BigInteger>();
			//while ((line = in.readLine()) != null)  
			//Pourquoi ca marche pas ça ?
			//Ca marche mais c'était pour le test sur les 500 premières valeurs que j'ai besoin de la 2e forme
			for(int i = 1;i<1000;i++)
			{
				line=in.readLine();
				nombre = "";
				for(int k =16;k<line.length();k++)  
					// Pourquoi 17 ?
					// C'est le nombre de caractères avant le début du nombre
				{
					nombre = nombre + line.charAt(k);
				}
				liste.add(new BigInteger(nombre));
				
				//  System.out.println (nombre);
			}
			in.close();
			return liste;
	  }

	
//*********************************************************************	
	 
		
	public static void main(String[] args)  throws IOException {

		//long tStart = System.currentTimeMillis();

		List<BigInteger> liste = textToList("keys/keys100.txt");
		System.out.print(liste.toString());
		
		
		/*
		mon_arbre.completeArbreReste();
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		
		System.out.println(tDelta);
		
		System.out.println(mon_arbre.recupereFacteursPremiers().get(5));
		*/
	}
	
		
	

}
