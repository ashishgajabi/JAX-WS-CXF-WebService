package in.blogspot.ashish4java.GlobalWeatherService.handler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class AuthHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		String userName = "";
		String password = "";
		Boolean direction = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (!direction) {
			Map<String, List<String>> reqheaders = (Map<String, List<String>>) context
					.get(MessageContext.HTTP_REQUEST_HEADERS);
			List<String> uName = reqheaders.get("UserName");
			List<String> pWord = reqheaders.get("Password");

			if (uName != null && !uName.isEmpty()) {
				userName = uName.get(0);
			}
			if (pWord != null && !pWord.isEmpty()) {
				password = pWord.get(0);
			}
			if (userName.equals("Ashish") && password.equals("Password123")) {
				return true;
			} else {
				HttpServletResponse response = (HttpServletResponse) context.get(MessageContext.SERVLET_RESPONSE);
				try {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				} catch (IOException e) {
					System.out.println("Exception in setting error in response :" +e.getMessage());
				}
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		System.out.println("inside handleFault");
		return true;
	}

	@Override
	public void close(MessageContext context) {
		System.out.println("inside close");
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

}
