package com.example.demo4ka.screens.note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.demo4ka.R
import com.example.demo4ka.databinding.FragmentMainBinding
import com.example.demo4ka.databinding.FragmentNoteBinding
import com.example.demo4ka.model.AppNote
import com.example.demo4ka.screens.main.MainAdapter
import com.example.demo4ka.screens.main.MainFragmentViewModel
import com.example.demo4ka.utilits.APP_ACTIVITY

class NoteFragment : Fragment() {

        private var _binding: FragmentNoteBinding?=null
        private val mBinding get() = _binding!!
        private lateinit var mViewModel: NoteFragmentViewModel
        private lateinit var mCurrentNote:AppNote

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
            mCurrentNote = arguments?.getSerializable("note") as AppNote
            return mBinding.root
        }

        override fun onStart() {
            super.onStart()
            initialization()

        }
        private fun initialization(){
            setHasOptionsMenu(true)
            mBinding.noteText.text = mCurrentNote.text
            mBinding.noteName.text = mCurrentNote.name
            mViewModel = ViewModelProvider(this).get(NoteFragmentViewModel::class.java)

        }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btn_delete ->{
            mViewModel.delete(mCurrentNote){
            APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
            }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
            super.onDestroyView()
            _binding = null

        }

}