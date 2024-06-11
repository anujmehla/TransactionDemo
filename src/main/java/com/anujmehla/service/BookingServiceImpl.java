package com.anujmehla.service;

import com.anujmehla.dto.BookingRequest;
import com.anujmehla.dto.BookingResponse;
import com.anujmehla.entity.PassengerInfo;
import com.anujmehla.entity.PaymentInfo;
import com.anujmehla.exception.InsufficientBalance;
import com.anujmehla.repository.PassengerInfoRepository;
import com.anujmehla.repository.PaymentInfoRepository;
import com.anujmehla.util.PaymentGatewaySimulator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Log4j2
@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private PaymentInfoRepository paymentInfoRepository;
    @Autowired
    private PassengerInfoRepository passengerInfoRepository;


    //transaction only works with public method and unchecked exception
    @Override
//    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    @Transactional(rollbackFor = {InsufficientBalance.class})
    public BookingResponse bookTicket(@RequestBody BookingRequest bookingRequest) throws InsufficientBalance {
        log.info("inside service");
        BookingResponse bookingResponse = null;
        PassengerInfo passengerInfo = passengerInfoRepository.save(bookingRequest.getPassengerInfo());

        log.info("here is the issue");
        PaymentInfo paymentInfo = bookingRequest.getPaymentInfo();

        // simulate transaction failure due to insufficient balance
        PaymentGatewaySimulator.validateFareBalanceCriteria(paymentInfo.getAccountNo(),passengerInfo.getFare());

        paymentInfo.setPassengerId(passengerInfo.getPassengerId());
        paymentInfo.setTotalFare(passengerInfo.getFare());
        paymentInfoRepository.save(paymentInfo);

        bookingResponse = new BookingResponse();
        bookingResponse.setStatus("Success");
        bookingResponse.setPassengerInfo(passengerInfo);
        bookingResponse.setPnr(UUID.randomUUID().toString().split("-")[0]);
        bookingResponse.setTotalFare(passengerInfo.getFare());

        return bookingResponse;
    }

}
