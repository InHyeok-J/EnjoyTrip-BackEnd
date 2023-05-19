package com.enjoytrip.attraction.service;

import com.enjoytrip.attraction.dto.AttractionDetailResDto;
import com.enjoytrip.attraction.dto.AttractionListResDto;
import com.enjoytrip.attraction.dto.AttractionSearchOptionsDto;
import com.enjoytrip.attraction.entity.Attraction;

import java.util.List;
public interface AttractionService {
    List<AttractionListResDto> search(AttractionSearchOptionsDto searchOptions);
    AttractionDetailResDto getDetail(int attractionId);
}
