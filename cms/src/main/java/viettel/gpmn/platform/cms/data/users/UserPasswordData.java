package viettel.gpmn.platform.cms.data.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPasswordData {
    private String currentPassword;
    private String newPassword;
}
