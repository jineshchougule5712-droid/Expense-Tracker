package com.expense.service;

import java.util.List;

import com.expense.dto.request.ExpenseRequest;
import com.expense.dto.response.ExpenseResponse;

public interface ExpenseService {

    ExpenseResponse addExpense(ExpenseRequest request);

    List<ExpenseResponse> getAllExpenses();

    ExpenseResponse getExpenseById(Long id);

    ExpenseResponse updateExpense(Long id, ExpenseRequest request);

    void deleteExpense(Long id);
}