package controller;

import java.util.List;
import model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    
    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable int id) {
        Producto p = service.buscarPorId(id);
        if (p != null) {
            return ResponseEntity.ok(p);
        }
        return ResponseEntity.notFound().build();
    }

    
    @PostMapping
    public Producto guardar(@RequestBody Producto p) {
        return service.guardar(p);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable int id, @RequestBody Producto p) {
        Producto existente = service.buscarPorId(id);
        if (existente != null) {
            existente.setNombre(p.getNombre());
            existente.setPrecio(p.getPrecio());
            Producto actualizado = service.guardar(existente);
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Producto existente = service.buscarPorId(id);
        if (existente != null) {
            service.eliminar(id);
            return ResponseEntity.ok("Producto con ID " + id + " eliminado correctamente de MySQL.");
        }
        return ResponseEntity.notFound().build();
    }
}