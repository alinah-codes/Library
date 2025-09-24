package kg.attractor.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "")
public class Role {
    @Id
    private long id;

    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<Role> roles;
}
