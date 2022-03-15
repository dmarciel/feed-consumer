package com.sparta.infrastructure.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialString {

    private int numberOfBytes;
    private byte[] bytesInUtf8;

}
