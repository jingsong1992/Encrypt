package ce_user_tools;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLmanager {
	
	/*
	 * load and return documnet object by parse filePath
	 * */
	public static Document getDomByFile(String strXmlFilePath){
		Document docu = null;
		try {
			DocumentBuilderFactory  dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			docu = db.parse(strXmlFilePath);
			return docu;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	
	/*
	 * get second node by root and nodeName
	 * */
	public static Node findSecondChildNode(Node rootNode,String childNodeName){
		try{
			NodeList nodeList = rootNode.getChildNodes();
			for(int i=0;i<nodeList.getLength();i++){
				if(nodeList.item(i).getNodeName().equals(childNodeName)){
					return nodeList.item(i);
				}
			}
			return null;
		}catch(Exception e){
			return null;
		}
	}
	
	
	/*
	 * get node's attribute by node and attributeName
	 * */
	public static String getNodeAttribute(Node node,String attributeName){
		if(node == null){
			return null;
		}
		if(node.getNodeType() == Element.ELEMENT_NODE){
			Element element = (Element)node;
			return element.getAttribute(attributeName);
		}
		return null;
	}
	
	
	/*
	 * if node include childnode,want to get chileNode's value,by node and childnodeName 
	 * */
	public static String getChileNodeValue(Node parentNode,String childNodeName){
		if(parentNode == null){
			return null;
		} 
		Node childNode = findChildNodeByParentNode(childNodeName,parentNode);
		if(childNode == null){
			return null;
		}
		String childNodeValue = getNodeValue(childNode);
		return childNodeValue;
	}
	
	
	/*
	 * get node by parentNode and nodeName
	 * */
	public static Node findChildNodeByParentNode(String childNodeName,Node parentNode){
		if(parentNode == null){
			return null;
		}
		Node childNode = parentNode.getFirstChild();
		while(childNode !=null){
			if(childNode.getNodeName().equals(childNodeName)){
				return childNode;
			}
			childNode = childNode.getNextSibling();
		}
		return null;
	}
	
	
	/*
	 * get childNodeValue by childNode
	 * */
	public static String getNodeValue(Node node){
		if(node == null){
			return null;
		}
		String nodeValue  = node.getFirstChild().getNodeValue();
		return nodeValue;
	}
}
