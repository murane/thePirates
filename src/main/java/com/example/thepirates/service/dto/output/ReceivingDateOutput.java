package com.example.thepirates.service.dto.output;

import com.example.thepirates.api.dto.ReceivingDateInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReceivingDateOutput {
    List<ReceivingDateInfo> receivingDateInfos = new ArrayList<>();

    public ReceivingDateOutput(List<ReceivingDateInfo> receivingDateInfos) {
        this.receivingDateInfos = receivingDateInfos;
    }
}
