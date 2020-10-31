
package com.proyecto.tucomunidad.controladores;

import com.proyecto.tucomunidad.Errores.ErrorServicio;
import com.proyecto.tucomunidad.Servicios.UsuarioService;
import com.proyecto.tucomunidad.entidades.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/")
public class usuarioControlador {
    
    @Autowired
    UsuarioService usuarioService;
    
     @GetMapping("/usuario_lista/{mail}")
    public String usuarioLista(ModelMap modelo, @PathVariable String mail) throws ErrorServicio {

        List<Usuario> usuarios = usuarioService.listarUsuariosComunidad(mail);
        modelo.put("usuarios", usuarios);

        return "usuarios.html";
    }

<<<<<<< HEAD
    @GetMapping("/usuario_lista/usuario_eliminar/{mail}")
=======
    @GetMapping("/usuario_lista/usuario_eliminar/{id}")
>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244
    public String usuarioEliminar(ModelMap modelo, @PathVariable String mail) {

        try {
            usuarioService.darBaja(mail);
            List<Usuario> usuarios = usuarioService.listarUsuariosComunidad(mail);
            modelo.put("usuarios", usuarios);

        } catch (ErrorServicio ex) {
            Logger.getLogger(Controladores.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            return "usuarios_lista.html";
        }

        return "usuarios_lista.html";
    }
    
<<<<<<< HEAD
     @GetMapping("/usuario_lista/usuario_update/{mail}")
    public String usuarioUpdate(ModelMap modelo, @PathVariable String mail) {

        try {
            usuarioService.asignarAdmin(mail);
            List<Usuario> usuarios = usuarioService.listarUsuariosComunidad(mail);
            modelo.put("usuarios", usuarios);

        } catch (ErrorServicio ex) {
            Logger.getLogger(Controladores.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            return "usuarios_lista.html";
        }

        return "usuarios_lista.html";
    }
    
    @GetMapping("/usuario_lista/usuario_ver/{mail}")
    public String usuarioMasInfo(ModelMap modelo, @PathVariable String mail) {
        
        modelo.put("ver", "si");
        modelo.put("crear", null);
        
        try {
            Usuario usuario;
            usuario = usuarioService.buscarPorId(mail);
            
            modelo.put("usuario", usuario);
            
        } catch (ErrorServicio ex) {
            Logger.getLogger(viviendaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return "usuario.html";
    }
=======
    
>>>>>>> 7c7c52edc2e4621bcdeeb98180a82e4ca58e2244
    
}
