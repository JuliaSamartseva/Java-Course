package com.example.producingwebservice;

import com.example.producingwebservice.classes.GetMessageRequest;
import com.example.producingwebservice.classes.GetMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Base64;


@Endpoint
public class MessageEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private final KeysRepository keysRepository;

	@Autowired
	public MessageEndpoint(KeysRepository keysRepository) {
		this.keysRepository = keysRepository;
	}


	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMessageRequest")
	@ResponsePayload
	public GetMessageResponse getResponse(@RequestPayload GetMessageRequest request) {
		GetMessageResponse response = new GetMessageResponse();
		response.setBody("Error");
		try {
			String responseText = "Received request from " + request.getTitle();
			String encryptedString = Base64.getEncoder().encodeToString(RSAUtil.encrypt(responseText, keysRepository.findKey(request.getTitle())));
			response.setBody(encryptedString);
			return response;
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		return response;
	}

}
