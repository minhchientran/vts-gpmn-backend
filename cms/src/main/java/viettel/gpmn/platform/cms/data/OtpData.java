package viettel.gpmn.platform.cms.data;


import java.time.LocalDateTime;

public record OtpData(
    String otp,
    long expiredTime
) {}
