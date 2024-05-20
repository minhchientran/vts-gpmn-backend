package vn.viettel.core.data.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.viettel.core.enums.Subsystem;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTokenData implements UserDetails {
    private String username;
    private String password;
    private String userId;
    private Subsystem subsystem;
    private String supplierId;
    private String supplierIdentityCode;
    private List<Authority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Authority implements GrantedAuthority {
        private String featureId;
        private String featureCode;
        private List<UserControlData> controls = new ArrayList<>();

        public String getAuthority() {
            return featureCode;
        }
    }

    public void setAuthorities(List<UserFeatureData> listUserFeatureData) {
        Map<String, Authority> mapFeatureData = new HashMap<>();
        for (UserFeatureData userFeature : listUserFeatureData) {
            UserControlData userControlData = new UserControlData(userFeature.getControlId(), userFeature.getControlCode());
            if (mapFeatureData.containsKey(userFeature.getFeatureId())) {
                mapFeatureData.get(userFeature.getFeatureId()).getControls().add(userControlData);
            }
            else {
                Authority authority = new Authority(userFeature.getFeatureId(), userFeature.getFeatureCode(), new ArrayList<>());
                authority.getControls().add(userControlData);
                mapFeatureData.put(userFeature.getFeatureId(), authority);
            }
        }
        this.authorities = (List<Authority>) mapFeatureData.values();
    }
}
