package logistica.jasper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ResumenDetalladoDetalleJasper {

	public static Collection<ResumenDetalladoDetalleReport> getList() {

		List<ResumenDetalladoDetalleReport> list = new ArrayList<ResumenDetalladoDetalleReport>();

		list.add(new ResumenDetalladoDetalleReport());
		list.add(new ResumenDetalladoDetalleReport());

		return list;
	}
}
