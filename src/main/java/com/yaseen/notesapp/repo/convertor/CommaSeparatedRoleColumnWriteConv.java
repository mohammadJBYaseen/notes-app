package com.yaseen.notesapp.repo.convertor;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.StdConverter;
import com.yaseen.notesapp.repo.entity.Role;
import org.springframework.data.convert.WritingConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WritingConverter
public class CommaSeparatedRoleColumnWriteConv extends StdConverter<List<Role>, String> {

    @Override
    public String convert(List<Role> roles) {
        return roles == null
                ? null
                : roles.stream().map(Role::name).map(String::trim).collect(Collectors.joining(","));
    }
}
