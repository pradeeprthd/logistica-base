package logistica.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FilesystemUtil {
	/**
	 */
	public static void escribirArchivo(String directorio, String nombreArchivo,
			byte[] data) throws Exception {
		File file = new File(directorio, nombreArchivo);
		try {
			FileUtils.writeByteArrayToFile(file, data);
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 */
	public static void borrarArchivo(String directorio, String nombreArchivo)
			throws Exception {
		File file = new File(directorio, nombreArchivo);

		try {
			FileUtils.forceDelete(file);
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 */
	public static byte[] obtenerArchivo(String directorio, String nombreArchivo)
			throws Exception {
		File file = new File(directorio, nombreArchivo);

		try {
			return FileUtils.readFileToByteArray(file);
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 */
	public static long obtenerTamanioArchivoBytes(String directorio,
			String nombreArchivo) throws Exception {
		File file = new File(directorio, nombreArchivo);

		return FileUtils.sizeOf(file);
	}
}
