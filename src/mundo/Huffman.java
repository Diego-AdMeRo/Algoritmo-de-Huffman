package mundo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Huffman {

	/**
	 * @author Diego Adrián Meneses Romero
	 * @version 1.0
	 */

	// Atributos
	private Node root;
	private String busqueda;

	// Constructor
	public Huffman(String texto) {
		this.root = arbol(tablaRepeOptimo(texto));
	}

	/**
	 * Método encargado de analizar el texto ingresado y realizar la tabla de
	 * repeticiones (Forma no Óptima)
	 * 
	 * @param texto
	 * @return repeticiones
	 */
	public ArrayList<Node> tablaRepe(String texto) {
		ArrayList<Node> repeticiones = new ArrayList<>();
		int j;
		boolean a;
		for (int i = 0; i < texto.length(); i++) {
			j = 0;
			a = false;
			while (a != true && j < repeticiones.size()) {
				if (texto.charAt(i) == repeticiones.get(j).getInfo().getLetter()) {
					repeticiones.get(j).getInfo().upTimes();
					a = true;
				}
				j++;
			}
			if (a == false)
				repeticiones.add(new Node(new Info(texto.charAt(i))));
		}
		ordenamiento(repeticiones);
		return repeticiones;
	}

	/**
	 * Método encargado de analizar el texto ingresado y realizar la tabla de
	 * repeticiones (Forma Óptima al usar diccionarios)
	 * 
	 * @param texto
	 * @return nodos
	 */
	public ArrayList<Node> tablaRepeOptimo(String texto) {
		HashMap<Character, Node> repeticiones = new HashMap<Character, Node>();
		for (int i = 0; i < texto.length(); i++) {
			if (repeticiones.containsKey(texto.charAt(i))) {
				Node aux = repeticiones.get(texto.charAt(i));
				aux.getInfo().upTimes();
				repeticiones.put(texto.charAt(i), aux);
			} else {
				repeticiones.put(texto.charAt(i), new Node(new Info(texto.charAt(i))));
			}
		}
		ArrayList<Node> nodos = new ArrayList<Node>(repeticiones.values());
		ordenamiento(nodos);
		return nodos;
	}

	/**
	 * Método enfocado a ordenar la tabla de caracteres respecto a las repeticiones
	 * ya sea después de su creación como también durante la elaboración del árbol
	 * binario
	 * 
	 * @param ordenar
	 */
	public void ordenamiento(ArrayList<Node> ordenar) {
		Collections.sort(ordenar, new Comparator<Node>() {
			@Override
			public int compare(Node p1, Node p2) {
				return Integer.valueOf(p1.getInfo().getTimes()).compareTo(Integer.valueOf(p2.getInfo().getTimes()));
			}
		});
	}

	/**
	 * El siguiente método tiene como propósito tomar la tabla de repeticiones he ir
	 * creando el árbol respectivo al texto ingresado
	 * 
	 * @param nodos (ArrayList)
	 * @return árbol (Node)
	 */

	private Node arbol(ArrayList<Node> nodos) {
		Node aux = new Node(new Info('\0'));
		aux.setLeft(nodos.get(0));
		aux.setRight(nodos.get(1));
		aux.getInfo().upTimes(nodos.get(0).getInfo().getTimes() + nodos.get(1).getInfo().getTimes());
		nodos.remove(0);
		nodos.remove(0);
		nodos.add(aux);
		ordenamiento(nodos);
		if (nodos.size() != 1)
			arbol(nodos);
		return nodos.get(0);
	}

	/**
	 * Metodo encargado de visualizar de manera lineal la estructura del arbol
	 * creado
	 * 
	 * @param root
	 */
	public void preOrder(Node root) {
		if (root != null) {
			String resultado = root.getInfo().getLetter() == '\0' ? "Null" : root.getInfo().getLetter() + "";
			System.out.print(resultado + "\t");
			if (root.getLeft() != null)
				preOrder(root.getLeft());
			if (root.getRight() != null)
				preOrder(root.getRight());
		}
	}

	/**
	 * Los siguientes métodos de comprimir tienen como objetivo a partir del árbol
	 * de recurrencias transformar el texto ingresado a 1's y 0's, es decir,
	 * simplificar su estructura a nivel informático
	 * 
	 * @param texto
	 * @return texto comprimido
	 */
	public String comprimir(String texto) {
		String comprimido = "";
		for (int i = 0; i < texto.length(); i++) {
			this.busqueda = "";
			comprimir(getRoot(), texto.charAt(i));
			comprimido += this.busqueda;
		}
		return comprimido;
	}

	private boolean comprimir(Node root, char letter) {
		if (root != null) {
			if (letter == root.getInfo().getLetter())
				return true;
			if (root.getLeft() != null) {
				if (comprimir(root.getLeft(), letter)) {
					this.busqueda = "0" + this.busqueda;
					return true;
				}
			}
			if (root.getRight() != null) {
				if (comprimir(root.getRight(), letter)) {
					this.busqueda = "1" + this.busqueda;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Método encargado de descomprimir los bits generados con anterioridad por el
	 * recorrido del árbol
	 * 
	 * @param root
	 * @param textoComprimido
	 * @return textoDescomprimido
	 */

	public String descomprimir(Node root, String textoComprimido) {
		Node auxNode = root;
		String textoDescomprimido = "";
		for (int i = 0; i < textoComprimido.length(); i++) {
			if (auxNode.getInfo().getLetter() == '\0') {
				if (textoComprimido.charAt(i) == '0')
					auxNode = auxNode.getLeft();
				else if (textoComprimido.charAt(i) == '1')
					auxNode = auxNode.getRight();
			}
			if (auxNode.getInfo().getLetter() != '\0') {
				textoDescomprimido += auxNode.getInfo().getLetter() + "";
				auxNode = root;
			}
		}
		return textoDescomprimido;
	}

	public Node getRoot() {
		return root;
	}

}
