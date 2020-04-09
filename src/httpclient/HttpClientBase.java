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
 * @title       : com/ca/rest/http/client/HttpClientBase
 * @description : WebServices Client Base to create web services calls.
 *
 * @category    : RIM SOI REST connector
 * @package     : com.ca.rest.http.client
 * @copyright   : 2013 KPN RIM Tooling
 * 
 * $Author: schee805 $
 * $Rev: 33 $
 * $Date: 2017-06-15 20:59:00 +0200 (Thu, 15 Jun 2017) $
 * 
 * $Id: HttpClientBase.java 33 2017-06-15 18:59:00Z schee805 $
 * 
 */

package httpclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;



public class HttpClientBase 
{
	HttpURLConnection conn = null;
	HTTPResponseData returnResult = new HTTPResponseData();
	
	public static Logger logger = Logger.getLogger(HttpClientBase.class.getName());
	
	
	public HTTPResponseData openConnection(String credentials, String uri, String postXML) 
    {
		
        try
        {
            URL url = new URL( uri ); 
            conn = (HttpURLConnection)url.openConnection(); 
            
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Authorization", "Basic " + credentials );
            conn.setRequestProperty("Content-type", "application/json; charset=utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            
            returnResult.responseCode		= conn.getResponseCode();
            returnResult.responseMessage	= conn.getResponseMessage();
            
            OutputStream wr = new DataOutputStream(conn.getOutputStream());      
            wr.write(postXML.getBytes());
            wr.flush ();
            wr.close ();
            
            BufferedReader reader = null;
            StringBuilder stringBuilder = null ;
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            returnResult.responseBody = stringBuilder.toString() ;
            
            reader = null ;
            stringBuilder = null ;
            
	        returnResult.responseCode		= conn.getResponseCode();
	        returnResult.responseMessage	= conn.getResponseMessage();
	        returnResult.responseLocation    = conn.getHeaderField("Location");
//	        logger.warning("HttpClientBase Response Code: " + returnResult.responseCode);
//	        logger.warning("HttpClientBase Response Message: " + returnResult.responseMessage);
//	        logger.warning("HttpClientBase Response Body: " + returnResult.responseBody);
        }
        catch( Exception e)
        {
        	logger.warning("HttpClientBase Exception: " + e.getMessage());
        }
        
        return returnResult;
    }
	
    public HTTPResponseData openConnection(String username, String password, String uri, String postXML) 
    {
        try
        {
            URL url = new URL( uri ); 
            conn = (HttpURLConnection)url.openConnection(); 
            
            conn.setRequestMethod("POST");

           	setBasicAuth(username, password);

           	conn.setRequestProperty("Content-type", "application/json; charset=utf-8");
            conn.setRequestProperty( "Accept", "application/json");
            conn.setDoOutput(true);
            
//            returnResult.responseCode		= conn.getResponseCode();
//            returnResult.responseMessage	= conn.getResponseMessage();
            
            OutputStream wr = new DataOutputStream(conn.getOutputStream());      
            wr.write(postXML.getBytes());
            wr.flush ();
            wr.close ();
            
            BufferedReader reader = null;
            StringBuilder stringBuilder = null ;
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            returnResult.responseBody = stringBuilder.toString() ;
            
            reader = null ;
            stringBuilder = null ;
            
	        returnResult.responseCode		= conn.getResponseCode();
	        returnResult.responseMessage	= conn.getResponseMessage();
	        returnResult.responseLocation    = conn.getHeaderField("Location");
//	        logger.warning("HttpClientBase Response Code: " + returnResult.responseCode);
//	        logger.warning("HttpClientBase Response Message: " + returnResult.responseMessage);
//	        logger.warning("HttpClientBase Response Body: " + returnResult.responseBody);
        }
        catch( Exception e)
        {
            logger.warning("HttpClientBase Exception: " + e.getMessage());
        }
        
        return returnResult;
    }
    
    public HTTPResponseData openGetConnection(String username, String password, String uri) 
    {
    	String strUserPassword = username + ":" + password;   
        String encodedAuthorization = BASE64Encoder.encode( strUserPassword.getBytes() );
        return openGetConnection (encodedAuthorization, uri);
    }
    
    public HTTPResponseData openGetConnection(String username, String password, String uri, String contenttype) 
    {
    	String strUserPassword = username + ":" + password;   
        String encodedAuthorization = BASE64Encoder.encode( strUserPassword.getBytes() );
        return openGetConnectionwc (encodedAuthorization, uri, contenttype);
    }
    
    public HTTPResponseData openGetConnectionwc(String credentials, String uri, String conttype) 
    {
        try
        {
            URL url = new URL( uri ); 
            conn = (HttpURLConnection)url.openConnection(); 
            conn.setRequestMethod("GET");

            conn.setRequestProperty("Authorization", "Basic " + credentials );
            conn.setRequestProperty("Content-type", conttype+"; charset=utf-8");
            conn.setRequestProperty("Accept", conttype);
            conn.setDoOutput(true);
            
            BufferedReader reader = null;
            StringBuilder stringBuilder = null ;
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            returnResult.responseBody = stringBuilder.toString() ;
            
            reader = null ;
            stringBuilder = null ;
            
            returnResult.responseCode		= conn.getResponseCode();
	        returnResult.responseMessage	= conn.getResponseMessage();
	        returnResult.responseLocation    = conn.getHeaderField("Location");
//	        logger.warning("HttpClientBase Response Code: " + returnResult.responseCode);
//	        logger.warning("HttpClientBase Response Message: " + returnResult.responseMessage);
//	        logger.warning("HttpClientBase Response Body: " + returnResult.responseBody);
        }
        catch( Exception e)
        {
        	logger.warning("HttpClientBase Exception: " + e.getMessage());
        }
        
        return returnResult;
    }
    
    public HTTPResponseData openGetConnection(String credentials, String uri) 
    {
        try
        {
            URL url = new URL( uri ); 
            conn = (HttpURLConnection)url.openConnection(); 
            conn.setRequestMethod("GET");

            conn.setRequestProperty("Authorization", "Basic " + credentials );
            conn.setRequestProperty("Content-type", "application/atom+xml; charset=utf-8");
            conn.setRequestProperty("Accept", "application/xml");
            conn.setDoOutput(true);
            
            BufferedReader reader = null;
            StringBuilder stringBuilder = null ;
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            returnResult.responseBody = stringBuilder.toString() ;
            
            reader = null ;
            stringBuilder = null ;
            
            returnResult.responseCode		= conn.getResponseCode();
	        returnResult.responseMessage	= conn.getResponseMessage();
	        returnResult.responseLocation    = conn.getHeaderField("Location");
//	        logger.warning("HttpClientBase Response Code: " + returnResult.responseCode);
//	        logger.warning("HttpClientBase Response Message: " + returnResult.responseMessage);
//	        logger.warning("HttpClientBase Response Body: " + returnResult.responseBody);
        }
        catch( Exception e)
        {
        	logger.warning("HttpClientBase Exception: " + e.getMessage());
        }
        
        return returnResult;
    }
    
    public HTTPResponseData openPutConnection(String credentials, String uri, String putXML) 
    {
        try
        {
            URL url = new URL( uri ); 
            conn = (HttpURLConnection)url.openConnection(); 
            
            conn.setRequestMethod("PUT");

            conn.setRequestProperty( "Authorization", "Basic " + credentials );
            conn.setRequestProperty("Content-type", "application/json; charset=utf-8");
            conn.setRequestProperty( "Accept", "application/json");
            conn.setDoOutput(true);
            
            OutputStream wr = new DataOutputStream(conn.getOutputStream());      
            wr.write(putXML.getBytes());
            wr.flush ();
            wr.close ();
            
            BufferedReader reader = null;
            StringBuilder stringBuilder = null ;
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            returnResult.responseBody = stringBuilder.toString() ;
            
            reader = null ;
            stringBuilder = null ;
            
	        returnResult.responseCode		= conn.getResponseCode();
	        returnResult.responseMessage	= conn.getResponseMessage();
	        returnResult.responseLocation    = conn.getHeaderField("Location");
	        logger.warning("HttpClientBase Response Code: " + returnResult.responseCode);
	        logger.warning("HttpClientBase Response Message: " + returnResult.responseMessage);
//	        logger.warning("HttpClientBase Response Body: " + returnResult.responseBody);
        }
        catch( Exception e)
        {
        	logger.warning("HttpClientBase Exception: " + e.getMessage());
        }
        
        return returnResult;
    }
    
    public HTTPResponseData openPostConnection(String user, String password, String uri, String ContentType, String putXML) 
    {
        try
        {
            URL url = new URL( uri ); 
            conn = (HttpURLConnection)url.openConnection(); 
            
            conn.setRequestMethod("POST");

            setBasicAuth(user, password);
            conn.setRequestProperty("Content-type", ContentType);
            conn.setRequestProperty( "Accept", "text/html, application/xml, *;q=0.1");
            conn.setDoOutput(true);
//            System.out.println("Body to be sent" + putXML);
            
            OutputStream wr = new DataOutputStream(conn.getOutputStream());      
            wr.write(putXML.getBytes());
            wr.flush ();
            wr.close ();
            
            BufferedReader reader = null;
            StringBuilder stringBuilder = null ;
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            returnResult.responseBody = stringBuilder.toString() ;
            
            reader = null ;
            stringBuilder = null ;
            
	        returnResult.responseCode		= conn.getResponseCode();
	        returnResult.responseMessage	= conn.getResponseMessage();
	        returnResult.responseLocation    = conn.getHeaderField("Location");
//	        logger.warning("HttpClientBase Response Code: " + returnResult.responseCode);
//	        logger.warning("HttpClientBase Response Message: " + returnResult.responseMessage);
//	        logger.warning("HttpClientBase Response Body: " + returnResult.responseBody);
        }
        catch( Exception e)
        {
        	logger.warning("HttpClientBase Exception: " + e.getMessage());
        }
        
        return returnResult;
    }
    
    public HTTPResponseData openPutConnection(String user, String password, String uri, String ContentType, String putXML) 
    {
        try
        {
            URL url = new URL( uri ); 
            conn = (HttpURLConnection)url.openConnection(); 
            
            conn.setRequestMethod("PUT");

            setBasicAuth(user, password);
            conn.setRequestProperty("Content-type", ContentType);
            conn.setRequestProperty( "Accept", "application/xml");
            conn.setDoOutput(true);
//            System.out.println("Body to be sent" + putXML);
            
            OutputStream wr = new DataOutputStream(conn.getOutputStream());      
            wr.write(putXML.getBytes());
            wr.flush ();
            wr.close ();
            
            BufferedReader reader = null;
            StringBuilder stringBuilder = null ;
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            returnResult.responseBody = stringBuilder.toString() ;
            
            reader = null ;
            stringBuilder = null ;
            
	        returnResult.responseCode		= conn.getResponseCode();
	        returnResult.responseMessage	= conn.getResponseMessage();
	        returnResult.responseLocation    = conn.getHeaderField("Location");
//	        logger.warning("HttpClientBase Response Code: " + returnResult.responseCode);
//	        logger.warning("HttpClientBase Response Message: " + returnResult.responseMessage);
//	        logger.warning("HttpClientBase Response Body: " + returnResult.responseBody);
        }
        catch( Exception e)
        {
        	logger.warning("HttpClientBase Exception: " + e.getMessage());
        }
        
        return returnResult;
    }
    
    /**
     * Sets basic Authentication with the global user and password for this
     * connection
     * @param conn
     */
    public void setBasicAuth( String username, String password)
    {
        String strUserPassword = username + ":" + password;      
        String encodedAuthorization = BASE64Encoder.encode( strUserPassword.getBytes() );      
        conn.setRequestProperty( "Authorization", "Basic "+ encodedAuthorization );
    }

    
    
    public String readResults(String postXml)
    {
        try
        {
            OutputStream wr = new DataOutputStream(conn.getOutputStream());      
            wr.write(postXml.getBytes());
            wr.flush ();
            wr.close ();
            
            BufferedReader reader = null;
            StringBuilder stringBuilder = null ;
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            String strResult = stringBuilder.toString() ;
            
            reader = null ;
            stringBuilder = null ;
            
            return strResult;
 
        }
        catch( Exception e )
        {
            e.printStackTrace() ;
        }

        return null ;
    }
    
    public int getResponseCode() throws IOException
    {
    	return conn.getResponseCode();
    }
    
    public String getResponseMessage() throws IOException
    {
    	return conn.getResponseMessage();
    }
}
