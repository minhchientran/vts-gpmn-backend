package vn.viettel.cms.services;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vn.viettel.cms.entities.Parameters;
import vn.viettel.cms.repositories.ParameterRepository;
import vn.viettel.core.utilities.Constant;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class OTPService {

    private Boolean isTest;

    private ParameterRepository parameterRepository;

    private RedisTemplate<String, Object> redisTemplate;

    public String generateOTP(String prefix, String objectId) {
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
        redisTemplate.opsForValue().set(prefix + Constant.SEPARATE + objectId, otp, otpExpiredTime, TimeUnit.SECONDS);
        return otp;
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
