package it.coopservice.quarkuspoc.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;

@Entity
@Table(name = "customers")

@FilterDef(name = "obj.code", parameters = @ParamDef(name = "code", type = "string"))
@Filter(name = "obj.code", condition = "code = :code")

@FilterDef(name = "like.name", parameters = @ParamDef(name = "name", type = "string"))
@Filter(name = "like.name", condition = "lower(name) LIKE :name")

@FilterDef(name = "obj.active", parameters = @ParamDef(name = "active", type = "boolean"))
@Filter(name = "obj.active", condition = "active = :active")

public class Customer extends PanacheEntityBase {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Id
    public String uuid;
    public String code;
    public String name;
    public boolean active;
    public String ldap_group;
    public String mail;
}
