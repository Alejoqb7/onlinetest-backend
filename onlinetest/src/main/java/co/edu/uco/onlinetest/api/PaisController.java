package co.edu.uco.onlinetest.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.onlinetest.dto.PaisDTO;

@RestController
@RequestMapping("/api/v1/paises")
public class PaisController {

    @GetMapping("/dummy")
    public PaisDTO getDummy() {
        return new PaisDTO();
    }

    @GetMapping
    public List<PaisDTO> consultar() {
        var lista = new ArrayList<PaisDTO>();
        lista.add(new PaisDTO(UUID.randomUUID(), "Colombia"));
        lista.add(new PaisDTO(UUID.randomUUID(), "Perú"));
        lista.add(new PaisDTO(UUID.randomUUID(), "Bolivia"));
        lista.add(new PaisDTO(UUID.randomUUID(), "Panamá"));
        lista.add(new PaisDTO(UUID.randomUUID(), "Venezuela"));
        return lista;
    }

    @GetMapping("/{id}")
    public PaisDTO consultar(@PathVariable UUID id) {
        return new PaisDTO(id, "Nombre de ejemplo");
    }

    @PostMapping
    public PaisDTO crear(@RequestBody PaisDTO pais) {
        pais.setId(UUID.randomUUID());
        return pais;
    }

    @PutMapping("/{id}")
    public PaisDTO modificar(@PathVariable UUID id, @RequestBody PaisDTO pais) {
        pais.setId(id);
        return pais;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable UUID id) {
        // implementar lógica real de eliminación
    }
}
