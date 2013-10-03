package model;

import model.statements.*;
import model.expressions.*;
import model.expressions.condition.*;

public class EuclideanAlgorithmFunction extends A_Function<Integer[], Integer> {
	public EuclideanAlgorithmFunction(Integer _dividend, Integer _divisor) {
		super(new Integer[2], new Block());
		m_parameters[0] = _dividend;
		m_parameters[1] = _divisor;
		m_result = new VarExpression<Integer>();

		Block b_main = (Block) m_statement;
		{
			VarExpression<Integer> var_divisor = new VarExpression<Integer>();
			VarExpression<Integer> var_dividendo = new VarExpression<Integer>();

			Block b_true = new Block();
			Block b_false = new Block();

			// se b > a
			b_main.add(new IfStatement(new GreatherThenCondition<Integer>(
					new ConstExpression<Integer>(m_parameters[0]),
					new ConstExpression<Integer>(m_parameters[1])), b_true,
					b_false));

			// então dividendo = b divisor = a
			b_true.add(new AssignmentStatement<Integer>(var_dividendo,
					new ConstExpression<Integer>(m_parameters[1])));
			b_true.add(new AssignmentStatement<Integer>(var_divisor,
					new ConstExpression<Integer>(m_parameters[0])));

			// senão dividendo = a divisor = b
			b_false.add(new AssignmentStatement<Integer>(var_dividendo,
					new ConstExpression<Integer>(m_parameters[0])));
			b_false.add(new AssignmentStatement<Integer>(var_divisor,
					new ConstExpression<Integer>(m_parameters[1])));

			Block b_loop = new Block();

			// enquanto resto(dividendo/divisor) != 0
			b_main.add(new WhileStatement(new DifferentThenCondition<Integer>(
					new IntegerModuleExpression(var_dividendo, var_divisor),
					new ConstExpression<Integer>(0)), b_loop));

			{
				// c = resto(dividendo/divisor)
				VarExpression<Integer> var_c = new VarExpression<Integer>();
				b_loop.add(new AssignmentStatement<Integer>(var_c,
						new IntegerModuleExpression(var_dividendo, var_divisor)));

				// dividendo = divisor
				b_loop.add(new AssignmentStatement<Integer>(var_dividendo,
						var_divisor));

				// divisor = c
				b_loop.add(new AssignmentStatement<Integer>(var_divisor, var_c));
			}

			// AlgoritmoDeEuclides = divisor
			b_main.add(new AssignmentStatement<Integer>(m_result, var_divisor));
		}
	}

	public Integer evaluate() {
		m_statement.execute();

		return m_result.evaluate();
	}
}
