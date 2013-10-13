package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Block implements I_Statement {
	public List<I_Statement> m_statements;

	public Block() {
		m_statements = new ArrayList<I_Statement>();
	}

	public void execute() {
		Iterator<I_Statement> iterator = m_statements.iterator();

		while (iterator.hasNext()) {
			iterator.next().execute();
		}
	}

	public void add(I_Statement _statement) {
		m_statements.add(_statement);
	}
}
