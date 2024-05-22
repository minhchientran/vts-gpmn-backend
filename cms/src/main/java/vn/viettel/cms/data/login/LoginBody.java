package vn.viettel.cms.data.login;


public record LoginBody (
        String username,
        String password,
        Integer subsystem
) {}
