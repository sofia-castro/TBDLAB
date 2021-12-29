package cl.tbd.ejemplo1.services;
import cl.tbd.ejemplo1.repositories.Tarea_HabilidadRepository;
import cl.tbd.ejemplo1.models.Tarea_Habilidad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@CrossOrigin
@RestController
public class Tarea_HabilidadService {
    private final Tarea_HabilidadRepository tarea_habilidadRepository;
    private final Gson gson;

    Tarea_HabilidadService(Tarea_HabilidadRepository tarea_habilidadRepository){
        this.gson = new GsonBuilder().create();
        this.tarea_habilidadRepository= tarea_habilidadRepository;
    }

    @GetMapping("/tareas_habilidades/")
    ResponseEntity<String> getAllTarea_Habilidad(){
        List<Tarea_Habilidad> tareas_habilidad =  tarea_habilidadRepository.getAllTarea_Habilidad() ;
        return new ResponseEntity<>(gson.toJson(tareas_habilidad),HttpStatus.OK);
    }

    @GetMapping("/tareas_habilidades/{id}")
    ResponseEntity<String> getTarea_Habilidad(@PathVariable Long id){
        Tarea_Habilidad tareas =  tarea_habilidadRepository.getTarea_Habilidad(id);
        if(tareas != null){
            return new ResponseEntity<>(gson.toJson(tareas),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/tareas_habilidades/create")
    ResponseEntity<String> createTarea_Habilidad(@RequestBody String request){
        Tarea_Habilidad tarOut = gson.fromJson(request,Tarea_Habilidad.class);
        if (tarOut != null){
            tarOut =  tarea_habilidadRepository.createTarea_Habilidad(tarOut);
            return new ResponseEntity<>(gson.toJson(tarOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/tarea_habilidades/{id}")
    ResponseEntity<String> updateTarea_Habilidad(@RequestBody Tarea_Habilidad request, @PathVariable Long id){
        Tarea_Habilidad tarOut =  tarea_habilidadRepository.getTarea_Habilidad(id);
        if(tarOut != null){
            if(request.getTareaId() != null){
                tarOut.setTareaId(request.getTareaId());
            }
            if(request.getEmeHabilidadId() != null){
                tarOut.setEmeHabilidadId(request.getEmeHabilidadId());
            }

            
            tarOut =  tarea_habilidadRepository.updateTarea_Habilidad(tarOut, id);
            return new ResponseEntity<>(gson.toJson(tarOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/tareas_habilidades/{id}")
    ResponseEntity<String> deleteTarea_Habilidad(@PathVariable Long id){
        if( tarea_habilidadRepository.deleteTarea_Habilidad(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
