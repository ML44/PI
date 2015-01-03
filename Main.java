
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.math.BigInteger;
	
public class Main {
	  
	  static BigInteger gcd(BigInteger x,BigInteger y) 
	  //Calcule de PGCD de deux BigInteger (soustractions)
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
	  
	  static List<BigInteger> textToList(String nom, int nbLignes, int nbCar) throws IOException 
	  //Convertit le fichier donné en entrée en une liste de BigInteger
	  //nom = nom du fichier
	  //nbLignes = nombre de lignes à prendre en compte
	  //(souvent c'est tout et on pourrait mettre la boucle while mais il faut parfois faire le calcul sur la moitié)
	  //nbCar = nombre de caractères au début de chaque ligne avant le debut du nombre !
	  {
			BufferedReader in = new BufferedReader(new FileReader(""+nom));
			String line;
			String nombre;
			List<BigInteger> liste = new ArrayList<BigInteger>();
			//while ((line = in.readLine()) != null)
			for(int i = 1;i<nbLignes;i++)
			{
				line=in.readLine();
				nombre = "";
				for(int k =nbCar;k<line.length();k++)
				{
					nombre = nombre + line.charAt(k);
				}
				liste.add(new BigInteger(nombre));
			}
			in.close();
			return liste;
	  }

	  static int nbChiffres(int n)
	  {
		  int k=0;
		  while(n>0)
		  {
			  n=n/10;
			  k=k+1;
		  }
		  return k;
	  }
	  
	  static void go(int n) throws IOException 
	  {
			List<BigInteger> ma_liste = textToList("keys/keys"+n+".txt", n, nbChiffres(n)+13);
			Arbre mon_arbre = Arbre.construitArbreProduit(ma_liste);
			mon_arbre.completeArbreReste();
			mon_arbre.afficheFacteursPremiers();
	  }

	public static void main(String[] args)  throws IOException {
		go(1000);
	}

}
