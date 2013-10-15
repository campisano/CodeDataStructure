package model.expression;

public class ArrayItem extends Value {
	private String itemName;

	public ArrayItem(String arrName, String itemName) {
		super(arrName);
		this.itemName = itemName;
	}

	public String evaluate() {
		return super.evaluate() + "[" + itemName + "]";
	}
}
