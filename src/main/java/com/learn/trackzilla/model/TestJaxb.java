package com.learn.trackzilla.model;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class TestJaxb {

	public static void main(String[] args) throws JAXBException {
		// TODO Auto-generated method stub
		Application app = new Application(0, "qwerty", "testing marshalling");
		JAXBContext jc = JAXBContext.newInstance(Application.class);
		StringWriter writer = new StringWriter();
		jc.createMarshaller().marshal(app, writer);
		Application a1 = (Application) jc.createUnmarshaller().unmarshal(new StringReader(writer.toString()));
		System.out.println(a1.getName());
		try {
			  Class.forName("org.postgresql.Driver");
			  //on classpath
			  System.out.println("in the path");
			} catch(ClassNotFoundException e) {
			  e.printStackTrace();
			}
	}

}
