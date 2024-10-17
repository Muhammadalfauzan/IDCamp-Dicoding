package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.BulletSpan
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerview.databinding.ActivityDetailBinding
import com.example.recyclerview.model.DataRecipe

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val recipe = intent.getParcelableExtra<DataRecipe>("recipe")

        recipe?. let {
            binding.imageView.setImageResource(recipe.imageFood)
            binding.tvNameRecipe.text = recipe.nameFood

            val bulletIngredients = addBulletSpanToText(recipe.ingredients.replace("\\n", "\n"))
            binding.tvBahan.text = bulletIngredients


            val bulletDeskripsi = addBulletSpanToText(recipe.deskripsi.replace("\\n", "\n"))
            binding.tvDeskripsi.text = bulletDeskripsi

            binding.imgShare.setOnClickListener {
                shareRecipe(recipe)
            }
        }

    }

    private fun shareRecipe(recipe: DataRecipe) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Resep: ${recipe.nameFood}")
            putExtra(
                Intent.EXTRA_TEXT,
                "Coba resep ini: ${recipe.nameFood}\n\nBahan-Bahan:\n${recipe.ingredients.replace("\\n", "\n")}\n\nCara Membuat:\n${recipe.deskripsi.replace("\\n", "\n")}"
            )
        }
        startActivity(Intent.createChooser(shareIntent, "Bagikan resep ini dengan"))
    }
    private fun addBulletSpanToText(text: String): SpannableStringBuilder {
        val spannableStringBuilder = SpannableStringBuilder()
        val items = text.split("\n")

        items.forEach { item ->
            if (item.trim().isNotEmpty()) {
                val spannableString = SpannableString(item.trim())
                spannableString.setSpan(BulletSpan(15), 0, spannableString.length, 0)
                spannableStringBuilder.append(spannableString).append("\n")
            }
        }
        return spannableStringBuilder
    }
}
