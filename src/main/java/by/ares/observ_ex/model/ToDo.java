package by.ares.observ_ex.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "to_dos")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String desc;
}
