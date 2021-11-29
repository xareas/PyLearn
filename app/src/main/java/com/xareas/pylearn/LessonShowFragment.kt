package com.xareas.pylearn
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.snackbar.Snackbar
import com.xareas.pylearn.data.model.LessonDetail
import com.xareas.pylearn.data.repository.LessonRepositoryImpl
import com.xareas.pylearn.data.repository.QuizRepositoryImpl
import com.xareas.pylearn.databinding.FragmentLessonShowBinding
import com.xareas.pylearn.utils.MainViewModelFactory
import com.xareas.pylearn.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_lesson_show.*
import java.util.*

class LessonShowFragment : Fragment() {

    private var _binding: FragmentLessonShowBinding? = null
    private val binding get() = _binding!!
    private lateinit var _viewModel: MainViewModel

    private var lessonsDetail : List<LessonDetail> = Collections.emptyList()
    private var lessonId: Int = 1
    private var currentDetail: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLessonShowBinding.inflate(inflater, container, false)


        _viewModel = ViewModelProvider(requireActivity(),
                     MainViewModelFactory(QuizRepositoryImpl(), LessonRepositoryImpl()))
                     .get(MainViewModel::class.java)


        return binding.root

       // return inflater.inflate(R.layout.fragment_lesson_show, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val safeArgs = LessonShowFragmentArgs.fromBundle(it)
            lessonId = safeArgs.lessonId
         }

        _viewModel.lessonsDetail(lessonId).observe(viewLifecycleOwner){ detail ->
             detail.let {
                this.lessonsDetail = detail

                 this.binding.mensaje.text = detail[currentDetail].text

                 var imgDraw = resources.getIdentifier(detail[currentDetail].image, null,
                     context?.packageName)
                this.binding.imagen.setImageResource(imgDraw)
             }

            if(detail.isEmpty()) {
                Snackbar.make(view, resources.getString(R.string.emptyList), Snackbar.LENGTH_SHORT)
                    .show()
            }

            this.binding.nextbutton.setOnClickListener {

                currentDetail++

                if(this.currentDetail > this.lessonsDetail.size - 1 ) {
                    this.currentDetail = this.lessonsDetail.size - 1
                    return@setOnClickListener
                }



                this.binding.mensaje.text = detail[currentDetail].text
                var imgDraw = resources.getIdentifier(detail[currentDetail].image, null,
                    context?.packageName)
                this.binding.imagen.setImageResource(imgDraw)

            }

            this.binding.backbutton.setOnClickListener {

                currentDetail--

                if(this.currentDetail < 0) {
                    this.currentDetail = 0
                    return@setOnClickListener
                }

                this.binding.mensaje.text = detail[currentDetail].text
                var imgDraw = resources.getIdentifier(detail[currentDetail].image, null,
                    context?.packageName)
                this.binding.imagen.setImageResource(imgDraw)
            }
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
           findNavController().navigateUp()
        }
    }



}