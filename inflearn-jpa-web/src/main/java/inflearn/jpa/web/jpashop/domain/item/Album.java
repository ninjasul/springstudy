package inflearn.jpa.web.jpashop.domain.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Builder
@Entity
@DiscriminatorValue("A")
public class Album extends Item {
    private String artist;
    private String etc;

    public Album(String artist, String etc) {
        this.artist = artist;
        this.etc = etc;
    }
}