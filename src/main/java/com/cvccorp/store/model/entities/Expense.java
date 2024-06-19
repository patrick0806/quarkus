package com.cvccorp.store.model.entities;

import com.cvccorp.store.model.enums.ExpenseCategory;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@MongoEntity(collection = "expenses")
public class Expense {
    @BsonId
    private String id;
    private String description;
    private BigDecimal value;
    private ExpenseCategory category;
    private OffsetDateTime date;
    private Integer installments;

    public String getId() {
        return id;
    }

    public Expense setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Expense setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Expense setValue(BigDecimal value) {
        this.value = value;
        return this;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public Expense setCategory(ExpenseCategory category) {
        this.category = category;
        return this;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public Expense setDate(OffsetDateTime date) {
        this.date = date;
        return this;
    }

    public Integer getInstallments() {
        return installments;
    }

    public Expense setInstallments(Integer installments) {
        this.installments = installments;
        return this;
    }
}
