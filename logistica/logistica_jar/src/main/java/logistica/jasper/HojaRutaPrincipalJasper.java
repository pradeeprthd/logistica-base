package logistica.jasper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class HojaRutaPrincipalJasper {

	public static Collection<HojaRutaPrincipalReport> getList(){
		
		List<HojaRutaPrincipalReport> list = new ArrayList<HojaRutaPrincipalReport>();
		
		list.add(new HojaRutaPrincipalReport("1", "sucursal 1", new Date(), "11-11111333", "cliente 1", "chofer 1", "1-ART345", "123456", "APROBADO"));
		list.add(new HojaRutaPrincipalReport("2", "sucursal 2", new Date(), "11-11111333", "cliente 2", "chofer 2", "1-ART342", "1222", "APROBADO"));
		
		return list;
	}
}
