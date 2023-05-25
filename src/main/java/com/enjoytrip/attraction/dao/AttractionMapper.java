package com.enjoytrip.attraction.dao;

import com.enjoytrip.attraction.dto.AttractionDetailResDto;
import com.enjoytrip.attraction.dto.AttractionGugunDto;
import com.enjoytrip.attraction.dto.AttractionListResDto;
import com.enjoytrip.attraction.dto.AttractionSearchOptionsDto;
import com.enjoytrip.attraction.entity.Attraction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionMapper {
    List<AttractionListResDto> search(AttractionSearchOptionsDto searchOptions);

    AttractionDetailResDto getDetail(String attractionId);

    List<AttractionGugunDto> getGugun(String sidoCode);

    AttractionListResDto getHotAttraction();

    List<AttractionListResDto> getRecommend(Long userId);

    int setFavorite(Long userId);
}
