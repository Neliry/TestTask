package com.example.testtask.mapper;

import com.example.testtask.dto.RegisterDto;
import com.example.testtask.dto.UserDto;
import com.example.testtask.entity.UserEntity;
import com.example.testtask.mapper.qualifier.MapperQualifier;
import com.example.testtask.mapper.qualifier.QualifierNames;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MapperQualifier.class)
public interface UserMapper {
    @Mapping(target = "email", qualifiedByName = QualifierNames.TO_LOWER_CASE)
    @Mapping(target = "username", qualifiedByName = QualifierNames.STRIP)
    UserEntity toEntity(RegisterDto registerDto);

    UserDto toDto(UserEntity userEntity);
}
