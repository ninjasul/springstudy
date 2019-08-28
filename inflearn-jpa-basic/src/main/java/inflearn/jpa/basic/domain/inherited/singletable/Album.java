package inflearn.jpa.basic.domain.inherited.singletable;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Entity(name = "ALBUM_SINGLE_TABLE")
@DiscriminatorValue("A")
public class Album extends Item {
    private String artist;
}