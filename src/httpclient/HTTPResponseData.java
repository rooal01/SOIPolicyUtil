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
 * @title       : com/ca/rest/http/client/HTTPResponseData
 * @description : WebServices response data validator.
 *
 * @category    : RIM SOI REST connector
 * @package     : com.ca.rest.http.client
 * @copyright   : 2013 KPN RIM Tooling
 * 
 * $Author: schee805 $
 * $Rev: 3 $
 * $Date: 2017-06-02 15:46:30 +0200 (Fri, 02 Jun 2017) $
 * 
 * $Id: HTTPResponseData.java 3 2017-06-02 13:46:30Z schee805 $
 * 
 */
 

package httpclient;

public class HTTPResponseData 
{
	public int responseCode = 500;
	public String responseBody = "";
	public String responseMessage = "Unknown Internal Server Error";
	public String responseLocation = "";
}
