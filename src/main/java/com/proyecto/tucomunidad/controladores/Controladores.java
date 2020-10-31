package com.proyecto.tucomunidad.controladores;

import com.proyecto.tucomunidad.Errores.ErrorServicio;
import com.proyecto.tucomunidad.Servicios.ComunidadService;
import com.proyecto.tucomunidad.Servicios.UsuarioService;
import com.proyecto.tucomunidad.Servicios.ViviendaService;
import com.proyecto.tucomunidad.entidades.Comunidad;
import com.proyecto.tucomunidad.entidades.Usuario;
import com.proyecto.tucomunidad.entidades.Vivienda;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class Controladores {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ViviendaService viviendaService;

    @Autowired
    ComunidadService comunidadService;

    @GetMapping("/")
    public String index() {

        return "index.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {

        if (error != null) {
            model.put("error", "Mail o clave incorrectos.");
            System.out.println(error.toString());
        }

        if (logout != null) {
            model.put("logout", "Sesión cerrada.");
        }

        return "login.html";
    }

    @GetMapping("/registro")
    public String registro(ModelMap modelo) {

        modelo.put("eleccion", "si");
        modelo.put("comunidad", "si");
        modelo.put("vivienda", null);

        List<Comunidad> comunidad = comunidadService.listarComunidades();
        modelo.put("comunidades", comunidad);

        return "registro.html";
    }

    @GetMapping("/registro/{idComunidad}")
    public String registro(ModelMap modelo, @PathVariable String idComunidad) {
        modelo.put("eleccion", "si");
        modelo.put("comunidad", null);
        modelo.put("vivienda", "si");

        List<Vivienda> vivienda = viviendaService.listarViviendasPorComunidad(idComunidad);
        modelo.put("viviendas", vivienda);

        return "registro.html";
    }

    @PostMapping("/registro")
    public String registro(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, 
            @RequestParam String telefono, @RequestParam String idVivienda, @RequestParam String claveVivienda, @RequestParam String clave1, 
            @RequestParam String clave2, MultipartFile foto) {

        try {

            usuarioService.registrar(mail, nombre, apellido, clave1, clave2, idVivienda, claveVivienda, telefono, foto);

        } catch (ErrorServicio ex) {
            Logger.getLogger(Controladores.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("mail", mail);
            modelo.put("telefono", telefono);
            modelo.put("vivienda", idVivienda);
            modelo.put("claveVivienda", claveVivienda);
            modelo.put("clave1", clave1);
            modelo.put("clave2", clave2);
            modelo.put("foto", foto);
        }

        return "index.html";

    }

    
    
    
    @GetMapping("/registro/editarPerfil/{mail}")
    public String editarPerfil(ModelMap modelo, @PathVariable String mail) {

        try {
            Usuario u = usuarioService.buscarPorId(mail);
            modelo.put("usuario", u);

        } catch (ErrorServicio ex) {
            Logger.getLogger(Controladores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "registro.html";
    }

    @PostMapping("/registro/editarPerfil")
    public String enviarDatos(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String telefono, @RequestParam String idVivienda, @RequestParam String clave1, @RequestParam String clave2, MultipartFile foto) {

        try {
            usuarioService.modificar(mail, nombre, apellido, clave1, clave2, telefono, foto);

        } catch (ErrorServicio ex) {
            Logger.getLogger(Controladores.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("mail", mail);
            modelo.put("telefono", telefono);
            modelo.put("clave", clave1);
            modelo.put("repetir clave", clave2);
            modelo.put("foto", foto);
        }

        return "exito_registro.html";
    }
}
