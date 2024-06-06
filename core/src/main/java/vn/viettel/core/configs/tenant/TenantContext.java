package vn.viettel.core.configs.tenant;

import org.springframework.security.core.context.SecurityContextHolder;
import vn.viettel.core.data.users.UserTokenData;

public class TenantContext {

    private static final InheritableThreadLocal<UserTokenData> contextThread = new InheritableThreadLocal<>();

    private TenantContext() {
    }
    public static void setUserInfo(UserTokenData userInfo) {
        contextThread.set(userInfo);
    }

    public static UserTokenData getUserInfo() {
        return (UserTokenData) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

    public static String getSupplierId() {
        return getUserInfo() == null ? getUserInfo().getSupplierId() : null;
    }

    public static String getSupplierIdentityCode() {
        return getUserInfo() == null ? getUserInfo().getSupplierIdentityCode() : null;
    }

    public static void clear() {
        contextThread.remove();
    }
}
