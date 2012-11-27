package logistica.util;

import java.util.Arrays;

public class PracticaEntrevista {

	public static int getValorBinario(int[] array, int valor) {

		Arrays.sort(array);
		int pos = 0;
		int ini = 0;
		int fin = array.length - 1;
		while (ini <= fin) {
			pos = (ini + fin) / 2;
			if (array[pos] == valor) {
				return pos;
			} else if (array[pos] < valor) {
				ini = pos + 1;
			} else {
				fin = pos - 1;
			}
		}

		return -1;
	}

	public static boolean contieneCadena(String cadena, String aBuscar) {
		boolean ret = false;

		int longitud = aBuscar.length();
		int pos = 0;

		while (pos + longitud != cadena.length()) {
			String aux = cadena.substring(pos, pos + longitud);
			if (aux.equalsIgnoreCase(aBuscar)) {
				ret = true;
				break;
			}
			pos++;
		}

		return ret;
	}
	
	public static boolean isCapicua(String cadena){
		boolean ret = false;
		
		
		return ret;
	}

	public static void main(String[] args) {
		/*System.out.println(PracticaEntrevista.getValorBinario(new int[] { 1,
				22, 5, 7, 33, 34, 2 }, 44));

		System.out.println(contieneCadena("eeeepepejgjgj", "jgjgg"));*/
		
		String key = "entity.nombre";
		String[] temp = key.split("\\.");
		if(temp.length > 1){
			String first = temp[0];
			System.out.println("el primero: " + first);
		}
	}
	
	
}
