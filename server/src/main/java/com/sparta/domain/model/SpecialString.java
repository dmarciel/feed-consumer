package com.sparta.domain.model;

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
