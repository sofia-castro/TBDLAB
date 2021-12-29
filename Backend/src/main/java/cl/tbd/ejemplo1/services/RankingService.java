package cl.tbd.ejemplo1.services;
import org.springframework.web.bind.annotation.*;

import cl.tbd.ejemplo1.repositories.RankingRepository;
import cl.tbd.ejemplo1.models.Ranking;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;


@CrossOrigin
@RestController
public class RankingService {
    
    private final RankingRepository RankingRepository;
    private final Gson gson;

    RankingService(RankingRepository RankingRepository){
        this.gson = new GsonBuilder().create();
        this.RankingRepository = RankingRepository;
    }

    @GetMapping("/rankings/")
    ResponseEntity<String> getRankings(){
        List<Ranking> Ran = RankingRepository.getRankings() ;
        return new ResponseEntity<>(gson.toJson(Ran),HttpStatus.OK);
    }

    @GetMapping("/Ranking/{id}")
    ResponseEntity<String> getRanking(@PathVariable Long id){
        Ranking Ranking = RankingRepository.getRanking(id);
        if(Ranking != null){
            return new ResponseEntity<>(gson.toJson(Ranking),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/Ranking/create")
    ResponseEntity<String> createRanking(@RequestBody String request){
        Ranking RankingOut = gson.fromJson(request,Ranking.class);
        if (RankingOut != null){
            RankingOut = RankingRepository.createRanking(RankingOut);
            return new ResponseEntity<>(gson.toJson(RankingOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/Ranking/{id}")
    ResponseEntity<String> updateRanking(@RequestBody Ranking request, @PathVariable Long id){
        Ranking RankingOut = RankingRepository.getRanking(id);
        if(RankingOut != null){
            if(request.getvalor() != null){
                RankingOut.setvalor(request.getvalor());
            }
            if(request.getId_tarea() != null){
                RankingOut.setId_tarea(request.getId_tarea());
            }
            if(request.getId_voluntario() != null){
                RankingOut.setId_tarea(request.getId_voluntario());
            }
      
            RankingOut = RankingRepository.updateRanking(RankingOut, id);
            return new ResponseEntity<>(gson.toJson(RankingOut),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/Ranking/{id}")
    ResponseEntity<String> deleteRanking(@PathVariable Long id){
        if(RankingRepository.deleteRanking(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
