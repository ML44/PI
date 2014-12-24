import java.math.BigInteger;

public class DoubleInt {
	BigInteger premier;
	BigInteger deuxieme;
	
	DoubleInt(BigInteger a,BigInteger b){
		premier=a;
		deuxieme=b;
		
	}
	
	public String toString(){
		return ("("+premier.toString()+","+deuxieme.toString()+")");
	}
}


