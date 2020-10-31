
package com.proyecto.tucomunidad.controladores;

import com.proyecto.tucomunidad.Enumeraciones.ViviendaTipo;
import com.proyecto.tucomunidad.Errores.ErrorServicio;
import com.proyecto.tucomunidad.Servicios.ViviendaService;
import com.proyecto.tucomunidad.entidades.Vivienda;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
//@PreAuthorize("hasAnyRole('ROLE_ADMIN_REGISTRADO')")
@RequestMapping("/")
public class viviendaControlador {
    
    @Autowired
    ViviendaService viviendaService;
    
     @GetMapping("/viviendas/mostrar/{idcomunidad}")
    public String vivienda(ModelMap modelo, @PathVariable String idcomunidad) throws ErrorServicio{

        List<Vivienda> viviendas = viviendaService.listarViviendasPorComunidad(idcomunidad);
        modelo.put("viviendas", viviendas);
        
        
        return "viviendas.html";
    }
    
    @GetMapping("/viviendas-crear/{idComunidad}")
    public String viviendaCrear(ModelMap modelo, @PathVariable String idComunidad) {
        
        modelo.put("idComunidad", idComunidad);
        
        modelo.put("crear", "si");
        modelo.put("masInfo", null);
        
        return "viviendas.html";

    }
    
    @PostMapping("/viviendas-creando")
    public String viviendaCrear2(@RequestParam String idComunidad, @RequestParam String direccion, @RequestParam String claveVivienda, @RequestParam ViviendaTipo tipo, @RequestParam boolean mascota, @RequestParam boolean duenoHabita, @RequestParam Integer numeroHabitantes, MultipartFile foto) {
 
        try { 
            viviendaService.registrar(claveVivienda, direccion, idComunidad, tipo, foto, mascota, duenoHabita, numeroHabitantes);
        } catch (ErrorServicio ex) {
            Logger.getLogger(viviendaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
            
           return "viviendas-exito.html";
}
    
    @GetMapping("viviendas_masinfo/{viviendaid}")
    public String viviendaMasInfo(ModelMap modelo, @PathVariable String viviendaid) {
        
        modelo.put("masInfo", "si");
        modelo.put("crear", null);
        
        try {
            Vivienda vivienda;
            vivienda = viviendaService.buscarPorId(viviendaid);
            
            modelo.put("vivienda", vivienda);
            
        } catch (ErrorServicio ex) {
            Logger.getLogger(viviendaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return "viviendas.html";
    }
}
