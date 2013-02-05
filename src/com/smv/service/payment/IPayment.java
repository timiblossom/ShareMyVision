package com.smv.service.payment;

import com.smv.common.bean.InvoiceItemDTO;
import com.smv.common.bean.InvoiceDTO;
import com.smv.common.bean.OrderItemDTO;
import com.smv.common.bean.PaymentDTO;
import com.smv.common.bean.PaymentMethodDTO;
import com.smv.common.bean.PurchaseOrderDTO;

public interface IPayment {
	public PurchaseOrderDTO upsertPurchaseOrder(PurchaseOrderDTO purchaseOrder, OrderItemDTO[] orderItems);
	public InvoiceDTO upsertInvoice(PurchaseOrderDTO purchaseOrder, InvoiceDTO invoice, InvoiceItemDTO[] invoiceItems);
	public PaymentDTO upsertPayment(PaymentDTO payment, PaymentMethodDTO paymentMethod, InvoiceDTO invoice);
}
