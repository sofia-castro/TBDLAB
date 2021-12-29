package cl.tbd.ejemplo1.services;
import org.springframework.web.bind.annotation.*;
import cl.tbd.ejemplo1.repositories.Eme_HabilidadRepository;
import cl.tbd.ejemplo1.models.Eme_Habilidad;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@CrossOrigin
@RestController
public class Eme_HabilidadService {
    private final Eme_HabilidadRepository eme_habilidadRepository;
    private final Gson gson;

    Eme_HabilidadService(Eme_HabilidadRepository eme_habilidadRepository){
        this.gson = new GsonBuilder().create();
        this.eme_habilidadRepository = eme_habilidadRepository;
    }

    @GetMapping("/eme_habilidades/")
    ResponseEntity<String> getEme_Habilidades(){
        List<Eme_Habilidad> eme_habilidad = eme_habilidadRepository.getEme_Habilidades();
        return new ResponseEntity<>(gson.toJson(eme_habilidad),HttpStatus.OK);
    }

    @GetMapping("/eme_habilidades/{id}")
    ResponseEntity<String> getEme_Habilidades(@PathVariable Long id){
        Eme_Habilidad eme_habilidad = eme_habilidadRepository.getEme_Habilidad(id);
        if(eme_habilidad != null){
            return new ResponseEntity<>(gson.toJson(eme_habilidad),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/eme_habilidades/create")
    ResponseEntity<String> createEme_Habilidad(@RequestBody String request){
        Eme_Habilidad eme_habilidadOut = gson.fromJson(request,Eme_Habilidad.class);
        if (eme_habilidadOut != null){
            eme_habilidadOut = eme_habilidadRepository.createEme_Habilidad(eme_habilidadOut);
            return new ResponseEntity<>(gson.toJson(eme_habilidadOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/eme_habilidades/{id}")
    ResponseEntity<String> updateEme_Habilidad(@RequestBody Eme_Habilidad request, @PathVariable Long id){
        Eme_Habilidad eme_habilidadOut = eme_habilidadRepository.getEme_Habilidad(id);
        if(eme_habilidadOut != null){
            if(request.getIdEmergencia() != null){
                eme_habilidadOut.setIdEmergencia(request.getIdEmergencia());
            }
            if(request.getIdHabilidad() != null){
                eme_habilidadOut.setIdHabilidad(request.getIdHabilidad());
            }

            eme_habilidadOut = eme_habilidadRepository.updateEme_Habilidad(eme_habilidadOut, id);
            return new ResponseEntity<>(gson.toJson(eme_habilidadOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/eme_habilidades/{id}")
    ResponseEntity<String> deleteEme_Habilidad(@PathVariable Long id){
        if(eme_habilidadRepository.deleteEme_Habilidad(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
