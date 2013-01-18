package logistica.jasper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ResumenDetalladoJasper {

	public static Collection<ResumenDetalladoReport> getList() {

		List<ResumenDetalladoReport> list = new ArrayList<ResumenDetalladoReport>();

		list.add(new ResumenDetalladoReport("Envios a domicilio",
				new ArrayList<ResumenDetalladoDetalleReport>()));
		list.add(new ResumenDetalladoReport("CORREO",
				new ArrayList<ResumenDetalladoDetalleReport>()));

		return list;
	}
}
