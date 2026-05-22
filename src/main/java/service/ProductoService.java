package service;

import java.util.List;
import model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    public List<Producto> listar() {
        return repository.findAll(); // SELECT * FROM productos
    }

    // NUEVO MÉTODO
    public Producto buscarPorId(int id) {
        return repository.findById(id).orElse(null); // Busca por ID, si no lo encuentra devuelve null
    }

    public Producto guardar(Producto p) {
        return repository.save(p); // INSERT o UPDATE INTO productos
    }

    public void eliminar(int id) {
        repository.deleteById(id); // DELETE FROM productos WHERE id = ?
    }
}