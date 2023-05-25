package com.enjoytrip.attraction.service;

import com.enjoytrip.attraction.dao.AttractionMapper;
import com.enjoytrip.attraction.dto.AttractionDetailResDto;
import com.enjoytrip.attraction.dto.AttractionGugunDto;
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
    public AttractionDetailResDto getDetail(String attractionId) {
        return dao.getDetail(attractionId);
    }

    @Override
    public List<AttractionGugunDto> getGugun(String sidoCode) {
        return dao.getGugun(sidoCode);
    }

    @Override
    public AttractionListResDto getHotAttraction() {
        return dao.getHotAttraction();
    }

    @Override
    public List<AttractionListResDto> getRecommend(Long userId) {
        this.dao.setFavorite(userId);
        return dao.getRecommend(userId);
    }

    @Override
    public int setFavorite(Long userId) {
        return dao.setFavorite(userId);
    }
}
