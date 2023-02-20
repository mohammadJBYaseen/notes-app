package com.yaseen.notesapp.repo.convertor;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ReadingConverter
public class CommaSeparatedColumnReadConverter extends StdConverter<String,List<String>> {

    @Override
    public List<String> convert(String dbData) {
        return dbData == null
                ? null
                : Arrays.stream(dbData.split(",")).map(String::trim).collect(Collectors.toList());
    }
}
