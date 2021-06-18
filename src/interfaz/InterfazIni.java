package interfaz;

import java.util.Scanner;
import mundo.Huffman;

public class InterfazIni {

	/**
	 * @author Diego Adrián Meneses Romero
	 * @version 1.0
	 */

	public static void main(String[] args) {

		Scanner lectorScanner = new Scanner(System.in);

		System.out.println("Ingrese el Texto a Comprimir");
		String texto = lectorScanner.nextLine();

		Huffman huffman = new Huffman(texto);
		huffman.preOrder(huffman.getRoot());
		System.out.println();

		String comprimido = huffman.comprimir(texto);
		System.out.println(comprimido);
		System.out.println(huffman.descomprimir(huffman.getRoot(), comprimido));

	}

}
