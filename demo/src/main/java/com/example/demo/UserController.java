package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/pruebas")
    public String pruebas(Model model) {

        // LE PASAMOS UN MODELO ( DATOS ) AL TEMPLATE
        model.addAttribute("title", "Título");
        model.addAttribute("nombre", "Joel");
        model.addAttribute("apellido", "Lopez");

        // NOMBRE DEL FICHERO/PLANTILLA WEB QUE SE ABRIRÁ EN EL NAVEGADOR
        return "pruebas";
    }

    @GetMapping("/contacto")
    public String contacto(Model model) {
        model.addAttribute("mensaje", "hola desde spring mvc");
        return "contacto";
    }

    // Paso 2.1: un único objeto en el modelo
    @GetMapping("/producto")
    public String verProducto(Model model) {
        model.addAttribute("objeto", new Producto(1, "Ordenador", 200));
        return "producto";
    }

    // Paso 2.2: lista de objetos en el modelo
    @GetMapping("/productos")
    public String mostrarProductos(Model model) {
        List<Producto> miLista = new ArrayList<>();
        miLista.add(new Producto(1, "Patinete", 300));
        miLista.add(new Producto(2, "Bicicleta", 1500));
        miLista.add(new Producto(3, "Ruedines", 30));
        miLista.add(new Producto(4, "Skate", 120.99f));

        model.addAttribute("productos", miLista);
        return "ver_productos";
    }
}