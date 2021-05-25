package tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.WrongClientCreateException;
import models.TipoDocumento;

public class Testing {
	
	//Verificacion de documentacion y email
    public static void validarDocumento(TipoDocumento tipoDocumentacion, String documentacion) throws WrongClientCreateException {
		if (tipoDocumentacion == TipoDocumento.DNI) {
			int caracterASCII = 0;
			if (documentacion.length() == 9 && Character.isLetter(documentacion.charAt(8))) {
				for (int i = 0; i < documentacion.length() - 1; i++) {
					caracterASCII = documentacion.charAt(i);
					if (caracterASCII > 47 && caracterASCII < 58) {
						//Correcto
					} else {
						throw new WrongClientCreateException("El dni introducido es incorrecto");
					}
				}
			} else {
				throw new WrongClientCreateException("El dni introducido es incorrecto");
			}
		} else {
			int caracterASCII = 0;
			if (documentacion.length() == 9 && Character.isLetter(documentacion.charAt(8))
					&& Character.isLetter(documentacion.charAt(0))) {
				for (int i = 1; i < documentacion.length() - 1; i++) {
					caracterASCII = documentacion.charAt(i);
					if (caracterASCII > 47 && caracterASCII < 58) {
						//Correcto
					} else {
						throw new WrongClientCreateException("El nie introducido es incorrecto");
					}
				}
			} else {
				throw new WrongClientCreateException("El nie introducido es incorrecto");
			}
		}
	}
    
    public static void validarEmail(String email) throws WrongClientCreateException {
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(email);

		if (mather.find() == false) {
			throw new WrongClientCreateException("El email introducido no es correcto");
		}
	}
}
