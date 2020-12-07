package com.blockchain.backend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Stub Entity class to show how
 * a java class would be mapped to
 * a table using ORM such as hibernate
 */
@Getter
@Setter
@Entity
@Table(name = "RequestMessages")
public class BaseMessage{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private String rawMessage;
}
