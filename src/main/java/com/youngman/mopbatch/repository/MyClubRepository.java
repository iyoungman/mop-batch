package com.youngman.mopbatch.repository;

import com.youngman.mopbatch.domain.entity.MyClub;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by YoungMan on 2019-06-22.
 */

public interface MyClubRepository extends JpaRepository<MyClub, Long> {
}
