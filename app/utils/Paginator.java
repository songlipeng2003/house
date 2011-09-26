package utils;

import java.util.HashMap;
import java.util.Map;

import play.mvc.Router;
import play.utils.Utils.Maps;

public class Paginator extends ViewHelper {
	protected final int offset;
	protected final int limit;
	protected final int total;

	public Paginator(int offset, int limit, int total, String action,
			Object... params) {
		super(action, params);
		this.offset = offset;
		this.limit = limit;
		this.total = total;
		addParams(offset, limit, total);
	}

	public String getAction(int page) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.putAll(this.params);
		params.put("offset", page * limit);
		params.put("limit", limit);
		return Router.reverse(defaultAction, params).url;
	}
}
