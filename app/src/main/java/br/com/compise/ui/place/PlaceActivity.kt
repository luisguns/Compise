package br.com.compise.ui.place

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.compise.R

class PlaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)
        supportActionBar?.title = "Estabelecimento"
    }


}