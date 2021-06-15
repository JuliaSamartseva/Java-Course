
package com.example.consumingwebservice;

import com.example.consumingwebservice.classes.GetMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
@Controller
public class ConsumingWebServiceApplication {

	@Autowired
	MessageClient client;

	public static void main(String[] args) {
		SpringApplication.run(ConsumingWebServiceApplication.class, args);
	}

	@GetMapping("/send")
	public String hello() {
		return "form.html";
	}

	@GetMapping("/message")
	public String send(Model model)  {
		model.addAttribute("message", new Message());
		return "form";
	}

	@PostMapping("/result")
	public String greetingSubmit(@ModelAttribute Message message, Model model) throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {
		GetMessageResponse response = client.getMessage(message.getTitle());
		message.setBody(RSAUtil.decrypt(response.getBody(), RSAUtil.privateKey));
		model.addAttribute("message", message);
		return "result";
	}


}
