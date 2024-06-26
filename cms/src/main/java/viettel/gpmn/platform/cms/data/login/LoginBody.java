package viettel.gpmn.platform.cms.data.login;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginBody (
        @NotBlank String username,
        @NotBlank String password,
        @NotNull Integer subsystem
) {}
