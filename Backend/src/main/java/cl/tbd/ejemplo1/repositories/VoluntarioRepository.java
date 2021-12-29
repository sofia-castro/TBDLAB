package cl.tbd.ejemplo1.repositories;
import java.util.List;
import cl.tbd.ejemplo1.models.Voluntarios;

public interface VoluntarioRepository{

    public Voluntarios createVoluntario(Voluntarios Voluntario);
    public List<Voluntarios> getAllVoluntarios();
    public Voluntarios getVoluntario(Long id);
    public Voluntarios updateVoluntario(Voluntarios Voluntario, Long id);
    public boolean deleteVoluntario(Long id);
}