import java.math.BigInteger;
import java.util.List;
import java.util.LinkedList;

public class Arbre {
	public BigInteger produit;
	public BigInteger reste;
	public Arbre fils_gauche;
	public Arbre fils_droit;
	public int cle;

	
//**********************	FONCTIONS USUELLES	
	
	
	public Arbre(int n) 
	// Nouvel arbre vide
	{
		produit = new BigInteger("0");
		reste = new BigInteger("0");
		fils_gauche = null;
		fils_droit = null;
		cle = n;
	}
	
	public Arbre(Arbre fg, Arbre fd) 
	// Nouvel arbre a partir des deux fils
	{
		produit = new BigInteger("0");
		reste = new BigInteger("0");
		fils_gauche = fg;
		fils_droit = fd;
		cle = 0;
	}
	
	boolean isLeaf() 
	// Est-ce une feuille ?
	{
		return ((this.fils_droit==null)&&(this.fils_gauche==null));
	}
	
	void print() 
	// Affiche l'arbre (a l'aide de la fonction auxilliaire ci-dessous)
	{
		this.print(0);
	}
	
	void print(int k)
	// Fonction auxilliaire
	// Appelee par la precedente
	// Gere l'indentation des - pour l'affichage de l'arbre
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
				System.out.println(this.produit.toString());			
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
	
	
//**********************	PRODUITS

		
	BigInteger produitFils()
	// Calcule le produit des deux valeurs 'produit' des fils (selon ce qui existe)
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
	
	void remplaceProduitFils() 
	// Remplace la valeur de 'produit' par le produit des 'produits' des fils
	{
		this.produit = this.produitFils();
	}
	
	public static Arbre construitArbreProduit(List<BigInteger> liste)
	//Construit l'arbre des produits de toute la liste
	{
		int n = liste.size();
		return construitArbreProduit(liste, 0, n-1);
	}
	
	private static Arbre construitArbreProduit(List<BigInteger> liste, int a, int b)
	//Fonction auxilliaire
	//Construit l'arbre des produits des restes indexes de a a b dans la liste
	//N'est appele que par construitAtbreProduit(List<BigInteger>)
	{
		Arbre mon_arbre;
		if(a==b)
		{
			mon_arbre = new Arbre(a+1);
			mon_arbre.produit = liste.get(a);
		}
		else
		{
			int m = (b+a)/2;
			Arbre mon_arbre_gauche = construitArbreProduit(liste, a, m);
			Arbre mon_arbre_droit = construitArbreProduit(liste, m+1, b);
			mon_arbre = new Arbre(mon_arbre_gauche, mon_arbre_droit);
			mon_arbre.remplaceProduitFils();
		}

		return mon_arbre;
	}
	
	
//**********************	RESTES	

	
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
	//Fonction auxilliaire
	//Calcule les restes d'un sous arbre (modulo m)
	//N'est appele que par completeAbreReste()
	{          
		reste=m.mod(produit.multiply(produit));
		if (!(fils_droit==null)){
		fils_droit.completeArbreReste(reste);
		}
		if (!(fils_gauche==null)){
		fils_gauche.completeArbreReste(reste);
		}	
	}
	

//**********************	RECUPERATION DES PGCD	

	
	
	private void recupereFacteursPremiers(List<DecomposeBigInt> L)
	//Explore l'arbre en profondeur et ajoute a L les pgcd non triviaux des feuilles
	//Appele par recupereFacteursPremiers()
	{
		
		if(this.isLeaf())
		{
			BigInteger facteur=Main.gcd(reste.divide(produit),produit);
			
			if(facteur.equals(BigInteger.ONE)==false)
			{
				L.add( new DecomposeBigInt(cle, produit, facteur));
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
	
	public List<DecomposeBigInt> recupereFacteursPremiers() 
	//Explore en profondeur et renvoie une liste des pgcd non triviaux des feuilles
	{
		List<DecomposeBigInt> ans = new LinkedList<DecomposeBigInt>();
		recupereFacteursPremiers(ans);
		return ans;
	}

	public void afficheFacteursPremiers()
	{
		List<DecomposeBigInt> list = this.recupereFacteursPremiers();
		for(DecomposeBigInt e : list)
		{
			System.out.println(e.toString());
		}
	}
	
}
