package com.yaseen.notesapp.repo.convertor;

import com.fasterxml.jackson.databind.util.StdConverter;
import jakarta.persistence.AttributeConverter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WritingConverter
public class CommaSeparatedColumnWriteConverter extends StdConverter<List<String>, String> {
    @Override
    public String convert(List<String> attribute) {
        return attribute == null
                ? null
                : attribute.stream().map(String::trim).collect(Collectors.joining(","));
    }
}
