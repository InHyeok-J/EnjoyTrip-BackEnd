package com.enjoytrip.attraction.service;

import com.enjoytrip.attraction.dao.AttractionMapper;
import com.enjoytrip.attraction.dto.AttractionDetailResDto;
import com.enjoytrip.attraction.dto.AttractionListResDto;
import com.enjoytrip.attraction.dto.AttractionSearchOptionsDto;
import com.enjoytrip.attraction.entity.Attraction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {
    private final AttractionMapper dao;
    @Override
    public List<AttractionListResDto> search(AttractionSearchOptionsDto searchOptions) {
        return dao.search(searchOptions);
    }

    @Override
    public AttractionDetailResDto getDetail(int attractionId) {
        return null;
    }
}
