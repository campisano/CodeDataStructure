package model.expressions;

import model.common.Scope;

public interface I_Expression<T> {
	T evaluate(Scope _scope) throws Exception;
}
