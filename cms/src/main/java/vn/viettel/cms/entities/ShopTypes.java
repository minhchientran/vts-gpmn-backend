package vn.viettel.cms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.viettel.core.entities.EntityWithInfo;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shop_types")
public class ShopTypes extends EntityWithInfo {

    @Column
    private String shopTypeParentId;

    @Column
    private String moduleId;

}
