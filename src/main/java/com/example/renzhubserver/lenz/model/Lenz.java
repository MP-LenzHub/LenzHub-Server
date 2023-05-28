package com.example.renzhubserver.lenz.model;

import com.example.renzhubserver.post.model.Post;
import com.example.renzhubserver.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class Lenz {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Float brightness;
    private Float contrast;
    private Float backLight;
    private Float saturate;
    private Float grain;
    private Float temperature;
    private Float sharpen;
    private Float distortion;

    @OneToOne(mappedBy = "lenz", cascade = CascadeType.ALL, orphanRemoval = true)
    private Post post;

    @Builder
    public Lenz(Float brightness, Float contrast, Float backLight, Float saturate, Float grain, Float temperature, Float sharpen, Float distortion){
        this.brightness = brightness;
        this.contrast = contrast;
        this.backLight = backLight;
        this.saturate = saturate;
        this.grain = grain;
        this.temperature = temperature;
        this.sharpen = sharpen;
        this.distortion = distortion;
    }
}
