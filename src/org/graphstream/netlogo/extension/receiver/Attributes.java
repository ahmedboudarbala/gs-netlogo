package org.graphstream.netlogo.extension.receiver;

import java.util.HashMap;
import java.util.Map;

import org.nlogo.core.LogoList;
import org.nlogo.api.LogoListBuilder;

/**
 * Helper class.
 * 
 * Stocks list of values for each attribute. Provides methods to add a value to
 * a list and to retrieve a list from attribute name. Handles the conversion
 * from NetStream types to NetLogo types.
 * 
 * @author Stefan Balev
 * 
 */
public class Attributes {
	Map<String, LogoListBuilder> map;

	public Attributes() {
		map = new HashMap<String, LogoListBuilder>();
	}

	public LogoList get(String attribute) {
		LogoListBuilder builder = map.remove(attribute);
		return builder == null ? LogoList.Empty() : builder.toLogoList();
	}

	public void add(String attribute, Object value) {
		Object logoValue = netStreamToLogo(value);
		if (logoValue == null)
			return;
		LogoListBuilder builder = map.get(attribute);
		if (builder == null) {
			builder = new LogoListBuilder();
			map.put(attribute, builder);
		}
		builder.add(logoValue);
	}

	protected static Object netStreamToLogo(Object o) {
		Object result = simpleNetStreamToLogo(o);
		if (result != null)
			return result;
		if (!o.getClass().isArray())
			return null;
		LogoListBuilder builder = new LogoListBuilder();
		for (Object element : (Object[]) o) {
			Object logoElement = simpleNetStreamToLogo(element);
			if (logoElement == null)
				return null;
			builder.add(logoElement);
		}
		return builder.toLogoList();
	}

	protected static Object simpleNetStreamToLogo(Object o) {
		if (o instanceof Boolean || o instanceof String || o instanceof Double)
			return o;
		if (o instanceof Number) {
			return new Double(((Number) o).doubleValue());
			
		}
		return null;
	}
}