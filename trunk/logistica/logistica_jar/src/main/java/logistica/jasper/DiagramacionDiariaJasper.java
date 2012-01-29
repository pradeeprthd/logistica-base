package logistica.jasper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class DiagramacionDiariaJasper {

	public static Collection<DiagramacionDiariaReport> getList() {
		List<DiagramacionDiariaReport> list = new ArrayList<DiagramacionDiariaReport>();

		list.add(new DiagramacionDiariaReport(new Date(),
				"novedad 1 /n novedad 2 /nnovedad 3", 1l, "Sucursal coto 1",
				"1-ACR234", "", new Date(), null, null, "", "122"));
		list.add(new DiagramacionDiariaReport(new Date(),
				"novedad 1 /n novedad 2 /nnovedad 3", 1l, "Sucursal coto 1",
				null, "Flete 1", new Date(), new Date(), new Date(), "Agencia 1", "122"));
		return list;
	}
}
