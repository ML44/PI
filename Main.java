

import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.math.BigInteger;

//import sun.security.util.BigInt;
	
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
			//while ((line = in.readLine()) != null)
			for(int i = 1;i<1000;i++)
			{
				line=in.readLine();
				nombre = "";
				for(int k =17;k<line.length();k++)
				{
					nombre = nombre + line.charAt(k);
				}
				liste.add(new BigInteger(nombre));
				
				//  System.out.println (nombre);
			}
			in.close();
			return liste;
	  }

	public Arbre construitArbreVide(int n) {
		if(n==0)
		{
			return null;
		}
		if(n==1)
		{
			return new Arbre();
		}
		else
		{
			int b = n/2;
			int a = n - b;
			return new Arbre(construitArbreVide(a),construitArbreVide(b));
		}		
	}
	
	public static Arbre construitArbreProduit(List<BigInteger> liste, int a, int b)
	{
		Arbre mon_arbre;
		if(a==b)
		{
			mon_arbre = new Arbre();
			mon_arbre.produit = liste.get(a);
		}
		else
		{
			int m = (b+a)/2; //?
			Arbre mon_arbre_gauche = construitArbreProduit(liste, a, m);
			Arbre mon_arbre_droit = construitArbreProduit(liste, m+1, b);
			mon_arbre = new Arbre(mon_arbre_gauche, mon_arbre_droit);
			mon_arbre.remplaceProduitFils();
		}

		return mon_arbre;
	}
	
	public static Arbre construitArbreProduit(List<BigInteger> liste)
	{
		int n = liste.size();
		return construitArbreProduit(liste, 0, n-1);//start ˆ 0 ou 1 ?
	}
	
		
	public static void main(String[] args)  throws IOException {
/*
		long tStart = System.currentTimeMillis();

		List<BigInteger> liste = textToList("keys/keys1000.txt");
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
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		System.out.print(tDelta);
	*/	
		
	/*	
		Arbre mon_arbre1 = new Arbre();
		mon_arbre1.produit = new BigInteger("3");
		Arbre mon_arbre2 = new Arbre();
		mon_arbre2.produit = new BigInteger("2");
		Arbre mon_arbre = new Arbre(mon_arbre1,mon_arbre2);
		mon_arbre.remplaceProduitFils();
	*/
		List<BigInteger> ma_liste = new ArrayList<BigInteger>();
		ma_liste.add(new BigInteger("3"));
		ma_liste.add(new BigInteger("2"));
		ma_liste.add(new BigInteger("2"));
		ma_liste.add(new BigInteger("2"));


		Arbre mon_arbre = construitArbreProduit(ma_liste);
		mon_arbre.print();
		
	}
	
		
	

}
