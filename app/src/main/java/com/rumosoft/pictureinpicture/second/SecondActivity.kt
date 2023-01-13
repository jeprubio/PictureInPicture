package com.rumosoft.pictureinpicture.second

import android.app.PictureInPictureParams
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.rumosoft.pictureinpicture.R
import com.rumosoft.pictureinpicture.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_second)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        initPictureInPicture()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_second)
        return navController.navigateUp(appBarConfiguration) ||
            super.onSupportNavigateUp()
    }

    override fun onUserLeaveHint() {
        enterPipMode()
    }

    private fun initPictureInPicture() {
        binding.buttonEnterPip.setOnClickListener {
            enterPipMode()
        }
    }

    private fun enterPipMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            PictureInPictureParams.Builder().apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    setAutoEnterEnabled(true)
                }
            }.build().also {
                enterPictureInPictureMode(it)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration
    ) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)

        if (isInPictureInPictureMode) {
            binding.toolbar.visibility = View.GONE
            binding.buttonEnterPip.visibility = View.GONE
        } else {
            binding.toolbar.visibility = View.VISIBLE
            binding.buttonEnterPip.visibility = View.VISIBLE
        }
    }
}
