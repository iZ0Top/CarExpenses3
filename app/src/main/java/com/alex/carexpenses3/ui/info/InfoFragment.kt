package com.alex.carexpenses3.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alex.carexpenses3.databinding.FragmentInfoBinding
import com.alex.carexpenses3.model.Car

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var infoViewModel: InfoViewModel
    private lateinit var mCarObserver: Observer<Car>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        infoViewModel = ViewModelProvider(this).get(InfoViewModel::class.java)
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)

        mCarObserver = Observer {
            binding.infoYear.text = it.year.toString()
            binding.infoWinCode.text = it.winCode
            binding.infoEngineVolume.text = it.engineVolume.toString()
        }

        infoViewModel.car.observe(viewLifecycleOwner, mCarObserver)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}