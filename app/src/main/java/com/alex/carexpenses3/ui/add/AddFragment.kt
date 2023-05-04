package com.alex.carexpenses3.ui.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.alex.carexpenses3.R
import com.alex.carexpenses3.databinding.FragmentAddBinding
import com.alex.carexpenses3.model.Expense

class AddFragment : Fragment(){

    private var _binding: FragmentAddBinding? = null
    val binding get() = _binding!!
    private lateinit var addViewModel: AddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        addViewModel = ViewModelProvider(this).get(AddViewModel::class.java)

        return binding.root
    }


    override fun onStart() {
        super.onStart()

    }


    override fun onResume() {
        super.onResume()

        childFragmentManager.setFragmentResultListener("result_key", this) { _, result ->

            val expense = result.getSerializable("bundle_key") as Expense

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}