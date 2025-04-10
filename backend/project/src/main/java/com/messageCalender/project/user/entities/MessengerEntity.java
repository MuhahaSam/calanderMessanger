package com.messageCalender.project.user.entities;

import java.math.BigInteger;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.messageCalender.project.user.entities.softDelete.SoftDelete;
import com.messageCalender.project.user.entities.softDelete.SoftDeleteFilter;

@Entity
@Table(name = "messengers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SoftDelete
@SoftDeleteFilter
public class MessengerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "messenger_nick", nullable = false)
    private String messengerNick;

    @Column(name = "messenger_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MessengerTypeEnum messengerType;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
