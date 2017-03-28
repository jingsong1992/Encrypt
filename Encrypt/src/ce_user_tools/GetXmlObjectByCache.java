package ce_user_tools;

import java.io.File;

import net.sf.ehcache.CacheManager;

import org.w3c.dom.Document;
import ce_user_bean.CacheManagerObject;
import ce_user_bean.PasswordConfig;
import ce_user_parseXml.CustomParserXML;
import ce_user_parseXml.PasswordConfigParse;
import ce_user_tools.CacheMang;
import ce_user_tools.XMLmanager;

public class GetXmlObjectByCache {
	/*
	 *  get PasswordConfig object 
	 * */
	public static PasswordConfig getPasswordConfig(CacheManagerObject cacheManagerObject){
		String cacheName = "CACHE_AUTH";
		String fileName = "password_config.xml";
		
		String filePath = FilePathGet.getFilePath(fileName, GetXmlObjectByCache.class);
		File f = new File(filePath);
		if(!f.exists()){
			return  null;
		}
		CacheManager cacheManager = cacheManagerObject.getCacheManager();
		StringBuffer key = new StringBuffer();
		key.append(filePath).append("pojo");
		PasswordConfigParse  parse = new PasswordConfigParse();
		String[] cacheInfo = new String[]{cacheName,key.toString(),filePath};
		return (PasswordConfig) getObjectByParse(cacheInfo,parse,cacheManager);
		
	}
	
	/*
	 * get object by cache or parse xml
	 * */
	public static Object getObjectByParse(String[] cacheInfo,CustomParserXML parse,CacheManager cacheManager){
		String cacheName = cacheInfo[0];
		String key = cacheInfo[1];
		String filePath = cacheInfo[2];
		Object cacheValue = CacheMang.getObjectFromCache(cacheName, key,cacheManager);
		if(cacheValue != null){
			return cacheValue;
		}
		Document docu = XMLmanager.getDomByFile(filePath);
		Object obj = null;
		obj = parse.parse(docu);
		CacheMang.pushObjectTocache(cacheName, key, obj,cacheManager);
		return obj;
	}
}
