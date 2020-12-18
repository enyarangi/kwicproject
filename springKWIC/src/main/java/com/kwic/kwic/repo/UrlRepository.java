package com.kwic.kwic.repo;

import com.kwic.kwic.entities.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url,Integer> {

}
