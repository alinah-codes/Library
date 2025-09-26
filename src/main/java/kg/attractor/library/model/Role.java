package kg.attractor.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    private long id;

    private String roleName;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
