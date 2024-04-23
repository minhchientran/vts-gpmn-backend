package com.example.authservice.repositories;

import com.example.authservice.entities.Users;
import vn.viettel.core.repositories.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<Users> {
    Optional<Users> findByUsername(String username);
}
