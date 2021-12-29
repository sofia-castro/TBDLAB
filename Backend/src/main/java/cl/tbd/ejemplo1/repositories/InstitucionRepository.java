package cl.tbd.ejemplo1.repositories;
import cl.tbd.ejemplo1.models.Institucion;
import java.util.List;
public interface InstitucionRepository{

    public Institucion createInstitucion(Institucion institucion);
    public List<Institucion> getInstituciones();  
    public Institucion getInstitucion(Long id);
    public Institucion updateInstitucion(Institucion institucion, Long id);
    public boolean deleteInstitucion(Long id);
}