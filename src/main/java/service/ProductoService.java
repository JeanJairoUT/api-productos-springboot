/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProductoRepository;

/**
 *
 * @author jairo
 */
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    public List<Producto> listar() {
        return repository.findAll(); // SELECT * FROM productos
    }

    public Producto guardar(Producto p) {
        return repository.save(p); // INSERT INTO productos
    }

    public void eliminar(int id) {
        repository.deleteById(id); // DELETE FROM productos WHERE id = ?
    }
}