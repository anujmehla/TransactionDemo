package com.anujmehla.controller;

import com.anujmehla.dto.BookingRequest;
import com.anujmehla.dto.BookingResponse;
import com.anujmehla.exception.InsufficientBalance;
import com.anujmehla.service.BookingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/v1")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/booking")
    public BookingResponse bookTicket(@RequestBody BookingRequest bookingRequest) throws InsufficientBalance {
        log.info("inside controller");
        return bookingService.bookTicket(bookingRequest);
    }
}
