package com.proyecto.tucomunidad.controladores;

import com.proyecto.tucomunidad.Errores.ErrorServicio;
import com.proyecto.tucomunidad.Servicios.ComentarioService;
import com.proyecto.tucomunidad.Servicios.FotoService;
import com.proyecto.tucomunidad.Servicios.ProyectService;
import com.proyecto.tucomunidad.Servicios.UsuarioService;
<<<<<<< HEAD
=======
import com.proyecto.tucomunidad.Servicios.VotacionService;
>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244
import com.proyecto.tucomunidad.entidades.Comentario;
import com.proyecto.tucomunidad.entidades.Foto;
import com.proyecto.tucomunidad.entidades.Proyecto;
import com.proyecto.tucomunidad.entidades.Usuario;
import java.util.ArrayList;
import java.util.Date;
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

@Controller
//@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@RequestMapping("/proyecto/{idcomunidad}")
public class proyectoControlador {

    @Autowired
    ProyectService proyectoService;
    @Autowired
    FotoService fotoService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ComentarioService comentarioService;
<<<<<<< HEAD
=======
    @Autowired
    VotacionService votacionService;
>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244

    @GetMapping("/mostrar")
    public String mostrar(ModelMap modelo, @PathVariable String idcomunidad, HttpSession session) {
        modelo.put("session", session);

        try {

            List<Proyecto> lista = proyectoService.buscarProyectosPorComunidad(idcomunidad);
            int i = 1;
            for (Proyecto proyecto : lista) {
                switch (i) {
                    case 1:
                        modelo.put("proyecto1", proyecto);
                        break;
                    case 2:
                        modelo.put("proyecto2", proyecto);
                        break;
                    case 3:
                        modelo.put("proyecto3", proyecto);
                        break;
                }
                i++;
            }
            modelo.put("lista", lista);
<<<<<<< HEAD

=======
            for (Proyecto p : lista) {
                proyectoService.estadoIniciado(p.getId());
                proyectoService.estadoFinalizado(p.getId());
                votacionService.cambiarResultado(p.getId());
            }
>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244
            modelo.put("vertodos", "si");
            modelo.put("crear", null);
            modelo.put("editar", null);
            modelo.put("ver1", null);
            modelo.put("editar1", null);

        } catch (ErrorServicio ex) {

        }
        return "proyecto.html";
    }

