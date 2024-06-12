package viettel.gpmn.platform.core.configs.tenant;

import org.springframework.security.core.context.SecurityContextHolder;
import viettel.gpmn.platform.core.data.users.UserTokenData;

public class TenantContext {

    private static final InheritableThreadLocal<UserTokenData> contextThread = new InheritableThreadLocal<>();

    private TenantContext() {
    }
    public static void setUserInfo(UserTokenData userInfo) {
        contextThread.set(userInfo);
    }

    public static UserTokenData getUserInfo() {
        try {
            return (UserTokenData) SecurityContextHolder.getContext().getAuthentication().getDetails();
        }
        catch (Exception ignored) {
            return null;
        }
    }

    public static String getSupplierId() {
        return getUserInfo() == null ? null : getUserInfo().getSupplierId();
    }

    public static String getSupplierCode() {
        return getUserInfo() == null ? null : getUserInfo().getSupplierCode();
    }

    public static void clear() {
        contextThread.remove();
    }
}
