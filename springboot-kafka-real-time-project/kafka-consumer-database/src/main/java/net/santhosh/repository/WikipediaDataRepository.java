package net.santhosh.repository;

import net.santhosh.entity.WikipediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikipediaDataRepository extends JpaRepository<WikipediaData, Long> {
}
