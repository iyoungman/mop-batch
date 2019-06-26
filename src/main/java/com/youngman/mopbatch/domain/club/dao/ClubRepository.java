package com.youngman.mopbatch.domain.club.dao;

import com.youngman.mopbatch.domain.club.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by YoungMan on 2019-06-22.
 */

public interface ClubRepository extends JpaRepository<Club,Long> {
}
