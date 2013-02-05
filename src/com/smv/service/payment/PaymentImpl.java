package com.smv.service.payment;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.smv.common.bean.InvoiceDTO;
import com.smv.common.bean.InvoiceItemDTO;
import com.smv.common.bean.OrderItemDTO;
import com.smv.common.bean.PaymentDTO;
import com.smv.common.bean.PaymentMethodDTO;
import com.smv.common.bean.PurchaseOrderDTO;
import com.smv.service.payment.db.dao.InvoiceDAO;
import com.smv.service.payment.db.dao.InvoiceItemDAO;
import com.smv.service.payment.db.dao.OrderItemDAO;
import com.smv.service.payment.db.dao.PaymentDAO;
import com.smv.service.payment.db.dao.PurchaseOrderDAO;
import com.smv.service.payment.db.dbobject.InvoiceDO;
import com.smv.service.payment.db.dbobject.InvoiceItemDO;
import com.smv.service.payment.db.dbobject.OrderItemDO;
import com.smv.service.payment.db.dbobject.PaymentDO;
import com.smv.service.payment.db.dbobject.PurchaseOrderDO;
import com.smv.util.db.AbstractDO;

public class PaymentImpl implements IPayment {

	protected static Logger LOGGER = Logger.getLogger(PaymentImpl.class);

	public PaymentImpl() {
	    BasicConfigurator.configure();
	}

	/* (non-Javadoc)
	 * @see com.smv.service.payment.IPayment#createInvoice(com.smv.common.bean.PurchaseOrderDTO, com.smv.common.bean.InvoiceDTO, com.smv.common.bean.InvoiceItemDTO[])
	 */
	@Override
	public InvoiceDTO upsertInvoice(PurchaseOrderDTO purchaseOrder,
			InvoiceDTO invoice, InvoiceItemDTO[] invoiceItems) {
		if ((purchaseOrder == null) || (invoice == null) || (invoiceItems == null) || (invoiceItems.length == 0)) {
			return null;
		}

		// Hook up/relate Invoice to Purchase Order and Invoice Items to Invoice
		invoice.setOrderId(purchaseOrder.getId());
		
		// Create new Invoice DO
		InvoiceDO invoiceDO = new InvoiceDO();

		// Set attributes of Invoice DO from Invoice DTO
		invoiceDO.setAccountId(invoice.getAccountId());
		invoiceDO.setInvoiceCode(invoice.getInvoiceCode());
		invoiceDO.setInvoiceDescription(invoice.getInvoiceDescription());
		invoiceDO.setInvoiceNumberId(invoice.getInvoiceNumberId());
		invoiceDO.setOrderId(invoice.getOrderId());
		invoiceDO.setTotalInvoicedAmount(invoice.getTotalInvoicedAmount());
		invoiceDO.setTotalInvoicedQuantity(invoice.getTotalInvoicedQuantity());
		invoiceDO.setUserId(invoice.getUserId());
		
		// Set operation of DO based on User Operation's action
		if (PaymentConstant.INSERT.equals(invoice.getAction())) {
			// Set operation to "CREATE" -- i.e. insert new row into database
			invoiceDO.setOperation(AbstractDO.CREATE);
		} else if (PaymentConstant.UPDATE.equals(invoice.getAction())) {
			// Set operation to "UPDATE" -- i.e. update row in database
			invoiceDO.setOperation(AbstractDO.UPDATE);
		}

		// Save changes to Invoice DO
		InvoiceDAO invoiceDAO = new InvoiceDAO(invoiceDO);
		invoiceDAO.save(true, false);

		// Set id of Invoice DTO from Invoice DO
		invoice.setId(invoiceDO.getId());
		
		// Create set of invoice items
		for (InvoiceItemDTO item : invoiceItems) {
			// Create new Invoice Item DO
			InvoiceItemDO invoiceItemDO = new InvoiceItemDO();

			// Set attributes of Invoice Item DO from Invoice Item DTO
			invoiceItemDO.setInvoiceId(invoice.getId());
			invoiceItemDO.setItemAmount(item.getItemAmount());
			invoiceItemDO.setItemCode(item.getItemCode());
			invoiceItemDO.setItemDescription(item.getItemDescription());
			invoiceItemDO.setItemQuantity(item.getItemQuantity());
			
			// Set operation of DO based on User Operation's action
			if (PaymentConstant.INSERT.equals(invoice.getAction())) {
				// Set operation to "CREATE" -- i.e. insert new row into database
				invoiceItemDO.setOperation(AbstractDO.CREATE);
			} else if (PaymentConstant.UPDATE.equals(invoice.getAction())) {
				// Set operation to "UPDATE" -- i.e. update row in database
				invoiceItemDO.setOperation(AbstractDO.UPDATE);
			}

			// Save changes to Invoice DO
			invoiceItemDO.setOperation(AbstractDO.CREATE);
			InvoiceItemDAO invoiceItemDAO = new InvoiceItemDAO(invoiceItemDO);
			invoiceItemDAO.save(true, false);

			// Set id of Invoice Item DTO from Invoice Item DO
			item.setId(invoiceItemDO.getId());
		}
		
		return invoice;
	}

