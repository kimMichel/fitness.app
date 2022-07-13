package co.tiagoaguiar.fitnesstracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ImcActivity : AppCompatActivity() {

    private lateinit var editWeight: EditText
    private lateinit var editHeight: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        editWeight = findViewById(R.id.edit_imc_weight)
        editHeight = findViewById(R.id.edit_imc_height)

        val btnSend: Button = findViewById(R.id.btn_send)
        btnSend.setOnClickListener {
            // se a função retornar false
            if (!validate()) {
                // Vai soltar um alerta ao usuário, contendo o texto localizado no arquivo string.xml
                Toast.makeText(this, R.string.fields_message, Toast.LENGTH_SHORT).show()
                // para matar apenas a execução do setOnClickListener e não do onCreate
                // se retornar sem o @setOnClickListener vai matar a execução do onCreate
                return@setOnClickListener
            }

        }
    }

    private fun validate(): Boolean {
        // retorna verdade se todas essas condições forem verdadeiras
        return (editWeight.text.toString().isNotEmpty()
                && editHeight.text.toString().isNotEmpty()
                && !editWeight.text.toString().startsWith("0")
                && !editHeight.text.toString().startsWith("0"))
    }

}