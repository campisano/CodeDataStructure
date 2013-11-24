package model;

import model.common.Language;
import model.common.Scope;
import model.expressions.I_Expression;
import model.statements.I_Statement;
import model.statements.PrintStatement;

public class Program extends Language {

	public <T> PrintStatement<T> PRINT(I_Expression<T> _exp) {
		return new PrintStatement<T>(_exp);
	}

	public Scope PROGRAM(I_Statement... _statements) throws Exception {

		Scope scope = new Scope();

		for (I_Statement s : _statements) {
			s.execute(scope);
		}

		return scope;
	}
}
