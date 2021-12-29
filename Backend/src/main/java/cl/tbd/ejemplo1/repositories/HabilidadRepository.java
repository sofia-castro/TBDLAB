package cl.tbd.ejemplo1.repositories;
import cl.tbd.ejemplo1.models.Habilidad;
import java.util.List;
public interface HabilidadRepository{

    public Habilidad createHabilidad(Habilidad habilidad);
    public List<Habilidad> getHabilidades();  
    public Habilidad getHabilidad(Long id);
    public Habilidad updateHabilidad(Habilidad habilidad, Long id);
    public boolean deleteHabilidad(Long id);
}