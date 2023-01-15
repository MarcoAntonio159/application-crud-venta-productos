package com.example.asincrona10.model.service;

import com.example.asincrona10.model.entity.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> listarTodos();
    public void guardar(Producto producto);
    public Producto buscarPorId(Long id);
    public void eliminar(Long id);
}
