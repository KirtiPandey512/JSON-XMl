package com.books.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


import com.books.entity.Author;
import com.books.entity.Book;

public class ServiceImpl {
	public List<Book> readFromJson()
	{
		List<Book> books=new ArrayList<Book>();
		FileReader file;
		JSONParser parser=new JSONParser();
		try {
			file = new FileReader("C:\\Users\\DELL\\Downloads\\books.json");
			Object obj=parser.parse(file);
			
			JSONArray array=(JSONArray)obj;
			int i=0;
			while(i<array.size())
			{
				JSONObject object=(JSONObject) array.get(i);
				Book book=new Book();
				
				book.setId((String) object.get("id"));
				book.setTitle((String) object.get("title"));
				book.setPrice( (long) object.get("price"));
				
				JSONArray array2=(JSONArray) object.get("authors");
				Author author[]=new Author[array2.size()];
				int j=0;
				while(j<array2.size())
				{
					JSONObject object2=(JSONObject) array2.get(j);
					Author author2=new Author();
					author2.setId(( (String)object2.get("id")));
					author2.setName((String) object2.get("name"));
					author[j]=author2;
					j++;
				}
				book.setAuthors(author);
				books.add(book);
				i++;
			}
		} catch ( IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
	public void print(List<Book>books)
	{
		for(int i=0;i<books.size();i++)
		{
		System.out.println(books.get(i).getId());
		System.out.println(books.get(i).getTitle());
		System.out.println(books.get(i).getPrice());
		for(int j=0;j<books.get(i).getAuthors().length;j++)
		{
			System.out.println(books.get(i).getAuthors().toString());
		}
		}
	}
	public void writeToXml(List<Book>books)
	{
		String file="C:\\Users\\DELL\\Downloads\\book1.xml";	
			DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
				Document document=documentBuilder.newDocument();
				Element root=document.createElement("Books");
				document.appendChild(root);
				for(int i=0;i<books.size();i++)
				{
					Element root2=document.createElement("Book");
					root.appendChild(root2);
					Attr attr =document.createAttribute("id");
					String str=books.get(i).getId()+"";
					attr.setValue(str);
					root2.setAttributeNode(attr);
					
					Element root3Element=document.createElement("title");
					root3Element.appendChild(document.createTextNode(books.get(i).getTitle()));
					root2.appendChild(root3Element);
					
					Element root4Element=document.createElement("price");
					root4Element.appendChild(document.createTextNode(books.get(i).getPrice()+""));
					root2.appendChild(root4Element);
					
					Element root5Element=document.createElement("authors");
					Author author[]=books.get(i).getAuthors();
					for(int j=0;j<author.length;j++)
					{
						Element root511Element=document.createElement("author");
						
						
					Element root51Element=document.createElement("id");
					root51Element.appendChild(document.createTextNode(author[j].getId()));
					root511Element.appendChild(root51Element);
					
					Element root52Element=document.createElement("name");
					root52Element.appendChild(document.createTextNode(author[j].getName()));
					root511Element.appendChild(root52Element);
					root5Element.appendChild(root511Element);
					}
					root2.appendChild(root5Element);
				
			} 
				TransformerFactory transformerFactory=TransformerFactory.newInstance();
				try {
					Transformer  transformer=transformerFactory.newTransformer();
					DOMSource domSource=new DOMSource(document);
					StreamResult streamResult=new StreamResult(new File(file));
					try {
						transformer.transform(domSource, streamResult);
						System.out.println("Created");
					} catch (TransformerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


