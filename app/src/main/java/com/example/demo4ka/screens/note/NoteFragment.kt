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
import com.example.demo4ka.screens.main.MainAdapter
import com.example.demo4ka.screens.main.MainFragmentViewModel
import com.example.demo4ka.utilits.APP_ACTIVITY

class NoteFragment : Fragment() {
    private var _binding: FragmentNoteBinding?=null
        private val mBinding get() = _binding!!
        private lateinit var mViewModel: NoteFragmentViewModel
        private lateinit var mCurrentNote:AppNote
    private var progressStatus = 0
    private var handler = Handler()



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
            mViewModel = ViewModelProvider(this).get(NoteFragmentViewModel::class.java)

            mBinding.prgBtn.setOnClickListener {

              //      progressBar.progress   = progressStatus + 50
                Thread(Runnable {
                    while (progressStatus < 100){
                        // update progress status
                        progressStatus +50

                        // sleep the thread for 100 milliseconds
                        Thread.sleep(100)

                        // update the progress bar
                        handler.post {

                            mBinding.prgBar.progress = progressStatus+50

                        }
                    }
                }).start()

            }

            mBinding.prgBtn2.setOnClickListener{
                Thread(Runnable {
                    while (progressStatus < 100){
                        // update progress status
                        progressStatus +50

                        // sleep the thread for 100 milliseconds
                        Thread.sleep(100)

                        // update the progress bar
                        handler.post {

                            mBinding.prgBar.progress = progressStatus+50

                        }
                    }
                }).start()

            }
            return(progressStatus)
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


