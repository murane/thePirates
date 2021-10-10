package com.example.thepirates.api.dto.response;

import com.example.thepirates.api.dto.ReceivingDateInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReceivingDateResponse {
    private List<ReceivingDateInfo> receivingDateInfos;

    public ReceivingDateResponse(List<ReceivingDateInfo> receivingDateInfos) {
        this.receivingDateInfos = receivingDateInfos;
    }
}
