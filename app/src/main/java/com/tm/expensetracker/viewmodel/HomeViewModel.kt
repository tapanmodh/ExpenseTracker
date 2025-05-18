package com.tm.expensetracker.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tm.expensetracker.Utils
import com.tm.expensetracker.data.ExpenseDataBase
import com.tm.expensetracker.data.dao.ExpenseDao
import com.tm.expensetracker.data.model.ExpenseEntity

class HomeViewModel(dao: ExpenseDao) : ViewModel() {
    val expenses = dao.getAllExpenses()

    fun getBalance(list: List<ExpenseEntity>): String {

        var total = 0.0
        for (item in list) {
            if (item.type == "Income") {
                total += item.amount
            } else {
                total -= item.amount
            }
        }
        return "$ ${Utils.formatToDecimalValue(total)}"

    }

    fun getTotalExpenses(list: List<ExpenseEntity>): String {

        var total = 0.0
        for (item in list) {
            if (item.type == "Expense") {
                total += item.amount
            }
        }
        return "$ ${Utils.formatToDecimalValue(total)}"
    }

    fun getTotalIncome(list: List<ExpenseEntity>): String {

        var total = 0.0
        for (item in list) {
            if (item.type == "Income") {
                total += item.amount
            }
        }

        return "$ ${Utils.formatToDecimalValue(total)}"
    }
}

class HomeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val dao = ExpenseDataBase.getDatabase(context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}