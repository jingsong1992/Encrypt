package ce_user_tools;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheMang {
	/*
	 * get object by cache
	 * */
	public static Object getObjectFromCache(String cacheName,String key,CacheManager cacheManager){		
		Cache cache = cacheManager.getCache(cacheName);
		if(cache == null){
			return null;
		}
		Element element = cache.get(key);
		if(element == null){
			return null;
		}
		return element.getObjectValue();
	}
	
	
	/*
	 * add object to cache if cache no this object
	 * */
	public static void pushObjectTocache(String cacheName,String key,Object obj,CacheManager cacheManager){
		Cache cache = cacheManager.getCache(cacheName);
		if(cache == null){
			return;
		}
		Element element = new Element(key,obj);
		cache.put(element);
	}
}
