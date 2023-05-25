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
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.alex.carexpenses3.R
import com.alex.carexpenses3.adapters.AdapterExpense
import com.alex.carexpenses3.databinding.FragmentAddBinding
import com.alex.carexpenses3.model.Event
import com.alex.carexpenses3.model.Expense
import com.alex.carexpenses3.ui.dialogs.AddDialog
import com.alex.carexpenses3.utils.APP_ACTIVITY
import com.alex.carexpenses3.utils.DIALOG_ADD_RESULT_KEY
import com.alex.carexpenses3.utils.DIALOG_ADD_TAG
import com.alex.carexpenses3.utils.TAG
import com.alex.carexpenses3.utils.showToast

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
        mRecyclerView = binding.addRecyclerView
        mRecyclerView.adapter = mAdapter

        mEventObserver = Observer {
            binding.textDate.text = it.date.substring(0,10)
            binding.textSum.text = it.sum.toString()
            binding.edittextOdometer.hint = it.odometer.toString()
        }

        mExpenseObserver = Observer { mAdapter.setList(it) }

        addViewModel.eventLD.observe(viewLifecycleOwner, mEventObserver)
        addViewModel.listExpensesLD.observe(viewLifecycleOwner, mExpenseObserver)

        binding.fab.setOnClickListener {
            val dialog = AddDialog()
            dialog.show(childFragmentManager, DIALOG_ADD_TAG)
        }
    }

    override fun onResume() {
        super.onResume()

        childFragmentManager.setFragmentResultListener(DIALOG_ADD_RESULT_KEY, this) { _, result ->
            Log.d(TAG, "AddFragment.childFragmentManager.setFragmentResultListener")
            val expense = result.getSerializable("bundle_key") as Expense
            addViewModel.addExpenseToList(expense)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val newOdometer = binding.edittextOdometer.text.toString().toIntOrNull()

        when(item.itemId){
            R.id.menu_save -> {
                if (newOdometer == null) {
                    binding.edittextOdometer.error = requireActivity().getString(R.string.text_add_mileage)
                    return false
                }
                if (addViewModel.listExpensesLD.value.isNullOrEmpty()){
                    showToast(requireActivity().getString(R.string.text_please_add))
                    return false
                }
                addViewModel.setNewOdometer(newOdometer)
                addViewModel.addEventToDB {
                    addViewModel.updateData(){
                        addViewModel.addExpensesListToDB {
                            APP_ACTIVITY.navController.navigate(R.id.action_addFragment_to_navigation_list)
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