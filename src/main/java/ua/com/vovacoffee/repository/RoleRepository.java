package ua.com.vovacoffee.repository;

import ua.com.vovacoffee.enums.RoleEnum;
import ua.com.vovacoffee.model.Role;

public interface RoleRepository extends MainRepository<Role, Long> {

    Role findByTitle(RoleEnum title);

    void deleteByTitle(RoleEnum title);
}
