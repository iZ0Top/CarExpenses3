package com.alex.carexpenses3.ui.dialogs

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.alex.carexpenses3.databinding.FragmentAddDialogBinding
import com.alex.carexpenses3.model.Expense

class AddDialog: DialogFragment() {

    private var _dialogBinding: FragmentAddDialogBinding? = null
    val dialogBinding get() = _dialogBinding!!
    private lateinit var expense: Expense

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

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

        val d = dialog as AlertDialog
        val button = d.getButton(DialogInterface.BUTTON_POSITIVE)
        button.setOnClickListener {
            validationData()
        }
    }

    private fun validationData(){

        expense = Expense(1, 0,1, 100, 1, 10.0, "01.01.0001", "Description", "Detail description", "Part number")

        val bundle = Bundle()
        bundle.putSerializable("bundle_key", expense)
        parentFragmentManager.setFragmentResult("result_key", bundle )
    }
}