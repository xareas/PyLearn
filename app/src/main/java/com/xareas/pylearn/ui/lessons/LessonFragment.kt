package com.xareas.pylearn.ui.lessons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.xareas.pylearn.R
import com.xareas.pylearn.adapter.LessonAdapter
import com.xareas.pylearn.data.model.LessonHeader
import com.xareas.pylearn.data.model.Question
import com.xareas.pylearn.data.repository.LessonRepositoryImpl
import com.xareas.pylearn.data.repository.QuizRepositoryImpl
import com.xareas.pylearn.databinding.FragmentLessonBinding
import com.xareas.pylearn.utils.MainViewModelFactory
import com.xareas.pylearn.utils.OnClickLesson
import com.xareas.pylearn.viewmodel.MainViewModel
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

        var x = Question(1,"")

        return binding.root
    }

    override  fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


      _viewModel.lessons().observe(viewLifecycleOwner){ lessons ->
          this.lessons = lessons
          lessons.let {

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