	/* (non-Javadoc)
	 * @see com.smv.service.payment.IPayment#createPayment(com.smv.common.bean.PaymentDTO, com.smv.common.bean.PaymentMethodDTO, com.smv.common.bean.InvoiceDTO)
	 */
	@Override
	public PaymentDTO upsertPayment(PaymentDTO payment,
			PaymentMethodDTO paymentMethod, InvoiceDTO invoice) {
		if ((payment == null) || (paymentMethod == null) || (invoice == null)) {
			return null;
		}

		// Hook up/relate Payment to Invoice and Payment to Payment Method
		payment.setInvoiceId(invoice.getId());
		payment.setPaymentMethod(paymentMethod.getId());

		// Create new Payment DO
		PaymentDO paymentDO = new PaymentDO();
		
		// Set attributes of Payment DO from Payment DTO
		paymentDO.setAccountId(payment.getAccountId());
		paymentDO.setDescription(payment.getDescription());
		paymentDO.setInvoiceId(payment.getInvoiceId());
		paymentDO.setPaymentDate(payment.getPaymentDate());
		paymentDO.setPaymentId(payment.getPaymentId());
		paymentDO.setPaymentMethod(payment.getPaymentMethod());
		paymentDO.setUserId(payment.getUserId());

		// Set operation of DO based on User Operation's action
		if (PaymentConstant.INSERT.equals(payment.getAction())) {
			// Set operation to "CREATE" -- i.e. insert new row into database
			paymentDO.setOperation(AbstractDO.CREATE);
		} else if (PaymentConstant.UPDATE.equals(payment.getAction())) {
			// Set operation to "UPDATE" -- i.e. update row in database
			paymentDO.setOperation(AbstractDO.UPDATE);
		}

		// Save changes to Payment DO
		PaymentDAO paymentDAO = new PaymentDAO(paymentDO);
		paymentDAO.save(true, false);

		// Set id of Payment DTO from Payment DO
		payment.setId(paymentDO.getId());

		return payment;
	}

