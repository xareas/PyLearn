package com.jonareas.pylearn.ui.lessons

import android.os.Bundle
import android.service.autofill.Dataset
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jonareas.pylearn.R
import com.jonareas.pylearn.adapter.LessonAdapter
import com.jonareas.pylearn.data.model.LessonHeader
import com.jonareas.pylearn.data.repository.LessonRepositoryImpl
import com.jonareas.pylearn.data.repository.QuizRepositoryImpl
import com.jonareas.pylearn.databinding.FragmentLessonBinding
import com.jonareas.pylearn.temp.DataSet
import com.jonareas.pylearn.utils.MainViewModelFactory
import com.jonareas.pylearn.utils.ViewModelFactory
import com.jonareas.pylearn.utils.getViewModel
import com.jonareas.pylearn.viewmodel.MainViewModel
import java.util.*


class LessonFragment : Fragment() {


    private  var _binding: FragmentLessonBinding? = null
    private lateinit var _viewModel: MainViewModel
    private val binding get() = _binding!!
    private lateinit var lessons : List<LessonHeader>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //viewmodel en el fragemento, uso requieredactivity
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
                    adapter = LessonAdapter(context, lessons)
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
}