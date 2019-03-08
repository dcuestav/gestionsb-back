package com.nidara.sabanasblancas.gestion.daos;

import com.nidara.sabanasblancas.gestion.model.Role;
import com.nidara.sabanasblancas.gestion.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
