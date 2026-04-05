package pjatk.tpo.demo;

import jakarta.persistence.*;
import lombok.*;


//do tabeli
@Entity
@Setter
@Table(name = "ANIME")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column
    private String studio;

    @Column
    private Integer rate;
}

