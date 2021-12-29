package cl.tbd.ejemplo1.repositories;
import cl.tbd.ejemplo1.models.Ranking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class RankingRepositoryImp implements RankingRepository {
    @Autowired
     private Sql2o sql2o;

    
    @Override
    public Ranking createRanking(Ranking ranking){
        String query = "insert into Ranking (valor, id_tarea, id_voluntario) values (:valor, :id_tarea, :id_voluntario)";
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query).addParameter("valor", ranking.getvalor())
                            .addParameter("id_tarea", ranking.getId_tarea())
                            .addParameter("id_voluntario", ranking.getId_voluntario())
                            .addParameter("deleted",false)
                            .executeUpdate().getKey();
            ranking.setId(insertedid);
            return ranking;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Ranking getRanking(Long id){
        String query = "select * from Ranking where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).addParameter("id",id).executeAndFetchFirst(Ranking.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Ranking> getRankings() {
        String query = "select * from Ranking";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).executeAndFetch(Ranking.class);
         }
         catch (Exception e){
             System.out.println(e.getMessage());
             return null;
         }
     }

     @Override
     public boolean deleteRanking(Long id){
         String query = "update Ranking set deleted = true where id = :id and deleted = false";
         try(Connection conn = sql2o.open()){
            id = conn.createQuery(query).addParameter("id",id).executeUpdate().getKey(Long.class);
         }
         catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
     }

     @Override
     public Ranking updateRanking( Ranking ranking,Long id){
        String query = "update Ranking where id = :id and deleted = false and set (valor, id_tarea, id_voluntario) values (:valor, :id_tarea, :id_voluntario)";
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query)
                            .addParameter("valor", ranking.getvalor())
                            .addParameter("id_tarea", ranking.getId_tarea())
                            .addParameter("id_voluntario", ranking.getId_voluntario())
                            .addParameter("deleted",false)
                            .executeUpdate().getKey(Long.class);
            ranking.setId(insertedid);

            return ranking;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

     }
}
