package com.smv.service.payment;



import javax.jws.WebService;

import com.smv.common.bean.InvoiceDTO;
import com.smv.common.bean.InvoiceItemDTO;
import com.smv.common.bean.OrderItemDTO;
import com.smv.common.bean.PaymentDTO;
import com.smv.common.bean.PaymentMethodDTO;
import com.smv.common.bean.PurchaseOrderDTO;
import com.smv.common.exception.SmvServiceException;
import com.smv.util.thread.InstanceGenerator;


@WebService(endpointInterface = "com.smv.service.payment.IPaymentService", serviceName = "PaymentService", targetNamespace = "http://smv") 
public class PaymentServiceImpl implements IPaymentService {

	/* (non-Javadoc)
	 * @see com.smv.service.payment.IPaymentService#upsertInvoice(com.smv.common.bean.PurchaseOrderDTO, com.smv.common.bean.InvoiceDTO, com.smv.common.bean.InvoiceItemDTO[])
	 */
	@Override
	public InvoiceDTO upsertInvoice(PurchaseOrderDTO purchaseOrder,
			InvoiceDTO invoice, InvoiceItemDTO[] invoiceItems)
			throws SmvServiceException {
		return InstanceGenerator.getPaymentImplInstance().upsertInvoice(purchaseOrder, invoice, invoiceItems);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.payment.IPaymentService#upsertPayment(com.smv.common.bean.PaymentDTO, com.smv.common.bean.PaymentMethodDTO, com.smv.common.bean.InvoiceDTO)
	 */
	@Override
	public PaymentDTO upsertPayment(PaymentDTO payment,
			PaymentMethodDTO paymentMethod, InvoiceDTO invoice)
			throws SmvServiceException {
		return InstanceGenerator.getPaymentImplInstance().upsertPayment(payment, paymentMethod, invoice);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.payment.IPaymentService#upsertPurchaseOrder(com.smv.common.bean.PurchaseOrderDTO, com.smv.common.bean.OrderItemDTO[])
	 */
	@Override
	public PurchaseOrderDTO upsertPurchaseOrder(PurchaseOrderDTO purchaseOrder,
			OrderItemDTO[] orderItems) throws SmvServiceException {
		return InstanceGenerator.getPaymentImplInstance().upsertPurchaseOrder(purchaseOrder, orderItems);
	}
}
