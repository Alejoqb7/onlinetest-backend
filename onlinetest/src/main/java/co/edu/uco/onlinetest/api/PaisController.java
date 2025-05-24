package co.edu.uco.onlinetest.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.onlinetest.dto.PaisDTO;

@RestController
@RequestMapping("/api/v1/paises")
public class PaisController {
	
	@GetMapping("/consultar")
	public String consultar() {
		return "Consultar todos los países";
	}
	
	@PostMapping("/dummy")
	public PaisDTO getDummy() {
		return new PaisDTO();
	}
	
	@PutMapping("/modificar")
	public String modificar() {
		return "Modificar un país";
	}
	
	@DeleteMapping("/eliminar")
	public String eliminar() {
		return "Eliminar de forma definitiva un país";
	}
}
