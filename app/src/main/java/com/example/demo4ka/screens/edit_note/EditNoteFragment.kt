package com.example.demo4ka.screens.edit_note


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.demo4ka.R
import com.example.demo4ka.databinding.FragmentEditNoteBinding
import com.example.demo4ka.model.AppNote
import com.example.demo4ka.utilits.APP_ACTIVITY
import com.example.demo4ka.utilits.showToast
import com.google.firebase.database.DatabaseReference


class EditNoteFragment : Fragment() {
    private var _binding: FragmentEditNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: EditNoteViewModel
    private lateinit var database: DatabaseReference

    private val args by navArgs<EditNoteFragmentArgs>()
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
        setDefaultValues()
        initialization()

    }

    private fun setDefaultValues(){
        val note = args.note
        with(mBinding){
            inputNameNote.setText(note.name)
            inputTextNote.setText(note.text)
            inputTextNote2.setText(note.text2)
        }
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(EditNoteViewModel::class.java)

        mBinding.btnEditNote2.setOnClickListener {
            val name = mBinding.inputNameNote.text.toString()
            val text = mBinding.inputTextNote.text.toString()
            val text2 = mBinding.inputTextNote2.text.toString()

            if (name.isNotEmpty() && text.isNotEmpty()) {
                mViewModel.editNote(args.note.copy(name = name, text = text, text2 = text2)){
                    APP_ACTIVITY.navController.popBackStack()
                }
            } else {
                showToast(getString(R.string.toast_enter_name))
            }
        }

        mBinding.btnNtplus.setOnClickListener {
            // input_text_note2. =
            //    if (condition) View.VISIBLE
            //     else DeprecationLevel.HIDDEN
            mBinding.inputTextNote2.visibility = View.VISIBLE
            mBinding.btnNtplus.visibility = View.INVISIBLE
            //    mBinding.btnAddNote.
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_column_action_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_add -> {
                mBinding.inputTextNote2.visibility = View.VISIBLE

            }
        }

        return super.onOptionsItemSelected(item)
    }


}









