package com.alex.carexpenses3.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alex.carexpenses3.R
import com.alex.carexpenses3.database.AppDatabase
import com.alex.carexpenses3.database.AppRepository
import com.alex.carexpenses3.databinding.FragmentListBinding
import com.alex.carexpenses3.utils.APP_ACTIVITY
import com.alex.carexpenses3.utils.REPOSITORY

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    lateinit var dashboardViewModel: FragmentListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val dashboardViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_add -> {
                APP_ACTIVITY.navController.navigate(R.id.action_navigation_list_to_addFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}