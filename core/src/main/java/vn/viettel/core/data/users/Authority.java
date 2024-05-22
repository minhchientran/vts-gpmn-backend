package vn.viettel.core.data.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements GrantedAuthority {
    private String featureId;
    private String featureCode;
    private List<UserControlData> controls;

    public String getAuthority() {
        return featureCode;
    }
}
