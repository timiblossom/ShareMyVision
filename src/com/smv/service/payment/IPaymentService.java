package com.smv.service.payment;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.smv.common.bean.InvoiceItemDTO;
import com.smv.common.bean.InvoiceDTO;
import com.smv.common.bean.OrderItemDTO;
import com.smv.common.bean.PaymentDTO;
import com.smv.common.bean.PaymentMethodDTO;
import com.smv.common.bean.PurchaseOrderDTO;
import com.smv.common.exception.SmvServiceException;

@WebService
public interface IPaymentService {

	public PurchaseOrderDTO upsertPurchaseOrder(@WebParam(name = "purchaseOrder")PurchaseOrderDTO purchaseOrder, 
			@WebParam(name = "orderItems")OrderItemDTO[] orderItems) throws SmvServiceException;

	public InvoiceDTO upsertInvoice(@WebParam(name = "purchaseOrder")PurchaseOrderDTO purchaseOrder, 
			@WebParam(name = "invoice")InvoiceDTO invoice, 
			@WebParam(name = "invoiceItems")InvoiceItemDTO[] invoiceItems) throws SmvServiceException;

	public PaymentDTO upsertPayment(@WebParam(name = "payment")PaymentDTO payment, 
			@WebParam(name = "paymentMethod")PaymentMethodDTO paymentMethod, 
			@WebParam(name = "invoice")InvoiceDTO invoice) throws SmvServiceException;

}
