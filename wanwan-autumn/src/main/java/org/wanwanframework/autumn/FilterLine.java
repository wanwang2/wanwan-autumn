package org.wanwanframework.autumn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterLine {

	public List<String> fields = new ArrayList<>();
	public String[] positions;
	@Override
	public String toString() {
		return "FilterLine [fields=" + fields + ", positions=" + Arrays.toString(positions) + "]";
	}
	
}
