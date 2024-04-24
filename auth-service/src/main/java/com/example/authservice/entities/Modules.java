package com.example.authservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import vn.viettel.core.entities.EntityWithInfo;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "modules")
public class Modules extends EntityWithInfo {

}
