package model.statements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.common.Scope;

import model.common.Language;

public class Block extends Language implements I_Statement {
	private Scope m_scope;
	private List<I_Statement> m_statements;

	public Block() {
		m_scope = new Scope();
		m_statements = new ArrayList<I_Statement>();
	}

	public void execute(Scope _scope) throws Exception {
		m_scope.setParent(_scope);
		Iterator<I_Statement> iterator = m_statements.iterator();

		while (iterator.hasNext()) {
			iterator.next().execute(m_scope);
		}

		m_scope.setParent(null);
	}

	public void add(I_Statement _statement) {
		m_statements.add(_statement);
	}

	public Scope getScope() {
		return m_scope;
	}
}
