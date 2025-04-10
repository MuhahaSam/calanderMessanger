package com.messageCalender.project.user.entities;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.messageCalender.project.user.entities.softDelete.SoftDelete;
import com.messageCalender.project.user.entities.softDelete.SoftDeleteFilter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SoftDelete
@SoftDeleteFilter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<MessengerEntity> messengers = new ArrayList<>();

    public void hashPassword(String salt) {
        this.password = hash(this.password, salt);
    }

    private String hash(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    public boolean checkPassword(String plainPassword) {
        return BCrypt.checkpw(plainPassword, this.password);
    }

    public void addMessenger(MessengerEntity messenger) {
        if (messengers == null)
            messengers = new ArrayList<>();
        messengers.add(messenger);
        messenger.setUser(this);
    }

}
