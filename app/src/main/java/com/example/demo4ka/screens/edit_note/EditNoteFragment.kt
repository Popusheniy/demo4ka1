package com.example.demo4ka.screens.edit_note

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.*

import androidx.lifecycle.ViewModelProvider
import com.example.demo4ka.R
import com.example.demo4ka.databinding.FragmentEditNoteBinding
import com.example.demo4ka.model.AppNote
import com.example.demo4ka.screens.main.MainAdapter
import com.example.demo4ka.utilits.APP_ACTIVITY
import com.example.demo4ka.utilits.showToast


import com.google.firebase.database.DatabaseReference



class EditNoteFragment: Fragment() {
    private var _binding: FragmentEditNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: EditNoteViewModel
    private lateinit var database: DatabaseReference
//    private lateinit var eAdapter: EditingNoteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()

    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(EditNoteViewModel::class.java)



        mBinding.btnEditNote2.setOnClickListener{

        //  eAdapter = EditingNoteAdapter(requireActivity())
            val name = mBinding.inputNameNote.text.toString()
            val text = mBinding.inputTextNote.text.toString()
            val text2 = mBinding.inputTextNote2.text.toString()

            if (name.isNotEmpty())
            {
                APP_ACTIVITY.navController.navigate(R.id.action_editNoteFragment_to_mainFragment)
            mViewModel.editNote(AppNote(name = name, text = text, text2 = text2, troubleCount = 1))
            } else{
            showToast(getString(R.string.toast_enter_name))
        }
       }

        mBinding.btnNtplus.setOnClickListener{
            // input_text_note2. =
            //    if (condition) View.VISIBLE
            //     else DeprecationLevel.HIDDEN
            mBinding.inputTextNote2.visibility = View.VISIBLE
            mBinding.btnNtplus.visibility= View.INVISIBLE
            //    mBinding.btnAddNote.
        }
        }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_column_action_menu, menu)

    }override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.btn_add -> {
                mBinding.inputTextNote2.visibility = View.VISIBLE

            }
        }

        return super.onOptionsItemSelected(item)
    }



}









