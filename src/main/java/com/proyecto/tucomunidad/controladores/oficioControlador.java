/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.tucomunidad.controladores;

import com.proyecto.tucomunidad.Enumeraciones.Categoria;
import com.proyecto.tucomunidad.Errores.ErrorServicio;
import com.proyecto.tucomunidad.Servicios.OficioService;
import com.proyecto.tucomunidad.Servicios.ValoracionService;
import com.proyecto.tucomunidad.entidades.Comentario;
import com.proyecto.tucomunidad.entidades.Oficio;
import com.proyecto.tucomunidad.entidades.Valoracion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Carlos Brondo
 */
@Controller
<<<<<<< HEAD
//@PreAuthorize("hasAnyRole('ROLE_REGULAR')")
@RequestMapping("/")
=======
@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@RequestMapping("/oficio/{idcomunidad}")
>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244
public class oficioControlador {

    @Autowired
    OficioService oficioService;

    @Autowired
    ValoracionService valoracionService;

<<<<<<< HEAD
    @GetMapping("/oficio/{idcomunidad}")
=======
    @GetMapping("/")
>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244
    public String mostrar(ModelMap modelo, @PathVariable String idcomunidad, HttpSession session) {
        modelo.put("session", session);
        modelo.put("idcomunidad", idcomunidad);

        List<Oficio> lista;
        try {
            lista = oficioService.buscarOficiosPorComunidad(idcomunidad);
            modelo.put("lista", lista);

        } catch (ErrorServicio ex) {
            Logger.getLogger(oficioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        modelo.put("vertodos", "si");
        modelo.put("crear", null);
        modelo.put("ver1", null);
        modelo.put("valorar", null);

        return "oficio.html";
    }

<<<<<<< HEAD
    @GetMapping("/oficio/{idcomunidad}/masinfo/{idoficio}")
=======
    @GetMapping("/masinfo/{idoficio}")
>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244
    public String masinfo(ModelMap modelo, @PathVariable String idcomunidad,
            @PathVariable String idoficio, HttpSession session) {
        modelo.put("session", session);
        modelo.put("idcomunidad", idcomunidad);

        Oficio oficio;
        try {
            oficio = oficioService.buscarPorId(idoficio);
            modelo.put("oficio", oficio);
            List<Valoracion> valoracion = oficio.getValoraciones();
            modelo.put("valoracion", valoracion);
<<<<<<< HEAD
            Integer promedio = oficioService.promedioValoracion(idoficio);
            modelo.put("promedio", promedio);
=======

>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244
        } catch (ErrorServicio ex) {
            Logger.getLogger(oficioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        modelo.put("vertodos", null);
        modelo.put("crear", null);
        modelo.put("ver1", "si");
        modelo.put("valorar", null);

        return "oficio.html";
    }

<<<<<<< HEAD
    @GetMapping("/oficio/{idcomunidad}/valorar/{idoficio}")
=======
    @GetMapping("/valorar/{idoficio}")
>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244
    public String valorar(ModelMap modelo, @PathVariable String idcomunidad,
            @PathVariable String idoficio, HttpSession session) {
        modelo.put("session", session);
        modelo.put("idcomunidad", idcomunidad);

        Oficio oficio;
        try {
            oficio = oficioService.buscarPorId(idoficio);
            modelo.put("oficio", oficio);
<<<<<<< HEAD
            Integer promedio = oficioService.promedioValoracion(idoficio);
            modelo.put("promedio", promedio);
=======
>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244
        } catch (ErrorServicio ex) {
            Logger.getLogger(oficioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        modelo.put("vertodos", null);
        modelo.put("crear", null);
        modelo.put("ver1", null);
        modelo.put("valorar", "si");

        return "oficio.html";
    }

<<<<<<< HEAD
    @PostMapping("/oficio/{idcomunidad}/valorando")
=======
    @PostMapping("/valorando")
>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244
    public String agregando(ModelMap modelo, @PathVariable String idcomunidad, @RequestParam String idoficio,
            @RequestParam String mail, @RequestParam String comentario, @RequestParam Integer calificacion, HttpSession session) throws ErrorServicio {
        try {
            modelo.put("idcomunidad", idcomunidad);
            Valoracion valoracion = valoracionService.crear(mail, calificacion, comentario);
            oficioService.agregarValoracion(idoficio, valoracion);

        } catch (ErrorServicio ex) {
<<<<<<< HEAD
            System.out.println(ex.toString());
            return "oficio.html";
        }

        return mostrar(modelo, idcomunidad, session);
    }

    @GetMapping("/oficio/{idcomunidad}/crear")
=======

            return "oficio.html";
        }

        modelo.put("session", session);
        modelo.put("idcomunidad", idcomunidad);

        List<Oficio> lista = oficioService.buscarOficiosPorComunidad(idcomunidad);
        modelo.put("lista", lista);

        modelo.put("vertodos", "si");
        modelo.put("crear", null);
        modelo.put("ver1", null);
        modelo.put("valorar", null);

        return "oficio.html";
    }

    @GetMapping("/crear")
>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244
    public String crear(ModelMap modelo, @PathVariable String idcomunidad,
            HttpSession session) {
        modelo.put("session", session);
        modelo.put("idcomunidad", idcomunidad);

        modelo.put("vertodos", null);
        modelo.put("crear", "si");
        modelo.put("ver1", null);
        modelo.put("valorar", null);

        return "oficio.html";
    }

<<<<<<< HEAD
    @PostMapping("/oficio/{idcomunidad}/creando")
    public String creando(ModelMap modelo, @PathVariable String idcomunidad,
            HttpSession session, @RequestParam String nombre, @RequestParam String contacto, @RequestParam Categoria categoria) {

        try {
            oficioService.crear(categoria, nombre, contacto);

            modelo.put("session", session);
            modelo.put("idcomunidad", idcomunidad);

            List<Oficio> lista;
=======
      @PostMapping("/creando")
    public String creando(ModelMap modelo, @PathVariable String idcomunidad,
            HttpSession session, @RequestParam String nombre, @RequestParam String contacto, @RequestParam Categoria cat) {

        try {
            oficioService.crear(cat, nombre, contacto);
        } catch (ErrorServicio ex) {
            Logger.getLogger(oficioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        modelo.put("session", session);
        modelo.put("idcomunidad", idcomunidad);

        List<Oficio> lista;
        try {
>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244
            lista = oficioService.buscarOficiosPorComunidad(idcomunidad);
            modelo.put("lista", lista);

        } catch (ErrorServicio ex) {
            Logger.getLogger(oficioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
<<<<<<< HEAD
        // return "redirect:/";
        return mostrar(modelo, idcomunidad, session);

    }

    @GetMapping("/oficio/{idcomunidad}/eliminar/{idoficio}")
=======

        modelo.put("vertodos", "si");
        modelo.put("crear", null);
        modelo.put("ver1", null);
        modelo.put("valorar", null);
        return "oficio.html";
    }

    @GetMapping("/eliminar/{idoficio}")
>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244
    public String eliminar(ModelMap modelo, @PathVariable String idcomunidad, HttpSession session, @PathVariable String idoficio) {
        modelo.put("session", session);
        modelo.put("idcomunidad", idcomunidad);
        try {
            oficioService.darBaja(idoficio);
        } catch (ErrorServicio ex) {
            Logger.getLogger(oficioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Oficio> lista;
        try {
            lista = oficioService.buscarOficiosPorComunidad(idcomunidad);
            modelo.put("lista", lista);

        } catch (ErrorServicio ex) {
            Logger.getLogger(oficioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        modelo.put("vertodos", "si");
        modelo.put("crear", null);
        modelo.put("ver1", null);
        modelo.put("valorar", null);

        return "oficio.html";
    }
}
