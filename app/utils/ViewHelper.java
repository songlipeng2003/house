package utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import play.classloading.enhancers.LocalvariablesNamesEnhancer.LocalVariablesNamesTracer;
import play.mvc.Router;

public class ViewHelper {
	protected final Map<String, Object> params;
	protected final String defaultAction;

	public ViewHelper(String defaultAction, Object... params) {
		this.params = new HashMap<String, Object>();
		this.defaultAction = defaultAction;
		addParams(params);
	}

	public Object get(String key) {
		return params.get(key);
	}

	public void addParams(Object... params) {
		for (Object o : params) {
			List<String> names = LocalVariablesNamesTracer
					.getAllLocalVariableNames(o);
			for (String name : names) {
				this.params.put(name, o);
			}
		}
	}

	public String getDefaultAction(LinkedHashMap<String, Object> params) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.putAll(this.params);
		paramMap.putAll(params);
		return Router.reverse(defaultAction, paramMap).url;
	}
}
