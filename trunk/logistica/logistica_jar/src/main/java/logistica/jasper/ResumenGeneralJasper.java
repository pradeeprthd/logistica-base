package logistica.jasper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ResumenGeneralJasper {

	public static Collection<ResumenGeneralReport> getList() {
		List<ResumenGeneralReport> list = new ArrayList<ResumenGeneralReport>();

		list.add(new ResumenGeneralReport());
		list.add(new ResumenGeneralReport());
		return list;
	}
}
