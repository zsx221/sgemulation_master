package com.macrochina.net.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private String msgDef;
    private List<Integer> ids;
}
