package com.example.demo4ka.screens.add_new_note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.demo4ka.R
import com.example.demo4ka.databinding.FragmentAddNoteBinding
import com.example.demo4ka.model.AppNote
import com.example.demo4ka.utilits.APP_ACTIVITY
import com.example.demo4ka.utilits.showToast


class AddNoteFragment : Fragment() {

    private var _binding:FragmentAddNoteBinding?=null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: AddNewNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoteBinding.inflate(layoutInflater,container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(AddNewNoteViewModel::class.java)
        mBinding.btnAddNote.setOnClickListener{
            val name = mBinding.inputNameNote.text.toString()
            val text = mBinding.inputTextNote.text.toString()
            val text2 = mBinding.inputTextNote2.text.toString()
            if(name.isEmpty()) {
                showToast(getString(R.string.toast_enter_name))
            } else {
                mViewModel.insert(AppNote(name = name, text = text, text2 = text2, troubleCount = 1)){
                    APP_ACTIVITY.navController.navigate(R.id.action_addNoteFragment_to_mainFragment)
                }
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}