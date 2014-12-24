
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class Arbre {
	public BigInteger produit;
	public BigInteger reste;
	public Arbre fils_gauche;
	public Arbre fils_droit;
	
	public Arbre() //Initialisation vide
	{
		produit = new BigInteger("0");
		reste = new BigInteger("0");
		fils_gauche = null;
		fils_droit = null;
	}
	
	public Arbre(Arbre fg, Arbre fd) //Initialisation avec les arbres fils
	{
		produit = new BigInteger("0");
		reste = new BigInteger("0");
		fils_gauche = fg;
		fils_droit = fd;
	}
	
	boolean isLeaf() 
	{
		return ((this.fils_droit==null)&&(this.fils_gauche==null));
	}
		
	BigInteger produitFils() //Calcule le produit des deux noeuds fils (s'ils existent)
	{
		if(!this.isLeaf())
		{
			if(this.fils_droit == null)
			{
				return this.fils_gauche.produit;
			}
			else if(this.fils_gauche == null)
			{
				return this.fils_droit.produit;
			}
			else
			{
				return this.fils_droit.produit.multiply(this.fils_gauche.produit);
			}
		}
		return null;
	}
	
	void remplaceProduitFils() //Remplace la valeur du noeud produit par le produit des fils
	{
		produit = this.produitFils();
	}
	
	 private void print(int k)   // N'est appelé que par print()
	{
		if(this!=null)
		{
			if(this.isLeaf())
			{
				int i = k;
				while(i>0)
				{
					System.out.print("-");
					i=i-1;
				}
				System.out.print(" ");
				System.out.println(this.produit.toString()+ " " + reste.toString());	
				
				
			}
			else
			{
				int i = k;
				while(i>0)
				{
					System.out.print("-");
					i=i-1;
				}
				System.out.print(" ");
				System.out.println(this.produit.toString());	
				
				this.fils_gauche.print(k+1);
				this.fils_droit.print(k+1);
			}
		}
	}
	
	void print() //Affiche l'arbre
	{
		this.print(0);
	}
	
	void completeArbreReste()
	//Calcule les restes de l'arbre
	{
		reste=produit.mod(produit.multiply(produit));
		if (!(fils_droit==null)){
			fils_droit.completeArbreReste(reste);
			}
			if (!(fils_gauche==null)){
			fils_gauche.completeArbreReste(reste);
			}
		
	}
	private void completeArbreReste(BigInteger m)
	//Calcule les restes d'un sous arbre (modulo m)
	//N'est appelé que par completeAbreReste()
	{          
		reste=m.mod(produit.multiply(produit));
		if (!(fils_droit==null)){
		fils_droit.completeArbreReste(reste);
		}
		if (!(fils_gauche==null)){
		fils_gauche.completeArbreReste(reste);
		}
		
	}
	
	private static Arbre construitArbreProduit(List<BigInteger> liste, int a, int b)
	//Construit l'arbre des produits des restes indexés de a à b dans la liste
	//N'est appelé que par construitAtbreProduit(List<BigInteger>)
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
	//Construit l'arbre des produits de toute la liste
	{
		int n = liste.size();
		return construitArbreProduit(liste, 0, n-1);//start à 0 ou 1 ?
	}
	
	public Arbre construitArbreVide(int n)
	//Construit arbre vide à n feuilles
	{
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
	
	private void recupereFacteursPremiers(List<DoubleInt> L)
	//Explore l'arbre en profondeur et ajoute à L les pgcd non triviaux des feuilles
	//Appelée par recupereFacteursPremiers()
	{
		
		if(this.isLeaf())
		{
			BigInteger facteur=Main.gcd(reste.divide(produit),produit);
			
			if(facteur.equals(BigInteger.ONE)==false)
			{
			L.add( new DoubleInt(facteur, produit));
			// Tu stockes le facteur ainsi que la clé ?
			}
		}
		else 
		{
			if(!(fils_droit==null)) 
			{
				fils_droit.recupereFacteursPremiers(L);
			}
			if(!(fils_gauche==null)) 
			{
				fils_gauche.recupereFacteursPremiers(L);
			}
		}		
	}
	
	public List<DoubleInt> recupereFacteursPremiers() 
	//Explore en profondeur et renvoie une liste des pgcd non triviaux des feuilles
	{
		List<DoubleInt> ans=new LinkedList<DoubleInt>();
		recupereFacteursPremiers(ans);
		return ans;
		}
		
	}

