package model.common;

import model.expressions.ConstExpression;
import model.expressions.I_ConditionalExpression;
import model.expressions.I_Expression;
import model.expressions.IntegerAddExpression;
import model.expressions.IntegerModuleExpression;
import model.expressions.IntegerSubExpression;
import model.expressions.VarExpression;
import model.expressions.condition.DifferentThenCondition;
import model.expressions.condition.EqualThenCondition;
import model.expressions.condition.GreatherThenCondition;
import model.expressions.condition.LessThenCondition;
import model.statements.AssignmentStatement;
import model.statements.Block;
import model.statements.FunctionVoid;
import model.statements.I_Statement;
import model.statements.IfStatement;
import model.statements.IntegerStatement;
import model.statements.WhileStatement;

public class Language {

	public IntegerStatement INTEGER(String _name) {
		return new IntegerStatement(_name);
	}

	// public void INTEGER(String _name, Integer _init_value) {
	// VarExpression<Integer> var = new VarExpression<Integer>();
	// m_block.add(ATTRIB(var, new ConstExpression<Integer>(_init_value)));
	// m_vars.put(_name, var);
	// }

	public <T> ConstExpression<T> CONST(T _value) {
		return new ConstExpression<T>(_value);
	}

	public VarExpression<Integer> VAR(String _name) {
		return new VarExpression<Integer>(_name);
	}

	public <T> AssignmentStatement<T> ASSIGN(VarExpression<T> _left,
			I_Expression<T> _right) {
		return new AssignmentStatement<T>(_left, _right);
	}

	// public <T> void ASSIGN(VarExpression<T> _left, I_Expression<T> _right) {
	// m_block.add(new AssignmentStatement<T>(_left, _right));
	// }

	// public <T> void ASSIGN(String _left, String _right) {
	// m_block.add(getAssignement(_left, _right));
	// }
	//
	// public <T> void ASSIGN(String _left, I_Expression<T> _right) {
	// m_block.add(getAssignement(_left, _right));
	// }

	// public <T> AssignmentStatement<T> getAssignement(VarExpression<T> _left,
	// I_Expression<T> _right) {
	// return new AssignmentStatement<T>(_left, _right);
	// }
	//
	// public <T> AssignmentStatement<T> getAssignement(String _left, String
	// _right) {
	// return new AssignmentStatement<T>(m_vars.get(_left), m_vars.get(_right));
	// }
	//
	// public <T> AssignmentStatement<T> getAssignement(String _left,
	// I_Expression<T> _right) {
	// return new AssignmentStatement<T>(m_vars.get(_left), _right);
	// }

	public I_Expression<Integer> ADD(I_Expression<Integer> _first,
			I_Expression<Integer> _second) {
		return new IntegerAddExpression(_first, _second);
	}

	// public I_Expression<Integer> ADD(I_Expression<Integer> _first,
	// String _second) {
	// VarExpression<Integer> var_result = new VarExpression<Integer>();
	// IntegerAddExpression add = new IntegerAddExpression(_first,
	// m_vars.get(_second));
	// ASSIGN(var_result, add);
	//
	// return var_result;
	// }
	//
	// public I_Expression<Integer> ADD(String _first,
	// I_Expression<Integer> _second) {
	// VarExpression<Integer> var_result = new VarExpression<Integer>();
	// IntegerAddExpression add = new IntegerAddExpression(m_vars.get(_first),
	// _second);
	// ASSIGN(var_result, add);
	//
	// return var_result;
	// }
	//
	// public I_Expression<Integer> ADD(String _first, String _second) {
	// VarExpression<Integer> var_result = new VarExpression<Integer>();
	// IntegerAddExpression add = new IntegerAddExpression(m_vars.get(_first),
	// m_vars.get(_second));
	// ASSIGN(var_result, add);
	//
	// return var_result;
	// }

	public I_Expression<Integer> SUB(I_Expression<Integer> _first,
			I_Expression<Integer> _second) {
		return new IntegerSubExpression(_first, _second);
	}

	// public I_Expression<Integer> SUB(I_Expression<Integer> _first,
	// String _second) {
	// VarExpression<Integer> var_result = new VarExpression<Integer>();
	// IntegerSubExpression add = new IntegerSubExpression(_first,
	// m_vars.get(_second));
	// ASSIGN(var_result, add);
	//
	// return var_result;
	// }
	//
	// public I_Expression<Integer> SUB(String _first,
	// I_Expression<Integer> _second) {
	// VarExpression<Integer> var_result = new VarExpression<Integer>();
	// IntegerSubExpression add = new IntegerSubExpression(m_vars.get(_first),
	// _second);
	// ASSIGN(var_result, add);
	//
	// return var_result;
	// }
	//
	// public I_Expression<Integer> SUB(String _first, String _second) {
	// VarExpression<Integer> var_result = new VarExpression<Integer>();
	// IntegerSubExpression add = new IntegerSubExpression(m_vars.get(_first),
	// m_vars.get(_second));
	// ASSIGN(var_result, add);
	//
	// return var_result;
	// }

	public IntegerModuleExpression MOD(I_Expression<Integer> _first,
			I_Expression<Integer> _second) {
		return new IntegerModuleExpression(_first, _second);
	}

	public IfStatement IF(I_ConditionalExpression _condition,
			I_Statement _statement_true, I_Statement _statement_false) {
		return new IfStatement(_condition, _statement_true, _statement_false);
	}

	public <T extends Comparable<T>> LessThenCondition<T> LT(
			I_Expression<T> _first, I_Expression<T> _second) {
		return new LessThenCondition<T>(_first, _second);
	}

	public <T extends Comparable<T>> GreatherThenCondition<T> GT(
			I_Expression<T> _first, I_Expression<T> _second) {
		return new GreatherThenCondition<T>(_first, _second);
	}

	public <T extends Comparable<T>> EqualThenCondition<T> EQ(
			I_Expression<T> _first, I_Expression<T> _second) {
		return new EqualThenCondition<T>(_first, _second);
	}

	public <T extends Comparable<T>> DifferentThenCondition<T> DT(
			I_Expression<T> _first, I_Expression<T> _second) {
		return new DifferentThenCondition<T>(_first, _second);
	}

	//
	// public <T extends Comparable<T>> LessThenCondition<T> minor(String
	// _first,
	// String _second) {
	// return new LessThenCondition<T>(m_vars.get(_first), m_vars.get(_second));
	// }

	public WhileStatement WHILE(I_ConditionalExpression _condition,
			I_Statement... _statements) {
		Block block = new Block();

		for (I_Statement s : _statements) {
			block.add(s);
		}

		return new WhileStatement(_condition, block);
	}

	public Block BLOCK(I_Statement... _statements) {
		Block block = new Block();

		for (I_Statement s : _statements) {
			block.add(s);
		}

		return block;
	}

	@SuppressWarnings("rawtypes")
	public I_Statement FUNCTION(I_Statement _statement,
			VarExpression... varExpressions) throws Exception {
		return new FunctionVoid(varExpressions, _statement);
	}
}
