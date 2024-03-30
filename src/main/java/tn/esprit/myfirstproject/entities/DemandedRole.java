package tn.esprit.myfirstproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DemandedRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

    @ManyToOne

    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne

    @JoinColumn(name = "role_id")
    private Role role;
    boolean accessApproved;
}
