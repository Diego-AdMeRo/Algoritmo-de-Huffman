package mundo;

public class Node {

	/**
	 * @author Diego Adrián Meneses Romero
	 * @version 1.0
	 */

	/* Atributos */
	private Node left, right;
	private Info info;

	/* Constructor */
	public Node(Info info) {
		this.right = this.left = null;
		this.info = info;
	}

	/* Metodos setters & getters */
	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

	public Info getInfo() {
		return info;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

}