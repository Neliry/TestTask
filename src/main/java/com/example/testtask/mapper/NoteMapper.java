package com.example.testtask.mapper;

import com.example.testtask.dto.NewNoteDto;
import com.example.testtask.dto.NoteDto;
import com.example.testtask.entity.NoteEntity;
import com.example.testtask.mapper.qualifier.MapperQualifier;
import com.example.testtask.mapper.qualifier.QualifierNames;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MapperQualifier.class)
public interface NoteMapper {
    @Mapping(target = "content", qualifiedByName = QualifierNames.STRIP)
    NoteEntity toEntity(NewNoteDto noteDto);

    @Mapping(target = "likes", source = "likedUsersIds", qualifiedByName = QualifierNames.SIZE)
    NoteDto toDto(NoteEntity userEntity);
}
