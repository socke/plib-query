package de.feu.plib.webservice;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style=Style.RPC)
public class StringReturner {
	public String getMyString(String stringToAdd) {
		return stringToAdd + " lautet der String";
	}
}
