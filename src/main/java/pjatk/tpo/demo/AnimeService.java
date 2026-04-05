package pjatk.tpo.demo;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimeService {
    private AnimeRepository animeRepository;

    AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    //zwracamy liste wszytskich
    public List<AnimeDto> getAllAnime(String sortField){
        List<Anime> all;
        if ("rate_desc".equals(sortField)) {
            all = animeRepository.findAll(Sort.by(Sort.Direction.DESC, "rate"));
        }else if (sortField != null && !sortField.isEmpty()) {
            all = animeRepository.findAll(Sort.by(sortField));
        } else {
            all = animeRepository.findAll();
        }

        List<AnimeDto> animeDtoList = new ArrayList<>();
        for (Anime anime : all) {
            animeDtoList.add(new AnimeDto(anime.getId(), anime.getTitle(), anime.getStudio(), anime.getRate()));
        }
        return animeDtoList;
    }

    //dodajemy do bazy
    public AnimeDto createAnime(AnimeDto animeDto) {
        Anime anime = Anime.builder()
                .title(animeDto.title())
                .studio(animeDto.studio())
                .rate(animeDto.rate())
                .build();
        Anime saved = animeRepository.save(anime); //zapisanie do bazy danych
        return new AnimeDto(saved.getId(), saved.getTitle(), saved.getStudio(), saved.getRate());
    }

    //aktualizacja na podstawie id

    public AnimeDto updateAnime(Long id, AnimeDto animeDto) {
        var animeExist = animeRepository.findById(id);
        if (animeExist.isEmpty()){
            return null;
        }

        Anime anime = animeExist.get();
        anime.setTitle(animeDto.title());
        anime.setStudio(animeDto.studio());
        anime.setRate(animeDto.rate());

        Anime updated = animeRepository.save(anime);
        return new AnimeDto(updated.getId(), updated.getTitle(), updated.getStudio(), updated.getRate());
    }


    //usuwanie na podstwaie id
    public boolean  deleteAnime(Long id) {
        if (!animeRepository.existsById(id)) {
            return false;
        }
        animeRepository.deleteById(id);
        return true;
    }

}

