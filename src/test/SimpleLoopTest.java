package test;

public class SimpleLoopTest {
	
	public static void PROGRAM(String... statements)
	{
		for(String s: statements)
		{
			System.out.println(s);
		}
	}
	
	public static String INTVAR(String name)
	{
		return "int " + name + ";";
	}
	
	public static String WHILE(String condition, String... statements)
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("while (")
			.append(condition)
			.append(") {\n")
		;
			
		for(String s: statements)
		{
			sb.append(s)
				.append("\n")
			;
		}
		
		return sb.append("}").toString();
	}
	
	public static String LT( String e1, String e2 )
	{
		return e1 + "<" + e2;
	}
	
	public static String ADD( String e1, String e2 )
	{
		return e1 + "+" + e2;
	}
	
	public static String VALUE( String varName )
	{
		return varName;
	}
	
	public static String ATRIB(String varName, String expression )
	{
		return varName + " = " + expression + ";";
	}
	
	public static String PRINT(String expression)
	{
		return "cout << " + expression + ";";
	}
	
	public static String CONST(int value)
	{
		return String.valueOf(value);
	}
	
	public static void main(String[] args) {
	
		
		PROGRAM
		(
				INTVAR("I"),
				INTVAR("J"),
				WHILE (LT(VALUE("I"),CONST(10)),
						ATRIB("J",ADD("J",CONST(10))),
						ATRIB("I",ADD("I",CONST(1)))
				),
				PRINT(VALUE("I")),
				PRINT(VALUE("J"))
		);
		
		// output
		// int I;
		// int J;
		// while (I<10) {
		// J = J+10;
		// I = I+1;
		// }
		// cout << I;
		// cout << J;
	}
	
	

}