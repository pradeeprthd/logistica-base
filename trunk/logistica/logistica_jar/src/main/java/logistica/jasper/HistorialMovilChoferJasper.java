package logistica.jasper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HistorialMovilChoferJasper {

	public static Collection<HistorialMovilChoferReport> getList() {
		List<HistorialMovilChoferReport> list = new ArrayList<HistorialMovilChoferReport>();

		list.add(new HistorialMovilChoferReport());
		list.add(new HistorialMovilChoferReport());
		return list;
	}
}
