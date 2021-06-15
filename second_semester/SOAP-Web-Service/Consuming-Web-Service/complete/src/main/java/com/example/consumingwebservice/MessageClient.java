
package com.example.consumingwebservice;

import com.example.consumingwebservice.classes.GetMessageRequest;
import com.example.consumingwebservice.classes.GetMessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class MessageClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(MessageClient.class);

	public GetMessageResponse getMessage(String name) {
		GetMessageRequest request = new GetMessageRequest();
		request.setTitle(name);

		log.info("Requesting " + name);

		return (GetMessageResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8081/ws/message", request,
						new SoapActionCallback(
								"http://spring.io/guides/gs-producing-web-service/GetCountryRequest"));
	}

}
