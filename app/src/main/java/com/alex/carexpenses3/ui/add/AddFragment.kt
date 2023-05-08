package com.alex.carexpenses3.ui.add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.alex.carexpenses3.R
import com.alex.carexpenses3.adapters.AdapterExpense
import com.alex.carexpenses3.databinding.FragmentAddBinding
import com.alex.carexpenses3.model.Event
import com.alex.carexpenses3.model.Expense
import com.alex.carexpenses3.ui.dialogs.AddDialog
import com.alex.carexpenses3.utils.LAST_ODOMETER
import com.alex.carexpenses3.utils.TAG

class AddFragment : Fragment(){

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var addViewModel: AddViewModel
    private lateinit var mEventObserver: Observer<Event>
    private lateinit var mExpenseObserver: Observer<List<Expense>>
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: AdapterExpense



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

        setHasOptionsMenu(true)

        mAdapter = AdapterExpense()
        mRecyclerView = binding.recyclerView
        mRecyclerView.adapter = mAdapter

        mEventObserver = Observer {
            binding.textDate.text = it.date
            binding.textSum.text = it.sum.toString()
            binding.edittextOdometer.hint = it.odometer.toString()
        }

        mExpenseObserver = Observer {
            mAdapter.setList(it)
        }


        addViewModel.eventLD.observe(viewLifecycleOwner, mEventObserver)
        addViewModel.listExpensesLD.observe(viewLifecycleOwner, mExpenseObserver)

        binding.edittextOdometer.setOnFocusChangeListener { _, hasFocus ->
            Log.d(TAG, "edittextOdometer.setOnFocusChangeListener, hasFocus = $hasFocus")
            val value = binding.edittextOdometer.text.toString().toIntOrNull()
            if (!hasFocus && (value != null) && (value > LAST_ODOMETER)){
                addViewModel.setNewOdometer(value)
            }
            else{
                binding.edittextOdometer.error = "Error!"
            }
        }


        binding.fab.setOnClickListener {
            val dialog = AddDialog()
            dialog.show(childFragmentManager, "dialog_tag")
        }
    }


    override fun onResume() {
        super.onResume()

        childFragmentManager.setFragmentResultListener("result_key", this) { _, result ->
            val expense = result.getSerializable("bundle_key") as Expense
            addViewModel.addExpenseToList(expense)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_save -> {
                addViewModel.addEventToDB {
                    addViewModel.updateData(){
                        addViewModel.addExpensesListToDB {

                        }
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onDestroy() {
        super.onDestroy()
        addViewModel.eventLD.removeObserver(mEventObserver)
        mRecyclerView.adapter = null
        _binding = null
    }
}