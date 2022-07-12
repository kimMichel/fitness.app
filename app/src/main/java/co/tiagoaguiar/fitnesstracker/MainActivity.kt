package co.tiagoaguiar.fitnesstracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    private lateinit var btnImc: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnImc = findViewById(R.id.btn_imc)
        btnImc.setOnClickListener {
            // Navegar para a próxima tela
            val i = Intent(this, ImcActivity::class.java) // Criei a inteção de navegar para outra tela
            startActivity(i) // Começar uma atividade (a intenção)
        }

    }
}