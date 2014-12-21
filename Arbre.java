import java.math.BigInteger;

public class Arbre {
	public BigInteger produit;
	public BigInteger reste;
	public Arbre fils_gauche;
	public Arbre fils_droit;
	
	public Arbre() {
		produit = new BigInteger("0");
		reste = new BigInteger("0");
		fils_gauche = null;
		fils_droit = null;
	}
	
	public Arbre(Arbre fg, Arbre fd) {
		produit = new BigInteger("0");
		reste = new BigInteger("0");
		fils_gauche = fg;
		fils_droit = fd;
	}
	
	boolean isLeaf() {
		return ((this.fils_droit==null)&&(this.fils_gauche==null));
	}
		
	BigInteger produitFils() {
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
	
	void remplaceProduitFils() {
		this.produit = this.produitFils();
	}
	
	void print(int k)
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
	
	void print() 
	{
		this.print(0);
	}
	
}
