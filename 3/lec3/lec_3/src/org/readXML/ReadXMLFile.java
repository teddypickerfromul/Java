package org.readXML;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.model.ModelItemKurs;
import org.model.ModelListKurs;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadXMLFile {
	File f;

	public ReadXMLFile(File f) {
		this.f = f;
	}

	public void readFile(ModelListKurs model) {
		try {
			
			File xmlFile = new File(f.getAbsolutePath());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("kurs");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					ModelItemKurs itemKurs = new ModelItemKurs();
					itemKurs.setCurrent(getTagValue("current", eElement));
					itemKurs.setValuta(getTagValue("valuta", eElement));
					itemKurs.setValue(getTagValueDouble("value", eElement));
					model.getList().add(itemKurs);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}

	private Double getTagValueDouble(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return Double.valueOf(nValue.getNodeValue());
	}
}
