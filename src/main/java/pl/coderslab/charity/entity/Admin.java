package pl.coderslab.charity.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Table(name = "admins")
public class Admin extends BaseUser{

    private String role = "ADMIN";
}
