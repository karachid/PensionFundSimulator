package simulation.cdr.generator;

public class Generator {
	
	public static double alea (int IX, int IY, int IZ){
		double inter;
		IX=171*(IX%177)-2*(IX/177);
		IY=172*(IY%176)-35*(IY/176);
		IZ=170*(IZ%178)-63*(IZ/178);
		if(IX<0) IX=IX+30269;
		if(IY<0) IY=IY+30307;
		if(IZ<0) IZ=IZ+30323;
		inter=(double)IX/30269+(double)IY/30307+(double)IZ/30323;
		return inter-(int)inter;
	}
	
	public static void main(String [] args) {
		int IX=1500, IY=15263, IZ=22222;
		for(int i=0;i<100;i++) {
			System.out.println(Generator.alea(IX, IY, IZ));
			IX+=5;
			IY+=5;
			IZ+=5;
		}
	}
}