	/* (non-Javadoc)
	 * @see com.smv.service.payment.IPayment#createPurchaseOrder(com.smv.common.bean.PurchaseOrderDTO, com.smv.common.bean.OrderItemDTO[])
	 */
	@Override
	public PurchaseOrderDTO upsertPurchaseOrder(PurchaseOrderDTO purchaseOrder,
			OrderItemDTO[] orderItems) {
		if ((purchaseOrder == null) || (orderItems == null) || (orderItems.length == 0)) {
			return null;
		}

		// Create new Purchase Order DO
		PurchaseOrderDO purchaseOrderDO = new PurchaseOrderDO();

		// Set attributes of Purchase Order DO from Purchase Order DTO
		purchaseOrderDO.setAccountId(purchaseOrder.getAccountId());
		purchaseOrderDO.setOrderCode(purchaseOrder.getOrderCode());
		purchaseOrderDO.setOrderDescription(purchaseOrder.getOrderDescription());
		purchaseOrderDO.setOrderNumberId(purchaseOrder.getOrderNumberId());
		purchaseOrderDO.setTotalOrderAmount(purchaseOrder.getTotalOrderAmount());
		purchaseOrderDO.setTotalOrderQuantity(purchaseOrder.getTotalOrderQuantity());
		purchaseOrderDO.setUserId(purchaseOrder.getUserId());
		
		// Set operation of DO based on User Operation's action
		if (PaymentConstant.INSERT.equals(purchaseOrder.getAction())) {
			// Set operation to "CREATE" -- i.e. insert new row into database
			purchaseOrderDO.setOperation(AbstractDO.CREATE);
		} else if (PaymentConstant.UPDATE.equals(purchaseOrder.getAction())) {
			// Set operation to "UPDATE" -- i.e. update row in database
			purchaseOrderDO.setOperation(AbstractDO.UPDATE);
		}

		// Save changes to Purchase Order DO
		PurchaseOrderDAO purchaseOrderDAO = new PurchaseOrderDAO(purchaseOrderDO);
		purchaseOrderDAO.save(true, false);

		// Set id of Purchase Order DTO from Purchase Order DO
		purchaseOrder.setId(purchaseOrderDO.getId());

		// Create set of Order Items DO
		for (OrderItemDTO item : orderItems) {
			// Create new Invoice Item DO
			OrderItemDO itemDO = new OrderItemDO();

			// Set attributes of Order Item DO from Order Item DTO
			itemDO.setIsService(item.getIsService());
			itemDO.setItemAmount(item.getItemAmount());
			itemDO.setItemCode(item.getItemCode());
			itemDO.setItemDescription(item.getItemDescription());
			itemDO.setItemQuantity(item.getItemQuantity());
			itemDO.setOrderId(purchaseOrder.getId());
			
			// Set operation of DO based on User Operation's action
			if (PaymentConstant.INSERT.equals(purchaseOrder.getAction())) {
				// Set operation to "CREATE" -- i.e. insert new row into database
				itemDO.setOperation(AbstractDO.CREATE);
			} else if (PaymentConstant.UPDATE.equals(purchaseOrder.getAction())) {
				// Set operation to "UPDATE" -- i.e. update row in database
				itemDO.setOperation(AbstractDO.UPDATE);
			}

			// Save changes to Invoice DO
			itemDO.setOperation(AbstractDO.CREATE);
			OrderItemDAO itemDAO = new OrderItemDAO(itemDO);
			itemDAO.save(true, false);

			// Set id of Order Item DTO from Order Item DO
			item.setId(itemDO.getId());
		}
		
		return purchaseOrder;
	}

	/* (non-Javadoc)
	 * @see com.smv.service.payment.IPayment#findPurchaseOrderByOrderNumberId(String)
	 */
	public PurchaseOrderDTO[] findPurchaseOrderByOrderNumberId(String orderNumberId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.smv.service.payment.IPayment#findPurchaseOrderFromInvoice(com.smv.common.bean.InvoiceDTO)
	 */
	public PurchaseOrderDTO findPurchaseOrderFromInvoice(InvoiceDTO invoice) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.smv.service.payment.IPayment#findInvoiceByPurchaseOrder(com.smv.common.bean.PurchaseOrderDTO)
	 */
	public InvoiceDTO findInvoiceByPurchaseOrder(PurchaseOrderDTO purchaseOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.smv.service.payment.IPayment#findInvoiceByInvoiceNumberId(java.lang.String)
	 */
	public PurchaseOrderDTO[] findInvoiceByInvoiceNumberId(
			String invoiceNumberId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.smv.service.payment.IPayment#findPurchaseOrderByAccount(java.lang.String)
	 */
	public PurchaseOrderDTO[] findPurchaseOrderByAccount(String orderNumberId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.smv.service.payment.IPayment#findPurchaseOrderByUser(java.lang.String)
	 */
	public PurchaseOrderDTO[] findPurchaseOrderByUser(String orderNumberId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.smv.service.payment.IPayment#findPaymentByPaymentId(java.lang.String)
	 */
	public PaymentDTO findPaymentByPaymentId(String paymentId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.smv.service.payment.IPayment#findPaymentFromInvoice(com.smv.common.bean.InvoiceDTO)
	 */
	public PaymentDTO[] findPaymentFromInvoice(InvoiceDTO invoice) {
		// TODO Auto-generated method stub
		return null;
	}

}
