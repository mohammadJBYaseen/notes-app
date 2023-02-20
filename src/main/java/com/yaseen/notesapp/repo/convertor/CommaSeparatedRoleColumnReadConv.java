package com.yaseen.notesapp.repo.convertor;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.yaseen.notesapp.repo.entity.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ReadingConverter
public class CommaSeparatedRoleColumnReadConv extends StdConverter<String,List<Role>> {

    @Override
    public List<Role> convert(String s) {
        return s == null
                ? null
                : Arrays.stream(s.split(",")).map(String::trim)
                .map(Role::valueOf)
                .collect(Collectors.toList());
    }
}
