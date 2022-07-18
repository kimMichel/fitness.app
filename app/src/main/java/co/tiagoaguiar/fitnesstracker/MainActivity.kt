package co.tiagoaguiar.fitnesstracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

//    private lateinit var btnImc: LinearLayout
    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainAdapter()
        rvMain = findViewById(R.id.rv_main)
        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(this)

        // classe para administrar a recyclerview e suas celulas (os seus layouts de itens)
        // Adapter ->

//        btnImc = findViewById(R.id.btn_imc)
//        btnImc.setOnClickListener {
//            // Navegar para a próxima tela
//            val i = Intent(this, ImcActivity::class.java) // Criei a inteção de navegar para outra tela
//            startActivity(i) // Começar uma atividade (a intenção)
//        }

    }

    private inner class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {
        // 1) Qual é o layout XML da celula especifica (item)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = layoutInflater.inflate(R.layout.main_item, parent, false)
            return MainViewHolder(view)
        }

        // 2) dispadarado toda vez que houver uma rolagem na tela e for necessário trocar
        // o conteudo da célula
        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        }

        // 3) informar quantas celulas essa lista terá
        override fun getItemCount(): Int {
            return 15
        }

    }

    // é a classe da celula em si.
    private class MainViewHolder(view: View) : RecyclerView.ViewHolder(view)
}