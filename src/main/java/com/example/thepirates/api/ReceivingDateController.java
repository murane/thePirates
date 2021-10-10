package com.example.thepirates.api;

import com.example.thepirates.api.dto.response.ReceivingDateResponse;
import com.example.thepirates.service.dto.ReceivingDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReceivingDateController {
    private final ReceivingDateService receivingDateService;

    @GetMapping("/rcvDate")
    public ResponseEntity<ReceivingDateResponse> QueryReceivingDate(@RequestParam("id") Long productId){
        var result = receivingDateService.getReceivingDates(productId);
        return ResponseEntity.ok(new ReceivingDateResponse(result.getReceivingDateInfos()));
    }
}
