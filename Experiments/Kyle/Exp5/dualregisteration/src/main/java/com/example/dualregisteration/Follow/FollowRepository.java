package com.example.dualregisteration.Follow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    
    @Query("SELECT f FROM Follow f WHERE f.follower.username = :username")
    List<Follow> getFollowsByFollower(String username);

    @Query("SELECT f FROM Follow f WHERE f.followed.businessName = :businessName")
    List<Follow> getFollowsByFollowed(String businessName);

}
