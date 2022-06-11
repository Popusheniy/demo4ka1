package com.example.demo4ka.screens.note

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.demo4ka.R
import com.example.demo4ka.databinding.FragmentMainBinding
import com.example.demo4ka.databinding.FragmentNoteBinding
import com.example.demo4ka.model.AppNote
import com.example.demo4ka.screens.edit_note.EditNoteViewModel
import com.example.demo4ka.screens.main.MainAdapter
import com.example.demo4ka.screens.main.MainFragmentViewModel
import com.example.demo4ka.utilits.APP_ACTIVITY
import com.example.demo4ka.utilits.ID_FIREBASE
import com.example.demo4ka.utilits.REF_DATABASE
import com.example.demo4ka.utilits.showToast
import kotlin.math.absoluteValue
import kotlin.math.min
import kotlin.math.roundToInt


class NoteFragment : Fragment() {
        private var _binding: FragmentNoteBinding?=null
        private val mBinding get() = _binding!!
        private lateinit var mViewModel: NoteFragmentViewModel
        private lateinit var eViewModel: EditNoteViewModel
        private lateinit var mCurrentNote:AppNote
        private var handler = Handler()
        private var progress = 0



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            _binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
            mCurrentNote = arguments?.getSerializable("note") as AppNote

            return mBinding.root
        }


        override fun onStart() {
            super.onStart()
            initialization()

        }
        private fun initialization(): Int {
            setHasOptionsMenu(true)
            mBinding.noteText.text = mCurrentNote.text
            mBinding.noteName.text = mCurrentNote.name
            mBinding.noteText2.text = mCurrentNote.text2
            mBinding.prgBar.progress = mCurrentNote.progressStatus



            mViewModel = ViewModelProvider(this).get(NoteFragmentViewModel::class.java)

            mBinding.prgBar.progress = 0

            mBinding.prgBtn.setOnClickListener {

              //      progressBar.progress   = progressStatus + 50
               if (progress <50){
                   progress +=50
                   mBinding.prgBar.progress = progress
               }else{
                   progress = 100

                   mBinding.prgBar.progress = progress


                }

            }

            mBinding.prgBtn2.setOnClickListener{

                if (progress <50){
                    progress +=50
                    mBinding.prgBar.progress = progress
                }else{
                    progress = 100

                    mBinding.prgBar.progress = progress


                }
                }

return(mBinding.prgBar.progress)
        }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu, menu)
        inflater.inflate(R.menu.edit_note_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.btn_delete -> {
                mViewModel.delete(mCurrentNote) {
                    APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
                }
            }
        }
        when (item.itemId) {
            R.id.btn_edit -> {
                mViewModel.editNote(mCurrentNote){
              APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_editNoteFragment)
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


