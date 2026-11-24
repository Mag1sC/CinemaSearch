package com.kazbekov.cinemasearch

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kazbekov.cinemasearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Пока приложение не имеет настройки темы, для светлой и темной устанавливается один и тот же цвет
        val systemBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
        enableEdgeToEdge(
            statusBarStyle = systemBarStyle,
            navigationBarStyle = systemBarStyle
        )

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())

            binding.topBar.setPadding(0, systemBars.top, 0, 0)
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                v.setPadding(systemBars.left, 0, systemBars.right, 0)
                binding.bottomBar.setPadding(0, 0, 0, systemBars.bottom)
            } else {
                // В landscape-ориентации bottomBar располагается слева
                binding.bottomBar.setPadding(systemBars.left, 0, 0, systemBars.bottom)
                v.setPadding(0, 0, 0, 0)
            }

            WindowInsetsCompat.CONSUMED
        }
    }

    private fun makeToast(message: CharSequence) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}