package com.anujmehla.dto;

import com.anujmehla.entity.PassengerInfo;
import com.anujmehla.entity.PaymentInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    private PaymentInfo paymentInfo;
    private PassengerInfo passengerInfo;
}
