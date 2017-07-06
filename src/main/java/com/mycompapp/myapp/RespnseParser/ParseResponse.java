package com.mycompapp.myapp.RespnseParser;

import org.w3c.dom.*;

import javax.xml.parsers.*;

import java.io.*;

public class ParseResponse {

	StringBuilder prodList = new StringBuilder();

	public String getProductList(InputStream input) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			// Document doc = dBuilder.parse(inputFile);
			Document doc = dBuilder.parse(input);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("product");
			System.out.println("----------------------------");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("ProductName : "
							+ eElement.getElementsByTagName("displayName")
									.item(0).getTextContent());
					prodList.append(eElement
							.getElementsByTagName("displayName").item(0)
							.getTextContent());
					prodList.append(",");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return prodList.toString();
	}
}