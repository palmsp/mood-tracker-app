package org.palms.mood.tracker.repository;

import org.palms.mood.tracker.domain.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {
}
