package br.gabrielsmartins.smartpayment.adapters.persistence.entity.transactions;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.accounts.AccountEntity;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.FinancialTransactionStatusDataEnum;
import br.gabrielsmartins.smartpayment.adapters.persistence.enums.converter.FinancialTransactionStatusDataEnumConverter;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(setterPrefix = "with")
@Getter
@Setter
@Table(name = "tbl_financial_transaction")
@Entity
@DynamicUpdate(true)
@SelectBeforeUpdate(false)
public class FinancialTransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private FinancialTransactionEntityId id;

    @Column(name = "financial_transaction_created_at")
    private LocalDateTime createdAt;

    @Column(name = "financial_transaction_description")
    private String description;

    @Column(name = "financial_transaction_amount")
    private BigDecimal amount;

    @Column(name = "financial_transaction_account_balance")
    private BigDecimal accountBalance;

    @Column(name = "financial_transaction_status")
    @Convert(converter = FinancialTransactionStatusDataEnumConverter.class)
    private FinancialTransactionStatusDataEnum status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financial_transaction_account_target_id", referencedColumnName = "account_id")
    private AccountEntity target;

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder(setterPrefix = "with")
    @Getter
    @Setter
    public static class FinancialTransactionEntityId implements Serializable {

        private static final long serialVersionUID = 1L;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "financial_transaction_account_id", referencedColumnName = "account_id")
        private AccountEntity source;

        @Column(name = "financial_transaction_identifier")
        private UUID identifier;
    }

}
