package ce_user_tools;

public class FilePathGet {
	
	//get filePath interface
	public static String getFilePath(String fileName,Class className){
		if(className == null||"".equals(className)){
			return null;
		}
		String filePath = className.getClassLoader().getResource(fileName).getPath();
		return filePath;
	} 
}
