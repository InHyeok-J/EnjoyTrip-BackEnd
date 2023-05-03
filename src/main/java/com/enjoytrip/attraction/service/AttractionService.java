package com.enjoytrip.attraction.service;

import com.enjoytrip.attraction.dto.AttractionSearchOptions;
import com.enjoytrip.attraction.entity.Attraction;

import java.util.List;
public interface AttractionService {
    List<Attraction> search(AttractionSearchOptions searchOptions);
}
