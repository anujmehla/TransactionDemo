package com.anujmehla.service;

import com.anujmehla.dto.BookingRequest;
import com.anujmehla.dto.BookingResponse;
import com.anujmehla.exception.InsufficientBalance;

public interface BookingService {
    public BookingResponse bookTicket(BookingRequest bookingRequest) throws InsufficientBalance;
}
