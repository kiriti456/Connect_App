package com.java.project.connectapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.project.connectapp.model.Posts;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {

    List<Posts> findByUserId(Long userId);

    @Query("SELECT p FROM Posts p WHERE p.user IN (SELECT f.sender FROM FriendRequest f WHERE f.receiver.id = :userId AND f.status = 'ACCEPTED') OR p.user IN (SELECT f.receiver FROM FriendRequest f WHERE f.sender.id = :userId AND f.status = 'ACCEPTED') AND p.visibility = 'PUBLIC'")
    List<Posts> findFriendsPublicPosts(@Param("userId") Long userId);
}
