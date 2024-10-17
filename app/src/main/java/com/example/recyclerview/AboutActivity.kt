package com.example.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // Tambahkan action bar jika ingin toolbar dengan back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Untuk tombol back di action bar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}