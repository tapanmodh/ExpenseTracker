package com.tm.expensetracker.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tm.expensetracker.R
import com.tm.expensetracker.data.ExpenseDataBase
import com.tm.expensetracker.data.dao.ExpenseDao
import com.tm.expensetracker.data.model.ExpenseEntity

class HomeViewModel(dao: ExpenseDao) : ViewModel() {
    val expenses = dao.getAllExpenses()

    fun getBalance(list : List<ExpenseEntity>): String {

        var total = 0.0
        for (item in list) {
            if (item.type == "Income") {
                total += item.amount
            } else {
                total -= item.amount
            }
        }
        return "$ $total"

    }

    fun getTotalExpenses(list : List<ExpenseEntity>): String {

        var total = 0.0
        for (item in list) {
            if (item.type == "Expense") {
                total += item.amount
            }
        }
        return "$ $total"
    }

    fun getTotalIncome(list : List<ExpenseEntity>): String {

        var total = 0.0
        for (item in list) {
            if (item.type == "Income") {
                total += item.amount
            }
        }
        return "$ $total"
    }

    fun getItemIcon(item: ExpenseEntity): Int {
        return when (item.category) {
            "Paypal" -> R.drawable.ic_paypal
            "Netflix" -> R.drawable.ic_netflix
            "En" -> R.drawable.ic_starbucks
            else -> R.drawable.ic_upwork
        }
    }
}

class HomeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val dao = ExpenseDataBase.getDatabase(context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}