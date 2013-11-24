package model.statements;

import model.common.Scope;

import model.common.Language;
import model.expressions.VarExpression;
import model.statements.I_Statement;

public class FunctionVoid extends Language implements I_Statement {
	@SuppressWarnings("rawtypes")
	protected VarExpression[] m_varExpressions;
	protected I_Statement m_statement;

	@SuppressWarnings("rawtypes")
	public FunctionVoid(VarExpression[] varExpressions, I_Statement _statement) {
		m_varExpressions = varExpressions;
		m_statement = _statement;
	}

	@SuppressWarnings("rawtypes")
	public void execute(Scope _scope) throws Exception {
		Scope scope = new Scope();

		for (VarExpression var : m_varExpressions) {
			scope.declare(var.getName(), var.evaluate(_scope));
		}

		scope.setParent(_scope);
		m_statement.execute(scope);
	}
}
