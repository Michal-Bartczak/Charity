package pl.coderslab.charity.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Table(name = "admins")
public class Admin extends BaseUser{

    private String role = "ADMIN";
}
