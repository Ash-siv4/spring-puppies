package com.qa.puppies.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.puppies.domain.Puppies;

@Repository
public interface PuppiesRepo extends JpaRepository<Puppies,Long>{

}
