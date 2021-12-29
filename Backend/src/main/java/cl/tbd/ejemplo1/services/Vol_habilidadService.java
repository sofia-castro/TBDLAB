package cl.tbd.ejemplo1.services;
import cl.tbd.ejemplo1.repositories.Vol_HabilidadRepository;
import cl.tbd.ejemplo1.models.Vol_habilidad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin
@RestController
public class Vol_habilidadService {

    private final Vol_HabilidadRepository vol_habilidadRepository;
    private final Gson gson;

    Vol_habilidadService(Vol_HabilidadRepository vol_habilidadRepository){
        this.gson = new GsonBuilder().create();
        this.vol_habilidadRepository = vol_habilidadRepository;
    }

    @GetMapping("/Vol_habilidad/")
    ResponseEntity<String> getVol_habilidad(){
        List<Vol_habilidad> Vol_habilidad = vol_habilidadRepository.getAllVol_habilidad() ;
        return new ResponseEntity<>(gson.toJson(Vol_habilidad),HttpStatus.OK);
    }

    @GetMapping("/Vol_habilidad/{id}")
    ResponseEntity<String> getVol_habilidad(@PathVariable Long id){
        Vol_habilidad Vol_habilidad = vol_habilidadRepository.getVol_habilidad(id);
        if(Vol_habilidad != null){
            return new ResponseEntity<>(gson.toJson(Vol_habilidad),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/Vol_habilidad/create")
    ResponseEntity<String> createVol_habilidad(@RequestBody String request){
        Vol_habilidad Vol_habilidadOut = gson.fromJson(request,Vol_habilidad.class);
        if (Vol_habilidadOut != null){
            Vol_habilidadOut = vol_habilidadRepository.createVol_habilidad(Vol_habilidadOut);
            return new ResponseEntity<>(gson.toJson(Vol_habilidadOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/vol_habilidad/{id}")
    ResponseEntity<String> updateVol_habilidad(@RequestBody Vol_habilidad request, @PathVariable Long id){
        Vol_habilidad Vol_habilidadOut = vol_habilidadRepository.getVol_habilidad(id);
        if(Vol_habilidadOut != null){
            if(request.getHabilidadId() != null){
                Vol_habilidadOut.setHabilidadId(request.getHabilidadId());
            }

            if(request.getVoluntarioId() != null){
                Vol_habilidadOut.setVoluntarioId(request.getVoluntarioId());
            }
            
            Vol_habilidadOut = vol_habilidadRepository.updateVol_habilidad(Vol_habilidadOut, id);
            return new ResponseEntity<>(gson.toJson(Vol_habilidadOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/vol_habilidades/{id}")
    ResponseEntity<String> deleteVol_habilidad(@PathVariable Long id){
        if(vol_habilidadRepository.deleteVol_habilidad(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
