package simple_model;

import visitor.NodeVisitor;

public class VisitableNode {
	protected NodeVisitor visitor;

	public void assign(NodeVisitor visitor) {
		this.visitor = visitor;
	}
}
