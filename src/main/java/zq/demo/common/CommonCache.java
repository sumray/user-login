package zq.demo.common;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class CommonCache {

	private ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();
	
	
	public void cache(String key, Object value) {
		cache.put(key, value);
	}
	
	public Object get(String key) {
		return cache.get(key);
	}
	
	public void delete(String key) {
		cache.remove(key);
	}
}
