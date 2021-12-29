package cl.tbd.ejemplo1.services;
import cl.tbd.ejemplo1.repositories.HabilidadRepository;
import cl.tbd.ejemplo1.models.Habilidad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin
@RestController

public class HabilidadService {

    private final HabilidadRepository habilidadRepository;
    private final Gson gson;

    HabilidadService(HabilidadRepository habilidadRepository){
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.habilidadRepository = habilidadRepository;
    }

    @GetMapping("/habilidades/")
    ResponseEntity<String> getHabilidades(){
        List<Habilidad> habilidades = habilidadRepository.getHabilidades();
        return ResponseEntity.status(200).body("Habilidades:\n" + gson.toJson(habilidades));
    }

    @GetMapping("/habilidades/{id}")
    ResponseEntity<String> getHabilidad(@PathVariable Long id){
        Habilidad habilidad = habilidadRepository.getHabilidad(id);
        if(habilidad != null){
            return ResponseEntity.status(200).body("Habilidad:\n" + gson.toJson(habilidad));
        }
        return ResponseEntity.status(404).body("No se encontro la habilidad objetivo :(\n");
    }

    @PostMapping("/habilidades/create")
    ResponseEntity<String> createHabilidad(@RequestBody String request){
        Habilidad habilidadOut = gson.fromJson(request,Habilidad.class);
        if (habilidadOut != null){  
            habilidadOut = habilidadRepository.createHabilidad(habilidadOut);
            return ResponseEntity.status(200).body("CREADO\n" + gson.toJson(habilidadOut));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/habilidades/{id}")
    ResponseEntity<String> updateHabilidad(@RequestBody Habilidad request, @PathVariable Long id){
        Habilidad habilidadOut = habilidadRepository.getHabilidad(id);
        if(habilidadOut != null){
            if(request.getNombreH() != null){
                habilidadOut.setNombreH(request.getNombreH());
            }

            if(request.getDescripcion() != null){
                habilidadOut.setDescripcion(request.getDescripcion());
            }

            habilidadOut = habilidadRepository.updateHabilidad(habilidadOut, id);
            return ResponseEntity.status(200).body("ACTUALIZADO\n" + gson.toJson(habilidadOut));
        }
        return ResponseEntity.status(404).body("No se encontro la habilidad objetivo :(\n");
    }

    @DeleteMapping("/habilidades/{id}")
    ResponseEntity<String> deleteHabilidad(@PathVariable Long id){
        if(habilidadRepository.deleteHabilidad(id)){
            return ResponseEntity.status(200).body("SOFTDELETEADO\n");
        }
        return ResponseEntity.status(404).body("No se encontro la habilidad objetivo :(\n");
    }


}

