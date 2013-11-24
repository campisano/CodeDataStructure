package function;

import model.common.Language;
import model.common.Scope;
import model.statements.Block;

public class EuclideanAlgorithmFunction extends Language {
	
	Block m_block;
	
	public EuclideanAlgorithmFunction(int _a, int _b) {

		m_block = BLOCK(
				INTEGER("divisor"),
				INTEGER("dividend"),
				INTEGER("result"),
				IF(
						GT(VAR("divisor"), VAR("dividend")),	// se b > a
						BLOCK(
								// então dividendo = b divisor = a
								ASSIGN(VAR("dividend"), CONST(_b)),
								ASSIGN(VAR("divisor"), CONST(_a))
								),
						BLOCK(
								// senão dividendo = a divisor = b
								ASSIGN(VAR("dividend"), CONST(_a)),
								ASSIGN(VAR("divisor"), CONST(_b))
								)
						),
				INTEGER("c"),
				WHILE(
						DT(		// enquanto resto(dividendo/divisor) != 0
								MOD(VAR("dividend"), VAR("divisor")),
								CONST(0)
								),
						ASSIGN(	// c = resto(dividendo/divisor)
								VAR("c"),
								MOD(VAR("dividend"), VAR("divisor"))
								),
						ASSIGN(VAR("dividend"), VAR("divisor")),	// dividendo = divisor
						ASSIGN(VAR("divisor"), VAR("c"))			// divisor = c
						),
				ASSIGN(VAR("result"), VAR("c"))	// result = divisor
				);
	}

	public int evaluate() throws Exception {
		Scope scope = new Scope();
		m_block.execute(scope);
		
		return m_block.getScope().get("result");
	}
}
