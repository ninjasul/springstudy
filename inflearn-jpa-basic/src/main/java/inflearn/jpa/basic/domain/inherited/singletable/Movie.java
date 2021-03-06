package inflearn.jpa.basic.domain.inherited.singletable;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Entity(name = "MOVIE_SINGLE_TABLE")
@DiscriminatorValue("M")
public class Movie extends Item {
    private String director;
    private String actor;

    public Movie(String name, int price, String director, String actor) {
        super(name, price);
        this.director = director;
        this.actor = actor;
    }

    public Movie(Long id, String name, int price, String director, String actor) {
        super(id, name, price);
        this.director = director;
        this.actor = actor;
    }
}