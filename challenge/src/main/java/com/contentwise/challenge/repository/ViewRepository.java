package com.contentwise.challenge.repository;

import com.contentwise.challenge.entity.InteractionId;
import com.contentwise.challenge.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewRepository extends JpaRepository<View, InteractionId> {

    @Query("SELECT v FROM View v WHERE v.interactionId.userId = :userId")
    public List<View> retrieveViews(@Param("userId") int userId);
}
