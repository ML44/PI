import java.math.BigInteger;

public class DecomposeBigInt {
	int cle;
	BigInteger produit;
	BigInteger facteur1;
	BigInteger facteur2;
	
	DecomposeBigInt(int n, BigInteger prod, BigInteger fac)
	{
		cle = n;
		produit=prod;
		facteur1=fac;
		facteur2=prod.divide(fac);
	}
	
	public String toString(){
		return ("-----\nKEY"+cle+"\n-----\n" + produit.toString() + "\n=\n" + facteur1.toString() + "\n*\n" + facteur2.toString() + "\n-----\n");
	}
}