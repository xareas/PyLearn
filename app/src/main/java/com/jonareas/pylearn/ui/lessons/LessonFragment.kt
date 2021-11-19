package com.jonareas.pylearn.ui.lessons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jonareas.pylearn.LessonShowFragmentArgs
import com.jonareas.pylearn.R
import com.jonareas.pylearn.adapter.LessonAdapter
import com.jonareas.pylearn.data.model.LessonHeader
import com.jonareas.pylearn.data.repository.LessonRepositoryImpl
import com.jonareas.pylearn.data.repository.QuizRepositoryImpl
import com.jonareas.pylearn.databinding.FragmentLessonBinding
import com.jonareas.pylearn.utils.MainViewModelFactory
import com.jonareas.pylearn.utils.OnClickLesson
import com.jonareas.pylearn.viewmodel.MainViewModel
import java.util.*


class LessonFragment : Fragment(), OnClickLesson {

    private  var _binding: FragmentLessonBinding? = null
    private val binding get() = _binding!!

    private lateinit var _viewModel: MainViewModel
    private lateinit var lessons : List<LessonHeader>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _viewModel = ViewModelProvider(requireActivity(),
             MainViewModelFactory(QuizRepositoryImpl(),LessonRepositoryImpl()))
            .get(MainViewModel::class.java)

        _binding = FragmentLessonBinding.inflate(inflater, container, false)
        lessons = Collections.emptyList()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      _viewModel.lessons().observe(viewLifecycleOwner){ lessons ->

          lessons.let {

                this.lessons = lessons

                binding.recyclerView.apply{
                    layoutManager = LinearLayoutManager(activity)
                    adapter = LessonAdapter(context, lessons,this@LessonFragment)
                    val heightInPixels = resources.getDimensionPixelSize(R.dimen.list_item_divider_height)
                    context?.let {
                        binding.recyclerView.addItemDecoration(LessonItemDecoration(ContextCompat.getColor(it, R.color.black), heightInPixels))
                    }
                }

            }

            if(lessons.isEmpty()) {
                Snackbar.make(view, resources.getString(R.string.emptyList), Snackbar.LENGTH_SHORT)
                    .show()
            }


        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        //_viewModel = null
    }

    //Metodo que se invoca cuando se da clic sobre una leccion
    override fun OnClickItem(lesson: LessonHeader) {
       // _viewModel.lessonsDetail(lesson.lessonHeaderId)

       //Toast.makeText(context,"Dio clic a ${lesson.description}", Toast.LENGTH_SHORT).show()

        var action = LessonFragmentDirections.actionLessonShow(lessonId = lesson.lessonHeaderId)
        findNavController().navigate(action)
    }
}