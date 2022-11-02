package org.zimttech.fleetmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zimttech.fleetmanager.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
