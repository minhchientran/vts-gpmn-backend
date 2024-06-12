package viettel.gpmn.platform.cms.data.login;


public record LoginBody (
        String username,
        String password,
        Integer subsystem
) {}
