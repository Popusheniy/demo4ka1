package com.example.demo4ka.screens.main

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.demo4ka.R
import com.example.demo4ka.databinding.FragmentMainBinding
import com.example.demo4ka.model.AppNote
import com.example.demo4ka.utilits.APP_ACTIVITY


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: MainFragmentViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainAdapter
    private var counter: Int = 0
    private var progressBar: ProgressBar? = null
    private var i = 0
    private var txtView: TextView? = null
    private val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return mBinding.root

    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        setHasOptionsMenu(true)
        mAdapter = MainAdapter(requireActivity())
        mRecyclerView = mBinding.recyclerView
        mRecyclerView.adapter = mAdapter
        mViewModel.allNotes.observe(viewLifecycleOwner) {
            mAdapter.setList(it)
        }
        mBinding.btnAddNote.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_addNoteFragment)
        }
        mAdapter.onNoteItemClickListener = {
            mViewModel.editItem(it)
        }
        mAdapter.onNoteItemEditListener = {
            APP_ACTIVITY.navController.navigate(
                MainFragmentDirections.actionMainFragmentToEditNoteFragment(it)
            )
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mRecyclerView.adapter = null
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exit_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_exit -> {
                mViewModel.signOut()
                APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_startFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}