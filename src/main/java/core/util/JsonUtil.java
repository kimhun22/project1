package core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import core.exception.HMException;

public class JsonUtil {

	/**
	 * Object to String
	 *
	 * @param obj
	 * @param pretty
	 * @return
	 */
	public static String serialize(Object obj, boolean pretty) {

		String result = "";
		GsonBuilder builder = new GsonBuilder().serializeNulls();
		Gson mapper = new Gson();

		try {
			if ( pretty ) {
				mapper = builder.setPrettyPrinting().create();
				result = mapper.toJson(obj);
			} else {
				result = mapper.toJson(obj);
			}
		} catch (Exception e) {
			throw new HMException(e.getMessage());
		}

		return result;

	}

}
