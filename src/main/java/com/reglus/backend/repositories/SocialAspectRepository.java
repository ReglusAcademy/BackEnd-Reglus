package com.reglus.backend.repositories;

import com.reglus.backend.model.entities.users.SocialAspect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialAspectRepository extends JpaRepository<SocialAspect, Long> {
}
