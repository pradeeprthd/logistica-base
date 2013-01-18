package logistica.jasper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MovilJasper {

	public static Collection<MovilReport> getList() {
		List<MovilReport> list = new ArrayList<MovilReport>();

		list.add(new MovilReport());
		list.add(new MovilReport());
		return list;
	}
}
