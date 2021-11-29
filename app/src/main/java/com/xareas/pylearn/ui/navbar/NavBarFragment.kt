package com.xareas.pylearn.ui.navbar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xareas.pylearn.ui.AboutActivity
import com.xareas.pylearn.ui.LoginActivity
import com.xareas.pylearn.databinding.FragmentNavBarBinding


class NavBarFragment: Fragment() {

    private lateinit var navVarViewModel: NavBarViewModel
    private var _binding: FragmentNavBarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navVarViewModel =
            ViewModelProvider(this).get(NavBarViewModel::class.java)

        _binding = FragmentNavBarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnCloseSesion.setOnClickListener{

            startActivity(Intent(context, LoginActivity::class.java))
        }

        binding.btnAcercaDe.setOnClickListener{
            startActivity(Intent(context, AboutActivity::class.java))
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}