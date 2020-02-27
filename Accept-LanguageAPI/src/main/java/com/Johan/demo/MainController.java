package com.Johan.demo;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@Configuration
//Controller
@RestController
public class MainController {
	
	@Autowired
	private MessageSource messageSource;


//passing a path variable 
//translate/AnyString    translate 1 word that is in the message.properties list.
//some phrases are as well in the list for translation! every word shall be separated with a . as is hello.world.of.space
	
	@GetMapping(path = "/translate/{name}", produces = MediaType.APPLICATION_JSON_VALUE) //{} points to a Variable name and annotated as PathVariable! 
	public String translateWords(@RequestHeader(name = "Accept-Language", required = false) Locale locale,@PathVariable String name) {
		String pattern = "{\"Keyword\":\"%s\", \"Translated\":\"%s\"}";
		
		
		return String.format(pattern, name , messageSource.getMessage(name, null, locale));
		
	}

	@GetMapping(value ="/newWord/{word}")
	public String addWord(@PathVariable String word) {
		
		return "the Word " + word + " added";
	}
	
	
}
