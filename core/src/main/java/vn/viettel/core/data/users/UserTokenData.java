package vn.viettel.core.data.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    public UserTokenData(String username, String password, String userId,
                         Subsystem subsystem, String supplierId, String supplierIdentityCode) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.subsystem = subsystem;
        this.supplierId = supplierId;
        this.supplierIdentityCode = supplierIdentityCode;
    }

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

    public void setAuthoritiesFromListFeature(List<UserFeatureData> listUserFeatureData) {
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
        this.authorities = new ArrayList<>(mapFeatureData.values());
    }
}
