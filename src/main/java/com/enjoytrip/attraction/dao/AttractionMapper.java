package com.enjoytrip.attraction.dao;

import com.enjoytrip.attraction.dto.AttractionSearch;
import com.enjoytrip.attraction.entity.Attraction;
import com.enjoytrip.attraction.entity.AttractionCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttractionMapper {
    List<Attraction> search(AttractionSearch searchOptions);
}
