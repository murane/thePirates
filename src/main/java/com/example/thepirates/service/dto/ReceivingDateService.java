package com.example.thepirates.service.dto;

import com.example.thepirates.api.dto.ReceivingDateInfo;
import com.example.thepirates.domain.Product;
import com.example.thepirates.service.dto.output.ReceivingDateOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceivingDateService {

    private final ProductService productService;

    @Transactional(readOnly = true)
    public ReceivingDateOutput getReceivingDates(Long productId) {
        Product product = productService.getProductById(productId);
        int startdateCount = getSendDate(product) + closingCheck(product);
        List<LocalDateTime> sendableDates = getSendableDates(startdateCount);
        List<String> dates = formattingDate(
                sendableDates.stream()
                .map(it -> it.plusDays(1))
                .collect(Collectors.toList())
        );
        return new ReceivingDateOutput(
                dates.stream()
                        .map(ReceivingDateInfo::new)
                        .collect(Collectors.toList())
        );
    }

    public List<LocalDateTime> getSendableDates(int count) {
        LocalDateTime cursor = LocalDateTime.now().plusDays(count);
        List<LocalDateTime> sendableDates = new ArrayList<>();
        while(sendableDates.size()<5){
            if(cursor.getDayOfWeek().equals(DayOfWeek.SATURDAY)||
            cursor.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                cursor = cursor.plusDays(1);
            }else{
                sendableDates.add(cursor);
                cursor = cursor.plusDays(1);
            }
        }
        return sendableDates;
    }

    private List<String> formattingDate(List<LocalDateTime> dateTimes) {
        return dateTimes.stream()
                .map(
                        it -> it.getMonthValue() + "월 " +
                                it.getDayOfMonth() + "일 " +
                                it.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN)
                )
                .collect(Collectors.toList());
    }

    private int getSendDate(Product product) {
        return product.getDelivery().getType().getSendDate();
    }

    private int closingCheck(Product product) {
        LocalTime now = LocalTime.now();
        LocalTime deliveryClosingTime = product.getDelivery().getClosing();
        if (now.isBefore(deliveryClosingTime))
            return 0;
        else
            return 1;
    }
}
