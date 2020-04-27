package vargajana95.moneytracker.ui.transactions

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_new_transaction.*
import kotlinx.android.synthetic.main.dialog_new_transaction.view.*
import vargajana95.moneytracker.R
import vargajana95.moneytracker.model.Category
import vargajana95.moneytracker.model.Transaction


class NewTransactionDialog : DialogFragment() {
    private lateinit var listener: NewTransactionDialogListener
    public var categories :List<Category> = mutableListOf();


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;

            val view = inflater.inflate(R.layout.dialog_new_transaction, null)

            view.spCategory.adapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_dropdown_item,categories.map { c-> c.name })

            builder.setView(view)
                .setPositiveButton("Save") { _, _ ->
                    val transaction = Transaction(
                        "",
                        "${dialog?.dpDate?.year}-${dialog?.dpDate?.month}-${dialog?.dpDate?.dayOfMonth}",
                        dialog?.etAmount?.text.toString().toInt() * if (dialog?.radioExpense?.isChecked!!) -1 else 1 ,
                        dialog?.etComment?.text.toString(),
                        dialog?.etName?.text.toString(),
                        Category(categories[dialog?.spCategory?.selectedItemPosition!!].id, categories[dialog?.spCategory?.selectedItemPosition!!].name)
                    )
                    listener.onCreateNewTransaction(transaction)
                    dialog?.cancel()
                }
                .setNegativeButton(
                    "Cancel"
                ) { _, _ ->
                    dialog?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = targetFragment as NewTransactionDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                (context.toString() +
                        " must implement NewTransactionDialogListener")
            )
        }
    }


    interface NewTransactionDialogListener {
        fun onCreateNewTransaction(transaction: Transaction)
    }
}