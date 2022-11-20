package com.example.repairagencyspring.mapper;

import com.example.repairagencyspring.dto.ReviewDTO;
import com.example.repairagencyspring.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(target = "id", source = "id")
    ReviewDTO reviewToReviewDTO(Review review);

    @Mapping(target = "id", source = "id")
    Review reviewDTOtoReview(ReviewDTO reviewDTO);
}
