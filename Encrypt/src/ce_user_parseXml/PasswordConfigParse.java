package ce_user_parseXml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ce_user_bean.PasswordConfig;
import ce_user_bean.PasswordConfigEmail;
import ce_user_tools.XMLmanager;


public class PasswordConfigParse implements CustomParserXML{

	public Object parse(Document docu) {

		PasswordConfig passwordConfig = new PasswordConfig();
		Element root = docu.getDocumentElement();
		
		/*
		 *<root>
		 *	<salt length="64">
		 *		<location>PREFIX</location>
	     *	</salt>
	     *<root>
		 * */
		
		Node saltNode = XMLmanager.findChildNodeByParentNode("salt", root);
		String saltLength = XMLmanager.getNodeAttribute(saltNode, "length");
		String saltLocation = XMLmanager.getChileNodeValue(saltNode, "location");
		passwordConfig.setSaltLength(Integer.parseInt(saltLength));
		passwordConfig.setSaltLocation(saltLocation);
		
		/*<encrypt>
		 	<algorithm>
				<class>com.cs.encrypt.SHA256Encrypt</class>
			</algorithm>
			<algorithm>
				<class>com.cs.encrypt.MD5Encrypt</class>
			</algorithm>
		</encrypt>
		 * */
		Node encryptNode = XMLmanager.findChildNodeByParentNode("encrypt", root);
		List<String> classList = new ArrayList<String>();
		if(encryptNode != null){
			NodeList algorithm = encryptNode.getChildNodes();
			for(int i = 0,leng = algorithm.getLength();i<leng;i++){
				Node childNode = algorithm.item(i);
				if(!"algorithm".equals(childNode.getNodeName())){
					continue;
				}else{
					String classValue = XMLmanager.getChileNodeValue(childNode, "class");
					if(classValue != null&&classValue != ""){
						classList.add(classValue);
					}
				}
			}
		}
		passwordConfig.setAlgorithmList(classList);
		
		/*
		 * <generate>ADMIN|USER</generate>
		 * */
		String generateValue = XMLmanager.getChileNodeValue(root, "generate");
		passwordConfig.setGenerate(generateValue);
		
		/*
		 <reset>
			<reset_url_prefix>http://10.39.104.39:39083/CEWeb</reset_url_prefix>
			<reset_expire>3</reset_expire>
			<autoLogin login="gggg">T</autoLogin>
		</reset>
		 * */
		Node resetNode = XMLmanager.findChildNodeByParentNode("reset", root);
		String resetUrlPrefixValue = XMLmanager.getChileNodeValue(resetNode, "reset_url_prefix");
		String resetExpirevalue = XMLmanager.getChileNodeValue(resetNode, "reset_expire");
		String autoLoginValue = XMLmanager.getChileNodeValue(resetNode, "autoLogin");
		boolean flag = false;
		if(autoLoginValue == "T"||autoLoginValue == "t"){
			flag = true;
		}
		passwordConfig.setResetUrlPrefix(resetUrlPrefixValue);
		passwordConfig.setResetExpire(resetExpirevalue);
		passwordConfig.setAutoLogin(flag);
		
		/*
		 <email>
			<sender name="CSBankSupport"/>
			<emailAddressField name="C_PRIVATE_MAIL"/>
			<emailList>
				<initialList>
					<template>loginUser</template>
					<template>loginCompany</template>
				</initialList>
			</emailList>
		</email>
		 * 
		 * */
		PasswordConfigEmail passwordConfigEmail = new PasswordConfigEmail();
		Node emailNode = XMLmanager.findChildNodeByParentNode("email", root);
		Node senderNode= XMLmanager.findSecondChildNode(emailNode, "sender");
		String senderName = XMLmanager.getNodeAttribute(senderNode, "name");
		Node emailAddressNode = XMLmanager.findSecondChildNode(emailNode, "emailAddressField");
		String emailAddressName = XMLmanager.getNodeAttribute(emailAddressNode, "name");
		Node emailListNode = XMLmanager.findSecondChildNode(emailNode, "emailList");
		Node initialListNode = XMLmanager.findSecondChildNode(emailListNode, "initialList");
		List<String> list = new ArrayList<String>();
		NodeList templateList = initialListNode.getChildNodes();
		for(int i = 0,leng = templateList.getLength();i<leng;i++){
			Node initialListChildNode = templateList.item(i);
			if(!"template".equals(initialListChildNode.getNodeName())){
				continue;
			}else{
				String templateValue = XMLmanager.getNodeValue(initialListChildNode);
				list.add(templateValue);
			}
		}
		passwordConfigEmail.setEmailSender(senderName);
		passwordConfigEmail.setEmailAddressFiledName(emailAddressName);
		passwordConfigEmail.setInitialList(list);
		passwordConfig.setPasswordConfigEmail(passwordConfigEmail);
		return passwordConfig;
	}
	
	public static void main(String[] args) {
		String fileName = PasswordConfigParse.class.getClassLoader().getResource("password_config.xml").getPath();
		Document docu = XMLmanager.getDomByFile(fileName);
		PasswordConfigParse a = new PasswordConfigParse();  
		PasswordConfig passwordConfig = (PasswordConfig) a.parse(docu);
		int saltLength = passwordConfig.getSaltLength();
		String saltLocation = passwordConfig.getSaltLocation();
		List<String> classList = passwordConfig.getAlgorithmList();
		for(int i=0;i<classList.size();i++){
			System.out.println(classList.get(i));
		}
		List<String> list = passwordConfig.getPasswordInitialList();
		for(int i = 0,lengt = list.size();i<lengt;i++){
			System.out.println(list.get(i));
		}
	}
}
