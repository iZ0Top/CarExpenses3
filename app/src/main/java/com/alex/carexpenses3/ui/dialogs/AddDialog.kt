package com.alex.carexpenses3.ui.dialogs

import android.app.Dialog
import android.content.DialogInterface
import android.content.LocusId
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.alex.carexpenses3.databinding.FragmentAddDialogBinding
import com.alex.carexpenses3.model.Expense
import com.alex.carexpenses3.utils.TAG

class AddDialog: DialogFragment(), View.OnClickListener {

    private var _dialogBinding: FragmentAddDialogBinding? = null
    val dialogBinding get() = _dialogBinding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d(TAG, "AddDialog.onCreateDialog")

        _dialogBinding = FragmentAddDialogBinding.inflate(layoutInflater)

        var dialogBuilder = AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setView(dialogBinding.root)
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Ok", null)

        return dialogBuilder.create()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "AddDialog.onResume")

        val d = dialog as AlertDialog
        val button = d.getButton(DialogInterface.BUTTON_POSITIVE)
        button.setOnClickListener {
            validationData() {
                d.dismiss()
            }
        }
        dialogBinding.description.setOnClickListener(this)
        dialogBinding.price.setOnClickListener(this)

    }

    private fun validationData(onSuccess:() -> Unit){
        Log.d(TAG, "AddDialog.validationData")

        dialogBinding.description.setOnClickListener {
        }

        val description = dialogBinding.description.text.toString()
        val descriptionDetail = dialogBinding.detailDescription.text.toString()
        val partNumber = dialogBinding.partNumber.text.toString()
        val quantity = dialogBinding.quantity.text.toString().toIntOrNull()
        val price = dialogBinding.price.text.toString().toDoubleOrNull()

        if (description.isBlank()) {
            dialogBinding.description.error = "is blank !"
            return
        }
        if (quantity == null || quantity == 0){
            dialogBinding.quantity.error = "is blank !"
            dialogBinding.quantity.hint = "1"
            return
        }
        if (price == null) {
            dialogBinding.price.error = "is blank !"
            dialogBinding.price.hint = "0.0"
            return
        }

        val expense = Expense(0, 0, 0, 0,quantity ,price, "0", description, descriptionDetail, partNumber )

        val bundle = Bundle()
        bundle.putSerializable("bundle_key", expense)
        parentFragmentManager.setFragmentResult("result_key", bundle )
        onSuccess()

    }

    override fun onClick(v: View?) {
        Log.d(TAG, "AddDialog.onClick")
        if (v != null) {
            when (v.id) {
                dialogBinding.description.id -> { dialogBinding.description.hint = ""}
                dialogBinding.price.id -> {dialogBinding.description.hint = ""}
            }
        }
    }
}