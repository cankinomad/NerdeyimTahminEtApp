package com.berka.mapper;

import com.berka.dto.request.EkleRequestDto;
import com.berka.repository.entity.Sehir;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ISehirMapper {

    ISehirMapper INSTANCE = Mappers.getMapper(ISehirMapper.class);

    Sehir toSehir(final EkleRequestDto dto);
}
