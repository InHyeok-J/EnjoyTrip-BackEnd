package com.enjoytrip.attraction.dao;

import com.enjoytrip.attraction.dto.AttractionSearchOptions;
import com.enjoytrip.attraction.entity.Attraction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttractionMapper {
    List<Attraction> search(AttractionSearchOptions searchOptions);
}
