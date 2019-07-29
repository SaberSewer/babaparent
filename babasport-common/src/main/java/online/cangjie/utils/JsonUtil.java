package online.cangjie.utils;



import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

public class JsonUtil {
	public static <T> JSONObject getJsonByList(List<T> list) {
		JSONObject json = new JSONObject();
		Iterator<T> iterator = list.iterator();
		for (int j = 0; iterator.hasNext(); j++) {
			json.put("obj" + j, getJsonByObject(iterator.next()).toString());
		}
		return json;
	}

	public static <T> JSONObject getJsonByObject(Object object) {
		JSONObject json = new JSONObject();
		Method[] method = object.getClass().getMethods();
		for (int i = 0; i < method.length; i++) {
			if (method[i].getName().equals("getClass")) {
				continue;
			}
			if (method[i].getName().startsWith("get")) {
				try {
					json.put(method[i].getName().substring(3).replaceFirst(method[i].getName().substring(3, 4),  method[i].getName().substring(3, 4).toLowerCase()), method[i].invoke(object));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		}
		return json;
	}

}
