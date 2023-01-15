package com.example.asincrona10.controller;

import com.example.asincrona10.model.entity.Producto;
import com.example.asincrona10.model.service.IProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/views/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping("/")
    public String listarProductos(Model model) {
        List<Producto> listadoProductos = productoService.listarTodos();

        model.addAttribute("titulo", "Lista de productos");
        model.addAttribute("productos", listadoProductos);
        return "/views/productos/listar";
    }

    @GetMapping("/create")
    public String crear(Model model) {

        Producto producto = new Producto();
        model.addAttribute("titulo","FORMULARIO: Nuevo Producto");
        model.addAttribute("producto", producto);

        return "/views/productos/frmCrear";
    }

    @PostMapping("/save")
    public String guardar(@Valid @ModelAttribute Producto producto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("titulo","FORMULARIO: Nuevo Producto");
            model.addAttribute("producto", producto);
            System.out.println("Existieron errores en el formulario");
            return "/views/productos/frmCrear";
        }
        productoService.guardar(producto);
        System.out.println("Producto guardado con exito");
        return "redirect:/views/productos/";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") Long idProducto, Model model) {
        Producto producto = productoService.buscarPorId(idProducto);
        model.addAttribute("titulo", "FORMULARIO: Editar Cliente");
        model.addAttribute("producto", producto);
        return "/views/productos/frmCrear";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") Long idProducto) {
        productoService.eliminar(idProducto);
        System.out.println("Producto Eliminado con Exito");
        return "redirect:/views/productos/";
    }
}
