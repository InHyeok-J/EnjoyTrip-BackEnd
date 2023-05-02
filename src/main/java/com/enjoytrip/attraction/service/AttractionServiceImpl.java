package com.enjoytrip.attraction.service;

import com.enjoytrip.attraction.dao.AttractionMapper;
import com.enjoytrip.attraction.dto.AttractionSearch;
import com.enjoytrip.attraction.entity.Attraction;

import java.util.List;

public class AttractionServiceImpl implements AttractionService {
    AttractionMapper dao;
    @Override
    public List<Attraction> search(AttractionSearch searchOptions) {
        return dao.search(searchOptions);
    }
}
