package viettel.gpmn.platform.cms.services;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import viettel.gpmn.platform.cms.data.OtpData;
import viettel.gpmn.platform.cms.entities.Parameters;
import viettel.gpmn.platform.cms.repositories.ParameterRepository;
import viettel.gpmn.platform.core.enums.OTPPrefix;
import viettel.gpmn.platform.core.services.BaseService;
import viettel.gpmn.platform.core.utilities.Constant;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class OTPService extends BaseService {

    private Boolean isTest;

    private ParameterRepository parameterRepository;

    private RedisTemplate<String, Object> redisTemplate;

    public OtpData generateOTP(OTPPrefix prefix, String objectId) {
        long otpExpiredTime = 300;
        int otpLength = 5;
        String otp = "12345";

        Optional<Parameters> optionalOtpExpiredTime = parameterRepository.findByCodeAndStatusIsTrue(Constant.OTP_EXPIRED_TIME);
        if (optionalOtpExpiredTime.isPresent()) {
            try {
                otpExpiredTime = Integer.parseInt(optionalOtpExpiredTime.get().getValue());
            }
            catch (Exception ignored) {}
        }
        Optional<Parameters> optionalOtpLength = parameterRepository.findByCodeAndStatusIsTrue(Constant.OTP_LENGTH);
        if (optionalOtpLength.isPresent()) {
            try {
                otpLength = Integer.parseInt(optionalOtpLength.get().getValue());
            }
            catch (Exception ignored) {}
        }
        if (!isTest) {
            otp = randomOTP(otpLength);
        }
        redisTemplate.opsForValue().set(prefix.getValue() + Constant.SEPARATE + objectId, otp, otpExpiredTime, TimeUnit.SECONDS);
        return new OtpData(otp, otpExpiredTime);
    }

    public static String randomOTP(Integer OTPLength) {
        if (OTPLength == null || OTPLength < 1) {
            OTPLength = 4;
        }

        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < OTPLength; i++) {
            int digit = new Random().nextInt(10);
            otp.append(digit);
        }

        return otp.toString();
    }
}
