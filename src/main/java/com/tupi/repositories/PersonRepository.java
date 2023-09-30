package com.tupi.repositories;

import com.tupi.data.vo.v1.PersonVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonVO, Long> {}
