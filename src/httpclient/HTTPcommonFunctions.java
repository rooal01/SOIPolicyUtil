/******************************************************************************
 *
 *  CA Technologies, Inc.
 *  One CA Plaza
 *  Islandia, NY 11749 USA
 *
 *  Copyright (c) 2012 CA Technologies, Inc.
 *  All rights reserved.
 *
 *  IN NO EVENT SHALL CA TECHNOLOGIES INCORPORATED BE LIABLE FOR
 *  ANY INCIDENTAL, INDIRECT, SPECIAL, OR CONSEQUENTIAL DAMAGES
 *  WHATSOEVER (INCLUDING BUT NOT LIMITED TO LOST PROFITS) ARISING OUT
 *  OF OR RELATED TO THIS SOFTWARE, EVEN IF CA TECHNOLOGIES INCORPORATED
 *  HAS BEEN ADVISED OF, KNOWN, OR SHOULD HAVE KNOWN, THE POSSIBILITY OF
 *  SUCH DAMAGES.
 *
 ******************************************************************************
 *
 * @title       : com/ca/rest/http/client/HTTPcommonFunctions
 * @description : Common HTTP functions
 *
 * @category    : RIM SOI REST connector
 * @package     : com.ca.rest.http.client
 * @copyright   : 2013 KPN RIM Tooling
 * 
 * $Author: schee805 $
 * $Rev: 3 $
 * $Date: 2017-06-02 15:46:30 +0200 (Fri, 02 Jun 2017) $
 * 
 * $Id: HTTPcommonFunctions.java 3 2017-06-02 13:46:30Z schee805 $
 * 
 */

package httpclient;

import java.io.StringReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class HTTPcommonFunctions 
{
	
	// Post the XML to the required Endpoint using Username/Password, and read the results
	public static HTTPResponseData Post_XML(URL Endpoint, String user, String pass, String XML)
	{
		HTTPResponseData returnResult = new HTTPResponseData();
		
		try
		{
			HttpClientBase httpClientBase = new HttpClientBase() ;
			returnResult = httpClientBase.openConnection(user, pass, Endpoint.toString(), XML);
		}
		catch (Exception e)
		{
			returnResult.responseMessage = e.getMessage();
		}
		
		return returnResult;
	}
	
	
	// Post the XML to the required Endpoint using Base64 Credentials, and read the results
	public static HTTPResponseData Post_XML(URL Endpoint, String creds, String XML)
	{
		HTTPResponseData returnResult = new HTTPResponseData();
		
		try
		{
			HttpClientBase httpClientBase = new HttpClientBase() ;
			returnResult = httpClientBase.openConnection(creds, Endpoint.toString(), XML);
		}
		catch (Exception e)
		{
			returnResult.responseMessage = e.getMessage();
		}
		
		return returnResult;
	}
	
	// Do a GET to the required Endpoint using Username/Password, and read the results
	public static HTTPResponseData Get(URL Endpoint, String user, String pass)
	{
		HTTPResponseData returnResult = new HTTPResponseData();
		
		try
		{
			HttpClientBase httpClientBase = new HttpClientBase();
			httpClientBase.conn.setRequestMethod("GET");
			returnResult = httpClientBase.openConnection(user, pass, Endpoint.toString(), null);
		}
		catch (Exception e)
		{
			returnResult.responseMessage = e.getMessage();
		}
		
		return returnResult;
	}
	
	// Do a GET to the required Endpoint using Base64 Credentials, and read the results
	public static HTTPResponseData Get(URL Endpoint, String creds)
	{
		HTTPResponseData returnResult = new HTTPResponseData();
		
		try
		{
			HttpClientBase httpClientBase = new HttpClientBase();
			httpClientBase.conn.setRequestMethod("GET");
			returnResult = httpClientBase.openConnection(creds, Endpoint.toString(), null);
		}
		catch (Exception e)
		{
			returnResult.responseMessage = e.getMessage();
		}
		
		return returnResult;
	}
	
	
	// Get an Element (value) from an XML string
	public static String getXMLValueFromNode(String xmlRecords, String tagName, String elementName)
	{
		String value = null;

		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlRecords));
			
			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName(tagName);
		
			// Iterate the tickets, assign the latest caseId found in the results.
			for (int i=0; i < nodes.getLength(); i++)
			{
				Element element = (Element) nodes.item(i);
			
				NodeList name = element.getElementsByTagName(elementName);
				Element line = (Element) name.item(0);
				
				value = getCharacterDataFromElement(line);
			}
			
			// Return null if the value was empty
			if (value.equals(""))
			{
				value = null;
			}
		}
		catch (Exception e)
		{
//TODO
		}
		
		return value;
	}
	
	// Function to get the character data from an XML Element
	public static String getCharacterDataFromElement(Element e)
	{
		Node child = e.getFirstChild();
		if (child instanceof CharacterData)
		{
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return null;
	}
	
	// Function to limit a string's length, truncate the rest
	public static String limitString(String value, int length)
	{
		StringBuilder buf = new StringBuilder(value);
		if (buf.length() > length)
		{
			buf.setLength(length);
		}

		return buf.toString();
	}
	
	// Function to limit a string's length, replace the rest with String appender (example: "...")
	public static String limitString(String value, int length, String appender)
	{
		StringBuilder buf = new StringBuilder(value);
		if (buf.length() > length)
		{
			buf.setLength(length);
			buf.append(appender);
		}

		return buf.toString();
	}
	
	// Convert SOI Date format into BizTalk required format
	public static String convertDateSOItoBizTalk(String soidate)
	{
		String biztalkdate = null;
		DatatypeFactory factory = null;
		
		SimpleDateFormat f_output = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'");
		TimeZone tz = TimeZone.getTimeZone("UTC");
	    f_output.setTimeZone(tz);
	    
	    try 
		{
			factory = DatatypeFactory.newInstance();
		    XMLGregorianCalendar xmlGregCal = factory.newXMLGregorianCalendar(soidate);
		    GregorianCalendar gregCal = xmlGregCal.toGregorianCalendar();
		    Date dateObj = gregCal.getTime();
		    long GregDate = dateObj.getTime();
		    Date rawDateObj = new Date(GregDate);
		    biztalkdate = f_output.format(rawDateObj);
		} 
		catch (DatatypeConfigurationException e) 
		{
			// TODO Auto-generated catch block
		}
		
	    return biztalkdate;
	}
	
	public static String urlsafe(String input)
	{
        StringBuilder resultStr = new StringBuilder();
        for (char ch : input.toCharArray())
        {
            if (isUnsafe(ch))
            {
                resultStr.append('%');
                resultStr.append(toHex(ch / 16));
                resultStr.append(toHex(ch % 16));
            }
            else
            {
                resultStr.append(ch);
            }
        }
        return resultStr.toString();
    }
	
	private static char toHex(int ch)
	{
        return (char) (ch < 10 ? '0' + ch : 'A' + ch - 10);
    }
	
	private static boolean isUnsafe(char ch)
	{
        if (ch > 128 || ch < 0)
            return true;
        return " %$&+,/:;=?@<>#%".indexOf(ch) >= 0;
    }
}
