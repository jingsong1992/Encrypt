package ce_user_servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.CacheManager;

import ce_user_bean.CacheManagerObject;
import ce_user_bean.PasswordConfig;
import ce_user_encrypt.Base64Encrypt;
import ce_user_encrypt.EncryptPolicy;
import ce_user_encrypt.PasswordHelper;
import ce_user_tools.FilePathGet;
import ce_user_tools.GetXmlObjectByCache;

/**
 * Servlet implementation class EncryptPasswordServlet
 */
public class EncryptPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EncryptPasswordServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CacheManagerObject cacheManagerObject = getCacheManagerObject();
		PrintWriter out = response.getWriter();
		String password = request.getParameter("password");
		PasswordConfig config = GetXmlObjectByCache.getPasswordConfig(cacheManagerObject);
		if(config == null){
			password = Base64Encrypt.encryptBase64(password);
		}else{
			password = PasswordHelper.encryptPasswordBySha256AndMD5(password, config);
		}
		out.print(password);
		out.close();
	}
	/*
	 * parse xml to get cacheManagerObject , cacheManagerObject contains cacheManager
	 * */
	public static CacheManagerObject getCacheManagerObject(){
		CacheManagerObject cacheManagerObject = new CacheManagerObject();
		String fileName = "password_config.xml";
		// add get filePath interface
		String filePath = FilePathGet.getFilePath(fileName, EncryptPasswordServlet.class);
		//end
		CacheManager cacheManager = CacheManager.create(filePath);
		cacheManagerObject.setCacheManager(cacheManager);
		return cacheManagerObject;
	}
}
