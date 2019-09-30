package inflearn.jpa.web.domain.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Builder
@Entity
@DiscriminatorValue("M")
public class Movie extends Item {
    private String director;
    private String actor;

    public Movie(String director, String actor) {
        this.director = director;
        this.actor = actor;
    }
}