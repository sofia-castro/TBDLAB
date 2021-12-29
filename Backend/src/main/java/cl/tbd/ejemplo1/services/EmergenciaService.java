package cl.tbd.ejemplo1.services;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cl.tbd.ejemplo1.repositories.EmergenciaRepository;
import cl.tbd.ejemplo1.models.Emergencia;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class EmergenciaService {
    
    private final EmergenciaRepository emergenciaRepository;
    private final Gson gson;

    EmergenciaService(EmergenciaRepository emergenciaRepository){
        this.gson = new GsonBuilder().create();
        this.emergenciaRepository = emergenciaRepository;
    }

    @GetMapping("/emergencias/")
    ResponseEntity<String> getEmergencias(){
        List<Emergencia> emergencias = emergenciaRepository.getEmergencias();
        return new ResponseEntity<>(gson.toJson(emergencias),HttpStatus.OK);
    }

    @GetMapping("/emergencias/{id}")
    ResponseEntity<String> getEmergencia(@PathVariable Long id){
        Emergencia emergencias = emergenciaRepository.getEmergencia(id);
        if(emergencias != null){
            return new ResponseEntity<>(gson.toJson(emergencias),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/emergencias/create")
    ResponseEntity<String> createEmergencia(@RequestBody String request){
        Emergencia emergenciasOut = gson.fromJson(request,Emergencia.class);
        if (emergenciasOut != null){
            emergenciasOut = emergenciaRepository.createEmergencia(emergenciasOut);
            return new ResponseEntity<>(gson.toJson(emergenciasOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/emergencias/{id}")
    ResponseEntity<String> updateEmergencia(@RequestBody Emergencia request, @PathVariable Long id){
        Emergencia emergenciasOut = emergenciaRepository.getEmergencia(id);
        if(emergenciasOut != null){
            if(request.getTitulo() != null){
                emergenciasOut.setTitulo(request.getTitulo());
            }
            if(request.getDireccion() != null){
                emergenciasOut.setDireccion(request.getDireccion());
            }
            if(request.getDescripcion() != null){
                emergenciasOut.setDescripcion(request.getDescripcion());
            }
            if(request.getInstitucionId() != null){
                emergenciasOut.setInstitucionId(request.getInstitucionId());
            }
            if(request.getActivo() != null){
                emergenciasOut.setActivo(request.getActivo());
            }

            emergenciasOut = emergenciaRepository.updateEmergencia(emergenciasOut, id);
            return new ResponseEntity<>(gson.toJson(emergenciasOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/emergencias/{id}")
    ResponseEntity<String> deleteEmergencia(@PathVariable Long id){
        if(emergenciaRepository.deleteEmergencia(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
