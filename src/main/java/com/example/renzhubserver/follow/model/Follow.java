package com.example.renzhubserver.follow.model;

import com.example.renzhubserver.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"to_user", "from_user"})
)
@IdClass(Follow.PK.class)
public class Follow {
    @Id //도훈이가
    @Column(name = "to_user", insertable = false, updatable = false)
    private Long toUser;

    @Id //진영이를 팔로우 한다.
    @Column(name = "from_user", insertable = false, updatable = false)
    private Long fromUser;

    @Builder
    public Follow(Long toUser, Long fromUser) {
        this.toUser = toUser;
        this.fromUser = fromUser;
    }

    public static class PK implements Serializable {
        Long toUser;
        Long fromUser;
    }
}
