package it.coopservice.quarkuspoc.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;

@Entity
@Table(name = "operations")

@FilterDef(name = "obj.code", parameters = @ParamDef(name = "code", type = "string"))
@Filter(name = "obj.code", condition = "code = :code")

@FilterDef(name = "obj.customer_uuids", parameters = @ParamDef(name = "customer_uuids", type = "string"))
@Filter(name = "obj.customer_uuids", condition = "customer_uuid IN (:customer_uuids) ")

public class Operation extends PanacheEntityBase {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Id
    public String uuid;
    public String name;
    public String customer_uuid;

}
