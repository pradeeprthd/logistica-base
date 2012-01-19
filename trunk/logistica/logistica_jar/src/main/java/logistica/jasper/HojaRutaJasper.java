package logistica.jasper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class HojaRutaJasper {

	public static Collection<HojaRutaReport> getList() {
		List<HojaRutaReport> list = new ArrayList<HojaRutaReport>();

		list.add(new HojaRutaReport("1-3456456", new Date(), "Cliente 1",
				"23-ACR567", "Miguel angel", "2346886533", "Rivadavia 234",
				"Ciudad Autonoma Buenos Aires", 20, 200, 0, "Carga Pesada",
				"Calchaqui 234", "QUILMES OESTE", "BULTOS", 20, new Date(),
				new Date()));
		list.add(new HojaRutaReport("1-3456456", new Date(), "Cliente 1",
				"23-ACR567", "Miguel angel", "2346886533", "Rivadavia 234",
				"Ciudad Autonoma Buenos Aires", 20, 200, 0, "Carga Pesada",
				"12 de Octubre 44", "QUILMES OESTE", "KILOGRAMOS", 200,
				new Date(), new Date()));
		return list;
	}
}
