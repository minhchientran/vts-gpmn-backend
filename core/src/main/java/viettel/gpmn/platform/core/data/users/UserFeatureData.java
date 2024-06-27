package viettel.gpmn.platform.core.data.users;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import viettel.gpmn.platform.core.data.InfoData;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFeatureData extends InfoData implements GrantedAuthority {
    private String id;
    private String url;
    private Set<UserControlData> listControl;
    private Set<UserFeatureData> childrenFeature;

    public String getAuthority() {
        return this.getCode();
    }
}
