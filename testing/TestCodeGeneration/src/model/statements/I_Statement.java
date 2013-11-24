package model.statements;

import model.common.Scope;

public interface I_Statement {
	public void execute(Scope _scope) throws Exception;
}
