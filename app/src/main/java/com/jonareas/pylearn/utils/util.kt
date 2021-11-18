package com.jonareas.pylearn.utils

import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jonareas.pylearn.PyLearnApplication
import com.jonareas.pylearn.data.repository.LessonRepository
import com.jonareas.pylearn.data.repository.LessonRepositoryImpl
import com.jonareas.pylearn.data.repository.QuizRepository
import com.jonareas.pylearn.data.repository.QuizRepositoryImpl
import com.jonareas.pylearn.viewmodel.MainViewModel


class ViewModelFactory<T>(val creator: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return creator() as T
    }
}

class MainViewModelFactory(
    private val quizRepository: QuizRepository,
    private val lessonRepository: LessonRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(lessonRepository,quizRepository) as T
    }

}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(noinline creator: (() -> T)? = null): T {
    return if (creator == null)
        ViewModelProvider(this).get(T::class.java)
    else
        ViewModelProvider(this, ViewModelFactory(creator)).get(T::class.java)
}


//Metodo para obtener el nombre en base al id
object Strings {

    fun get(@StringRes stringRes: Int, vararg formatArgs: Any = emptyArray()): String {
        return PyLearnApplication.instance.getString(stringRes, *formatArgs)
    }

    fun getDrawName(drawName: Int): String {
        return "drawable/${PyLearnApplication.instance.resources
            .getResourceEntryName(drawName)}"
    }
}