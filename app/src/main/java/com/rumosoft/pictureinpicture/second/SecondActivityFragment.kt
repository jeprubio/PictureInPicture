package com.rumosoft.pictureinpicture.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rumosoft.pictureinpicture.databinding.FragmentSecondActivityBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SecondActivityFragment : Fragment() {

    private var _binding: FragmentSecondActivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
