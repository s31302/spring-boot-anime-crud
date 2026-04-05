package pjatk.tpo.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//do rozszerzania JpaRepositoryaby miec gitowe metody findAll(), save(), deleteById(), existsById()
@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {

}
