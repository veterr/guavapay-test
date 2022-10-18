package org.guavapay.delivery.repository;

import org.guavapay.delivery.model.ERole;
import org.guavapay.delivery.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
	Optional<Role> findByName(ERole name);
}