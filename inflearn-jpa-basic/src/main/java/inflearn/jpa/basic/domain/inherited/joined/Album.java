package inflearn.jpa.basic.domain.inherited.joined;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Entity(name = "ALBUM_JOINED")
@DiscriminatorValue("A")
public class Album extends Item {
    private String artist;
}