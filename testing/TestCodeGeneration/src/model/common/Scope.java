package model.common;

import java.util.TreeMap;

public class Scope {

	private Scope m_parent;
	private TreeMap<String, Object> m_vars;

	public Scope getParent() {
		return m_parent;
	}

	public void setParent(Scope _parent) {
		m_parent = _parent;
	}

	public Scope() {
		m_parent = null;
		m_vars = new TreeMap<String, Object>();
	}

	public <T> void declare(String _name, T _var) throws Exception {
		if (m_vars.containsKey(_name)) {
			throw new Exception("redeclaring var " + _name);
		}
		m_vars.put(_name, _var);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String _name) {
		if (m_vars.containsKey(_name)) {
			return (T) m_vars.get(_name);
		} else {
			return m_parent.get(_name);
		}
	}

	public <T> void assign(String _name, T _var) {
		if (m_vars.containsKey(_name)) {
			m_vars.put(_name, _var);
		} else {
			m_parent.assign(_name, _var);
		}
	}
}
