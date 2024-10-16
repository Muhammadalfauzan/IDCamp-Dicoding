package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.adapter.AdapterRecipe
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.model.DataRecipe

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterRecipe: AdapterRecipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflating layout with ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting Toolbar as ActionBar
        setSupportActionBar(binding.toolbar)

        // Initialize RecyclerView and Adapter
        adapterRecipe = AdapterRecipe()
        binding.rvRecipe.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterRecipe
        }

        // Load data into RecyclerView
        loadData()
    }

    // Inflate menu for toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.acction_setting, menu)
        return true
    }

    // Handle menu item selection
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {

                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadData() {

        val nameArray = resources.getStringArray(R.array.data_food)
        val ingredientsArray = resources.getStringArray(R.array.data_Bahan)
        val imageArray = resources.obtainTypedArray(R.array.data_image_food)
        val deskripsiArray = resources.getStringArray(R.array.data_deskripsi)


        val recipeList = ArrayList<DataRecipe>()

        // Fill the list with data from the arrays
        for (i in nameArray.indices) {
            val recipe = DataRecipe(
                nameFood = nameArray[i],
                ingredients = ingredientsArray[i],
                imageFood = imageArray.getResourceId(i, -1),
                deskripsi = deskripsiArray[i]
            )
            recipeList.add(recipe)
        }

        adapterRecipe.submitList(recipeList)

        imageArray.recycle()
    }
}
