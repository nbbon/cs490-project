package mum.pmp.mstore.integration.mockpayment.model;

import java.time.LocalDate;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;

@Entity(name = "PaymentData")
@SqlResultSetMappings({
	@SqlResultSetMapping(
        name="VisaCardTransactionMapping",
        classes=@ConstructorResult(
                targetClass=VisaCardTransaction.class,
                columns={@ColumnResult(name="id", type=Long.class),
                		@ColumnResult(name="card_number", type=String.class),
                        @ColumnResult(name="card_name", type=String.class),
                        @ColumnResult(name="t_date", type=LocalDate.class),
                        @ColumnResult(name="t_amount", type=Double.class),
                        @ColumnResult(name="pre_balance", type=Double.class),
                        @ColumnResult(name="remain_balance", type=Double.class),
                        @ColumnResult(name="description", type=String.class)})),
	@SqlResultSetMapping(
	        name="MasterCardTransactionMapping",
	        classes=@ConstructorResult(
	                targetClass=MasterCardTransaction.class,
	                columns={@ColumnResult(name="id", type=Long.class),
	                		@ColumnResult(name="card_number", type=String.class),
	                        @ColumnResult(name="card_name", type=String.class),
	                        @ColumnResult(name="t_date", type=LocalDate.class),
	                        @ColumnResult(name="t_amount", type=Double.class),
	                        @ColumnResult(name="pre_balance", type=Double.class),
	                        @ColumnResult(name="remain_balance", type=Double.class),
	                        @ColumnResult(name="description", type=String.class)}))
})
@NamedNativeQueries({
	@NamedNativeQuery(
        name="PaymentData.getCurrentVisaCardBalance",
        query="SELECT c.balance \n" + 
        		"FROM visa_card_balance c \n" +
        		"WHERE c.card_number = :cardNumber",
        		resultClass=Double.class),
	@NamedNativeQuery(
	        name="PaymentData.getCurrentMasterCardBalance",
	        query="SELECT c.balance \n" + 
	        		"FROM master_card_balance c \n" +
	        		"WHERE c.card_number = :cardNumber",
	        		resultClass=Double.class),
	@NamedNativeQuery(
	        name="PaymentData.setCurrentMasterCardBalance",
	        query="UPDATE master_card_balance c \n " +
	        		"SET balance = :balance \n" + 
	        		"WHERE c.card_number = :cardNumber",
	        		resultClass=Void.class),
	@NamedNativeQuery(
	        name="PaymentData.setCurrentVisaCardBalance",
	        query="UPDATE visa_card_balance c \n " +
	        		"SET balance = :balance \n" + 
	        		"WHERE c.card_number = :cardNumber",
	        		resultClass=Void.class),
	@NamedNativeQuery(
	        name="PaymentData.setVisaCardBalance",
	        query="UPDATE visa_card_balance c \n " +
	        		"SET balance = :balance \n" + 
	        		"WHERE c.card_number = :cardNumber",
	        		resultClass=Void.class)
})
public class PaymentData {
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
