package viettel.gpmn.platform.core.data.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import viettel.gpmn.platform.core.enums.Subsystem;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTokenData implements UserDetails {

    public UserTokenData(String username, String password, String userId,
                         Subsystem subsystem, String supplierId, String supplierCode) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.subsystem = subsystem;
        this.supplierId = supplierId;
        this.supplierCode = supplierCode;
    }

    private String username;
    private String password;
    private String userId;
    private Subsystem subsystem;
    private String supplierId;
    private String supplierCode;
    private List<UserFeatureData> authorities;

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

}
