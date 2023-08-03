package com.berka.mapper;

import com.berka.dto.resposne.TahminResponseDto;
import com.berka.repository.entity.Tahmin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ITahminMapper {

    ITahminMapper INSTANCE = Mappers.getMapper(ITahminMapper.class);


    TahminResponseDto toTahminResponseDto(final Tahmin tahmin);
}
