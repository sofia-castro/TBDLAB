package cl.tbd.ejemplo1.repositories;
import cl.tbd.ejemplo1.models.Emergencia;
import java.util.List;
public interface EmergenciaRepository{

    public Emergencia createEmergencia(Emergencia emergencia);
    public List<Emergencia> getEmergencias();  
    public Emergencia getEmergencia(Long id);
    public Emergencia updateEmergencia(Emergencia emergencia, Long id);
    public boolean deleteEmergencia(Long id);
}