    @GetMapping("/masinfo/{idproyecto}")
    public String masinfo(ModelMap modelo, @PathVariable String idcomunidad,
            @PathVariable String idproyecto, HttpSession session) {
        modelo.put("session", session);
        Proyecto proyecto;
        try {
            proyecto = proyectoService.buscarPorId(idproyecto);
            modelo.put("proyecto", proyecto);
<<<<<<< HEAD
=======
        } catch (ErrorServicio ex) {
            Logger.getLogger(proyectoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244
            List<Comentario> comentarios = proyectoService.obtenerComentarioDeProyecto(idproyecto);

            modelo.put("comentarios", comentarios);

<<<<<<< HEAD
            Integer progreso;

            progreso = proyectoService.progresoProyecto(idproyecto);
            modelo.put("progreso", progreso);
            Integer votosfavor = proyectoService.cantidadVotos(idproyecto);
            modelo.put("votosfavor", votosfavor);
            
            
            
            List<Foto> fotoprogreso;

            fotoprogreso = proyectoService.buscarPorId(idproyecto).getFotoProgreso();
            modelo.put("fotoprogreso", fotoprogreso);
            
            
=======
        } catch (ErrorServicio ex) {
            Logger.getLogger(proyectoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        Integer progreso;
        try {
            progreso = proyectoService.progresoProyecto(idproyecto);
            modelo.put("progreso", progreso);
            Integer votosfavor = proyectoService.cantidadVotos(idproyecto);

            modelo.put("votosfavor", votosfavor);
        } catch (ErrorServicio ex) {
            Logger.getLogger(proyectoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Foto> fotoprogreso;
        try {
            Integer dias=proyectoService.progresoProyecto(idproyecto);
            modelo.put("dias",dias);
            fotoprogreso = proyectoService.buscarPorId(idproyecto).getFotoProgreso();
            modelo.put("fotoprogreso", fotoprogreso);
>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244
        } catch (ErrorServicio ex) {
            Logger.getLogger(proyectoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        modelo.put("vertodos", null);
        modelo.put("crear", null);
        modelo.put("editar", null);
        modelo.put("ver1", "si");
        modelo.put("editar1", null);
        return "proyecto.html";
    }

    @GetMapping("/crear")
    public String crear(ModelMap modelo, @PathVariable String idcomunidad, HttpSession session) {
        modelo.put("session", session);

        modelo.put("vertodos", null);
        modelo.put("crear", "si");
        modelo.put("editar", null);
        modelo.put("ver1", null);
        modelo.put("editar1", null);
        return "proyecto.html";
    }

    @PostMapping("/creando")
    public String creando(ModelMap modelo, MultipartFile foto, @RequestParam String nombre, @RequestParam String descripcion,
            @RequestParam Integer presupuesto, @RequestParam String fechainicio, @RequestParam String fechafin,
            @PathVariable String idcomunidad, @RequestParam String idresponsable, @RequestParam Integer quorum,
            HttpSession session, @RequestParam String fechainiciovot, @RequestParam String fechafinvot) {

        try {
            proyectoService.crearProyecto(foto, nombre, descripcion, fechainicio, fechafin, idresponsable, idcomunidad, presupuesto, fechainiciovot, fechafinvot, quorum);
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("presupuesto", presupuesto);
            modelo.put("descripcion", descripcion);
            modelo.put("fechainicio", fechainicio);
            modelo.put("fechafin", fechafin);
            modelo.put("fechainiciovot", fechainiciovot);
            modelo.put("fechafinvot", fechafinvot);
            modelo.put("foto", foto);
            modelo.put("idcomunidad", idcomunidad);

            modelo.put("session", session);
            modelo.put("vertodos", null);
            modelo.put("crear", "si");
            modelo.put("editar", null);
            modelo.put("ver1", null);

            return "proyecto.html";
        }
        modelo.put("session", session);

        try {

            List<Proyecto> lista = proyectoService.buscarProyectosPorComunidad(idcomunidad);
            int i = 1;
            for (Proyecto proyecto : lista) {
                switch (i) {
                    case 1:
                        modelo.put("proyecto1", proyecto);
                        break;
                    case 2:
                        modelo.put("proyecto2", proyecto);
                        break;
                    case 3:
                        modelo.put("proyecto3", proyecto);
                        break;
                }
                i++;
            }
            modelo.put("lista", lista);
        } catch (ErrorServicio ex) {

        }
        modelo.put("session", session);
        modelo.put("vertodos", "si");
        modelo.put("crear", null);
        modelo.put("editar", null);
        modelo.put("ver1", null);
        modelo.put("editar1", null);

        return "proyecto.html";
    }

    @GetMapping("/editar")
    public String editar(ModelMap modelo, @PathVariable String idcomunidad, HttpSession session) {
        modelo.put("session", session);
        Usuario usuario;
        usuario = usuarioService.getUsuario();
        List<Proyecto> lista = proyectoService.ProyectoAdmin(usuario.getMail());

        modelo.put("lista", lista);
        modelo.put("vertodos", null);
        modelo.put("crear", null);
        modelo.put("editar", "si");
        modelo.put("ver1", null);
        modelo.put("editar1", null);

        return "proyecto.html";
    }

    @GetMapping("/editar/{idproyecto}")
    public String editar1(ModelMap modelo, @PathVariable String idcomunidad, HttpSession session, @PathVariable String idproyecto) {
        modelo.put("session", session);
        Proyecto proyecto;
        try {
            proyecto = proyectoService.buscarPorId(idproyecto);
            modelo.put("proyecto", proyecto);
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
        }

        modelo.put("vertodos", null);
        modelo.put("crear", null);
        modelo.put("editar", null);
        modelo.put("ver1", null);
        modelo.put("editar1", "si");
        return "proyecto.html";
    }

    @PostMapping("/editando")
    public String editando(ModelMap modelo, MultipartFile foto, @RequestParam String nombre, @RequestParam String descripcion,
            @RequestParam Double presupuesto, @RequestParam String fechainicio, @RequestParam String fechafin,
            @PathVariable String idcomunidad, @RequestParam String idresponsable,
            HttpSession session, @RequestParam String idproyecto) throws ErrorServicio {
        try {
            proyectoService.modificarProyecto(foto, nombre, descripcion, fechainicio, fechafin, idresponsable, idcomunidad, presupuesto, idproyecto);
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("presupuesto", presupuesto);
            modelo.put("descripcion", descripcion);
            modelo.put("fechainicio", fechainicio);
            modelo.put("fechafin", fechafin);
            modelo.put("idcomunidad", idresponsable);
            modelo.put("foto", foto);

            Proyecto proyecto = proyectoService.buscarPorId(idproyecto);
            modelo.put("proyecto", proyecto);
            modelo.put("idcomunidad", idcomunidad);

            modelo.put("session", session);
            modelo.put("vertodos", null);
            modelo.put("crear", "si");
            modelo.put("editar", null);
            modelo.put("ver1", null);
            System.out.println("Ayudaaaaa");
            return "proyecto.html";
        }
<<<<<<< HEAD
        
        
        try {

            List<Proyecto> lista = proyectoService.buscarProyectosPorComunidad(idcomunidad);
            int i = 1;
            for (Proyecto proyecto : lista) {
                switch (i) {
                    case 1:
                        modelo.put("proyecto1", proyecto);
                        break;
                    case 2:
                        modelo.put("proyecto2", proyecto);
                        break;
                    case 3:
                        modelo.put("proyecto3", proyecto);
                        break;
                }
                i++;
            }
            modelo.put("lista", lista);

            modelo.put("vertodos", "si");
            modelo.put("crear", null);
            modelo.put("editar", null);
            modelo.put("ver1", null);
            modelo.put("editar1", null);

        } catch (ErrorServicio ex) {

        }
=======

>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244
        List<Proyecto> lista = proyectoService.buscarProyectosPorComunidad(idcomunidad);
        modelo.put("lista", lista);

        modelo.put("session", session);
        modelo.put("vertodos", "si");
        modelo.put("crear", null);
        modelo.put("editar", null);
        modelo.put("ver1", null);
        modelo.put("editar1", null);

        return "proyecto.html";
    }

    @GetMapping("/eliminar/{idproyecto}")
    public String darbaja(ModelMap modelo, @PathVariable String idcomunidad, HttpSession session, @PathVariable String idproyecto) {
        modelo.put("session", session);
        try {
            proyectoService.darBaja(idproyecto);
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
        }

        Usuario usuario;
        usuario = usuarioService.getUsuario();
        List<Proyecto> lista = proyectoService.ProyectoAdmin(usuario.getMail());

        modelo.put("lista", lista);

        modelo.put("vertodos", null);
        modelo.put("crear", null);
        modelo.put("editar", "si");
        modelo.put("ver1", null);
        modelo.put("editar1", null);
        return "proyecto.html";
    }

    @GetMapping("/agregarinfo/{idproyecto}")
    public String agregar(ModelMap modelo, @PathVariable String idcomunidad, HttpSession session, @PathVariable String idproyecto) {
        modelo.put("session", session);

        modelo.put("vertodos", null);
        modelo.put("crear", null);
        modelo.put("editar", null);
        modelo.put("ver1", null);
        modelo.put("editar1", null);
        modelo.put("agregarinfo", "si");

        return "proyecto.html";
    }

    @PostMapping("/agregandoinfo")
    public String agregando(ModelMap modelo, MultipartFile foto, @PathVariable String idcomunidad, @RequestParam String idresponsable,
            HttpSession session, @RequestParam String idproyecto, @RequestParam String comentario) throws ErrorServicio {
        try {
            Comentario com = comentarioService.crearComentario(comentario);

            proyectoService.agregarFotos(foto, idproyecto);
            proyectoService.agregarComentario(idproyecto, com.getId());
        } catch (ErrorServicio ex) {

            return "proyecto.html";
        }

<<<<<<< HEAD
        try {

            List<Proyecto> lista = proyectoService.buscarProyectosPorComunidad(idcomunidad);
            int i = 1;
            for (Proyecto proyecto : lista) {
                switch (i) {
                    case 1:
                        modelo.put("proyecto1", proyecto);
                        break;
                    case 2:
                        modelo.put("proyecto2", proyecto);
                        break;
                    case 3:
                        modelo.put("proyecto3", proyecto);
                        break;
                }
                i++;
            }
            modelo.put("lista", lista);
        } catch (ErrorServicio ex) {

        }
=======
        List<Proyecto> lista = proyectoService.buscarProyectosPorComunidad(idcomunidad);
        modelo.put("lista", lista);
>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244

        modelo.put("session", session);
        modelo.put("vertodos", "si");
        modelo.put("crear", null);
        modelo.put("editar", null);
        modelo.put("ver1", null);
        modelo.put("editar1", null);

        return "proyecto.html";
    }

}
