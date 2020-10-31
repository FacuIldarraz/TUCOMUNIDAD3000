
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

    @GetMapping("/usuario_lista/usuario_eliminar/{id}")
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
    
    
    
}
