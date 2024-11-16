package com.contentwise.challenge.repository;

import com.contentwise.challenge.entity.InteractionId;
import com.contentwise.challenge.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRepository extends JpaRepository<View, InteractionId> {
}
