package org.palms.mood.tracker.repository;

import org.palms.mood.tracker.domain.FeelingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Repository
public interface FeelingRepository extends JpaRepository<FeelingEntity, Long> {
}
