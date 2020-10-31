package com.proyecto.tucomunidad.controladores;

import com.proyecto.tucomunidad.Errores.ErrorServicio;
import com.proyecto.tucomunidad.Servicios.ComunidadService;
import com.proyecto.tucomunidad.Servicios.HitoService;
import com.proyecto.tucomunidad.Servicios.ProyectService;
import com.proyecto.tucomunidad.Servicios.UsuarioService;
import com.proyecto.tucomunidad.entidades.Comunidad;
import com.proyecto.tucomunidad.entidades.Hito;
import com.proyecto.tucomunidad.entidades.Proyecto;
import com.proyecto.tucomunidad.entidades.Usuario;
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
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("hasRole('ROLE_REGULAR')")
@Controller
public class logueadoControlador {

    @Autowired
    ProyectService proyectoService;

    @Autowired
    ComunidadService comunidadService;

    @Autowired
    HitoService hitoService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/logueado")
    public String ingreso(ModelMap modelo, HttpSession session) throws ErrorServicio {

        try {

            modelo.put("session", session);

            Usuario usuario = usuarioService.getUsuario();

            Comunidad comunidad = comunidadService.buscarPorId(usuario.getIdcomunidad());

            modelo.put("info", "si");
            modelo.put("crear", null);
            modelo.put("comunidad", comunidad);

            List<Proyecto> listaProyectos = proyectoService.buscarProyectosPorComunidad(usuario.getIdcomunidad());
            modelo.put("listaproyectos", listaProyectos);

            List<Hito> listaHitos = hitoService.buscarHitosPorComunidad(usuario.getIdcomunidad());
            modelo.put("listahitos", listaHitos);

        } catch (ErrorServicio ex) {
            Logger.getLogger(Controladores.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "logueado.html";
    }

}
