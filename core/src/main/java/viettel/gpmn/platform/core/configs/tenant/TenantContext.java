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
        return (UserTokenData) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

    public static String getSupplierId() {
        return getUserInfo() == null ? getUserInfo().getSupplierId() : null;
    }

    public static String getSupplierCode() {
        return getUserInfo() == null ? getUserInfo().getSupplierCode() : null;
    }

    public static void clear() {
        contextThread.remove();
    }
}
