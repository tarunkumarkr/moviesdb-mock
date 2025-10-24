package com.tekpyramid.movietek.Repositry;

import com.tekpyramid.movietek.Entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language,Integer> {
    Optional<Language> findByName(String name);
}
