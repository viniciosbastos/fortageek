package br.com.fortageek.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UsuarioService {

	@RequestMapping(path = "/user", method = RequestMethod.GET)
	public String test() {
		return "oh eu aqui djfhdsoghsiougiho";
	}
}
