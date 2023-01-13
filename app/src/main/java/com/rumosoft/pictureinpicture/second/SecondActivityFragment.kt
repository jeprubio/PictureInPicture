package com.rumosoft.pictureinpicture.second

import android.app.PictureInPictureParams
import android.os.Build
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonEnterPip.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                PictureInPictureParams.Builder().apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        setAutoEnterEnabled(true)
                    }
                }.build().also {
                    activity?.enterPictureInPictureMode(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode)

        if (isInPictureInPictureMode) {
            binding.buttonEnterPip.visibility = View.GONE
        } else {
            binding.buttonEnterPip.visibility = View.VISIBLE
        }
    }
}
