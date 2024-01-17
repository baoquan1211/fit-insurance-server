package com.fit.health_insurance.service;

import com.fit.health_insurance.dto.PaymentDto;
import com.fit.health_insurance.enums.PaymentStatus;
import com.fit.health_insurance.enums.PaymentType;
import com.fit.health_insurance.exception.InternalErrorException;
import com.fit.health_insurance.exception.NotFoundException;
import com.fit.health_insurance.model.Contract;
import com.fit.health_insurance.model.Payment;
import com.fit.health_insurance.repository.PaymentRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    @Value("${server.address}")
    private String SERVER_HOST;
    @Value("${server.port}")
    private String SERVER_PORT;

    private final PaymentRepository repo;
    private final VnPayService vnPayService;
    private final ModelMapper mapper;

    public String vnPayCreateUrl(Contract contract, String ipAddress) {
        Integer amount = contract.getPrice();
        Integer contractId = contract.getId();
        if (contractId == null || amount == null)
            throw new InternalErrorException("Contract is not valid to do payment");
        Payment newPayment = Payment.builder()
                .amount(amount)
                .contract(contract)
                .status(PaymentStatus.INITIAL)
                .type(PaymentType.VNPAY)
                .build();
        String baseUrl = "http://" + SERVER_HOST + ":" + SERVER_PORT;
        repo.save(newPayment);
        return vnPayService.createOrder(amount, contractId,  newPayment.getId(), baseUrl, ipAddress);
    }

    public Integer paymentCheck(Integer paymentId, HttpServletRequest request)  {
        String paymentTime = request.getParameter("vnp_PayDate");
        Integer transactionId = Integer.parseInt(request.getParameter("vnp_TransactionNo"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        int paymentStatus = vnPayService.orderReturn(request);
        Payment payment = repo.findAllById(paymentId).orElseThrow(() -> new NotFoundException("Payment not found"));

        if (paymentStatus == 1) {
            payment.setStatus(PaymentStatus.SUCCESS);
            try {
                payment.setPayDate(formatter.parse(paymentTime));
            }
            catch (ParseException e) {
                throw new InternalErrorException("Something went wrong");
            }
            payment.setTransactionId(transactionId);
            repo.save(payment);
            return 1;
        }
        else {
            payment.setStatus(PaymentStatus.FAILURE);
            payment.setTransactionId(transactionId);
            repo.save(payment);
            return 0;
        }
    }

    public List<PaymentDto> findAll() {
        var entities = repo.findAll();
        if (entities.isEmpty()) {
            throw new NotFoundException("Payment not found");
        }
        return entities.stream().map(entity -> mapper.map(entity, PaymentDto.class)).toList();
    }
}
