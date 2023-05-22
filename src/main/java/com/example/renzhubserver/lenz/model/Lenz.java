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
    private String brightness;
    private String contrast;
    private String backLight;
    private String saturate;
    private String grain;
    private String temperature;
    private String sharpen;
    private String distortion;

    @OneToOne(mappedBy = "lenz", cascade = CascadeType.ALL, orphanRemoval = true)
    private Post post;

    @Builder
    public Lenz(String brightness, String contrast, String backLight, String saturate, String grain, String temperature, String sharpen, String distortion){
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
