package com.tekpyramid.movietek.Repositry;

import com.tekpyramid.movietek.Entity.Financial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialRepositry  extends JpaRepository<Financial,Integer> {
}
