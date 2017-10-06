package joern.java.spring.two.client.model;

import java.util.LinkedHashMap;

public class AuthTokenData {
	
	public static final String KEY_TOKEN_TYPE = "token_type";
	public static final String KEY_ACCESS_TOKEN = "access_token";
	public static final String KEY_REFRESH_TOKEN = "refresh_token";
	public static final String KEY_EXPIRES_IN = "expires_in";
	public static final String KEY_SCOPE = "scope";
	
	private LinkedHashMap<String, Object> data;
	
	public AuthTokenData() {
		data = new LinkedHashMap<>();
	}
	
	

	public String get(String key) {
		
		Object o = data.get(key);
		String s = o instanceof String ? (String)o : null;
		return s;
	}



	public void setData(LinkedHashMap<String, Object> data) {
		if(data != null) {
			this.data = data;
		}
	}



	public String toString() {
		return new StringBuilder()
				.append(this.getClass().getSimpleName()).append(" [")
				.append(KEY_TOKEN_TYPE).append("=").append(data.get(KEY_TOKEN_TYPE)).append(", ")
				.append(KEY_ACCESS_TOKEN).append("=").append(data.get(KEY_ACCESS_TOKEN)).append(", ")
				.append(KEY_REFRESH_TOKEN).append("=").append(data.get(KEY_REFRESH_TOKEN)).append(", ")
				.append(KEY_EXPIRES_IN).append("=").append(data.get(KEY_EXPIRES_IN)).append(", ")
				.append(KEY_SCOPE).append("=").append(data.get(KEY_SCOPE)).append("]")
				.toString();
	}

}