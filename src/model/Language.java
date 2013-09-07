package model;

import java.util.TreeMap;

import model.statements.*;
import model.expressions.*;
import model.expressions.condition.*;

public class Language {

	private TreeMap<String, VarExpression> m_vars;
	private Block m_block;

	public Language() {
		m_vars = new TreeMap<String, VarExpression>();
		m_block = new Block();
	}

	public void integer(String _name) {
		m_vars.put(_name, new VarExpression<Integer>());
	}

	public void integer(String _name, Integer _init_value) {
		VarExpression<Integer> var = new VarExpression<Integer>();
		assign(var, new ConstExpression<Integer>(_init_value));
		m_vars.put(_name, var);
	}

	public VarExpression<Integer> var(String _name) {
		return (VarExpression<Integer>) m_vars.get(_name);
	}

	public <T> void assign(VarExpression<T> _left, I_Expression<T> _right) {
		m_block.add(getAssignement(_left, _right));
	}

	public <T> void assign(String _left, String _right) {
		m_block.add(getAssignement(_left, _right));
	}

	public <T> void assign(String _left, I_Expression<T> _right) {
		m_block.add(getAssignement(_left, _right));
	}

	public <T> AssignmentStatement<T> getAssignement(VarExpression<T> _left,
			I_Expression<T> _right) {
		return new AssignmentStatement<T>(_left, _right);
	}

	public <T> AssignmentStatement<T> getAssignement(String _left, String _right) {
		return new AssignmentStatement<T>(m_vars.get(_left), m_vars.get(_right));
	}

	public <T> AssignmentStatement<T> getAssignement(String _left,
			I_Expression<T> _right) {
		return new AssignmentStatement<T>(m_vars.get(_left), _right);
	}

	public I_Expression<Integer> add(I_Expression<Integer> _first,
			I_Expression<Integer> _second) {
		VarExpression<Integer> var_result = new VarExpression<Integer>();
		IntegerAddExpression add = new IntegerAddExpression(_first, _second);
		assign(var_result, add);

		return var_result;
	}

	public I_Expression<Integer> add(I_Expression<Integer> _first,
			String _second) {
		VarExpression<Integer> var_result = new VarExpression<Integer>();
		IntegerAddExpression add = new IntegerAddExpression(_first,
				m_vars.get(_second));
		assign(var_result, add);

		return var_result;
	}

	public I_Expression<Integer> add(String _first,
			I_Expression<Integer> _second) {
		VarExpression<Integer> var_result = new VarExpression<Integer>();
		IntegerAddExpression add = new IntegerAddExpression(m_vars.get(_first),
				_second);
		assign(var_result, add);

		return var_result;
	}

	public I_Expression<Integer> add(String _first, String _second) {
		VarExpression<Integer> var_result = new VarExpression<Integer>();
		IntegerAddExpression add = new IntegerAddExpression(m_vars.get(_first),
				m_vars.get(_second));
		assign(var_result, add);

		return var_result;
	}

	public I_Expression<Integer> sub(I_Expression<Integer> _first,
			I_Expression<Integer> _second) {
		VarExpression<Integer> var_result = new VarExpression<Integer>();
		IntegerSubExpression add = new IntegerSubExpression(_first, _second);
		assign(var_result, add);

		return var_result;
	}

	public I_Expression<Integer> sub(I_Expression<Integer> _first,
			String _second) {
		VarExpression<Integer> var_result = new VarExpression<Integer>();
		IntegerSubExpression add = new IntegerSubExpression(_first,
				m_vars.get(_second));
		assign(var_result, add);

		return var_result;
	}

	public I_Expression<Integer> sub(String _first,
			I_Expression<Integer> _second) {
		VarExpression<Integer> var_result = new VarExpression<Integer>();
		IntegerSubExpression add = new IntegerSubExpression(m_vars.get(_first),
				_second);
		assign(var_result, add);

		return var_result;
	}

	public I_Expression<Integer> sub(String _first, String _second) {
		VarExpression<Integer> var_result = new VarExpression<Integer>();
		IntegerSubExpression add = new IntegerSubExpression(m_vars.get(_first),
				m_vars.get(_second));
		assign(var_result, add);

		return var_result;
	}

	public void ifcond(I_ConditionalExpression _condition,
			I_Statement _statement_true, I_Statement _statement_false) {
		m_block.add(new IfStatement(_condition, _statement_true,
				_statement_false));
	}

	public <T extends Comparable<T>> LessThenCondition<T> minor(
			I_Expression<T> _first, I_Expression<T> _second) {
		return new LessThenCondition<T>(_first, _second);
	}

	public <T extends Comparable<T>> LessThenCondition<T> minor(String _first,
			String _second) {
		return new LessThenCondition<T>(m_vars.get(_first), m_vars.get(_second));
	}

	public void execute() {
		m_block.execute();
	}
}
