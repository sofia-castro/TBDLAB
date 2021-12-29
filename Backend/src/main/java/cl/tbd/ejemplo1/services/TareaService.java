package cl.tbd.ejemplo1.services;
import cl.tbd.ejemplo1.repositories.TareaRepository;
import cl.tbd.ejemplo1.models.Tarea;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin
@RestController
public class TareaService {
    private final TareaRepository tareaRepository;
    private final Gson gson;

    TareaService(TareaRepository tareaRepository){
        this.gson = new GsonBuilder().create();
        this.tareaRepository = tareaRepository;
    }

    @GetMapping("/tareas/")
    ResponseEntity<String> getTareas(){
        List<Tarea> Voluntarioses = tareaRepository.getTareas() ;
        return new ResponseEntity<>(gson.toJson(Voluntarioses),HttpStatus.OK);
    }

    @GetMapping("/tareas/{id}")
    ResponseEntity<String> getTareas(@PathVariable Long id){
        Tarea tareas = tareaRepository.getTarea(id);
        if(tareas != null){
            return new ResponseEntity<>(gson.toJson(tareas),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/tareas/create")
    ResponseEntity<String> createTarea(@RequestBody String request){
        Tarea tarOut = gson.fromJson(request,Tarea.class);
        if (tarOut != null){
            tarOut = tareaRepository.createTarea(tarOut);
            return new ResponseEntity<>(gson.toJson(tarOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/tareas/{id}")
    ResponseEntity<String> updateTarea(@RequestBody Tarea request, @PathVariable Long id){
        Tarea tarOut = tareaRepository.getTarea(id);
        if(tarOut != null){
            if(request.getCant_Voluntarios() != null){
                tarOut.setCant_Voluntarios(request.getCant_Voluntarios());
            }

            if(request.getTitulo() != null){
                tarOut.setTitulo(request.getTitulo());
            }

            if(request.getDescripcion() != null){
                tarOut.setDescripcion(request.getDescripcion());
            }
            if(request.getEmergenciaId() != null){
                tarOut.setEmergenciaId(request.getEmergenciaId());
            }
            if(request.getEstadoId() != null){
                tarOut.setEstadoId(request.getEstadoId());
            }
            tarOut = tareaRepository.updateTarea(tarOut, id);
            return new ResponseEntity<>(gson.toJson(tarOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/tareas/{id}")
    ResponseEntity<String> deleteTarea(@PathVariable Long id){
        if(tareaRepository.deleteTarea(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
