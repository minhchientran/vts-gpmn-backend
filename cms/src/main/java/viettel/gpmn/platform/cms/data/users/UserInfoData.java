package viettel.gpmn.platform.cms.data.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoData {
    private String username;
    private String password;
    private String email;
    private LocalDate birthDay;
    private String refCode;
    private String taxNumber;
    private String citizenIdentificationNumber;
    private String imageCardFrontFileName;
    private String imageCardFrontData;
    private String imageCardBackFileName;
    private String imageCardBackData;
    private String imageCardFront;
    private String imageCardBack;
}
