package co.tiagoaguiar.fitnesstracker

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import co.tiagoaguiar.fitnesstracker.model.Calc

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
            val weight = editWeight.text.toString().toInt()
            val height = editHeight.text.toString().toInt()

            val result = imcCalculate(weight, height)
            // Log.i("test", "resultado: $result") dica: utilize o log para verificar se a lógica está funcionando corretamente

            val dialog = AlertDialog.Builder(this) // código padrão para criar "dialog"

                .setTitle(getString(R.string.imc_response, result))
                .setMessage(imcResponse(result))
                .setPositiveButton(android.R.string.ok) { dialog, which ->
                    // aqui vai rodar depois do click
                }
                .setNegativeButton(R.string.save) { dialog, which ->
                    Thread {
                        val app = application as App
                        val dao = app.db.calcDao()
                        dao.insert(Calc(type = "imc", res = result))

                        runOnUiThread {
                            openListActivity()
                        }

                    }.start()
                }
                .create()
                .show()

            // Qualquer serviço que são recursos do sistema como: camera, teclado, sensor biometrico
            // os services utilizamos para manipular os recursos e serviços do sistema android
            val service = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            //comando para esconder o teclado assim que executar a ação
            service.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

    // Codigo padrão onde igual quando inflamos um layout, precisamos inflar o menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    // Serve para escutar os eventos de click dentro dos itens do menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_search){
            finish()
            openListActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openListActivity() {
        val intent = Intent(this@ImcActivity, ListCalcActivity::class.java)
        intent.putExtra("type", "imc")
        startActivity(intent)
    }

    @StringRes //Essa notação força ao usuário retornar um arquivo de resource
    private fun imcResponse(imc: Double): Int {
        return when {
            imc < 15.0 -> R.string.imc_severely_low_weight
            imc < 16.0 -> R.string.imc_very_low_weight
            imc < 18.5 -> R.string.imc_low_weight
            imc < 25.0 -> R.string.normal
            imc < 30.0 -> R.string.imc_high_weight
            imc < 35.0 -> R.string.imc_so_high_weight
            imc < 40.0 -> R.string.imc_severely_high_weight
            else -> R.string.imc_extreme_weight
        }
    }

    private fun imcCalculate(weight: Int, height: Int): Double {
        return weight / ((height / 100.0) * (height / 100.0))
    }

    private fun validate(): Boolean {
        // retorna verdade se todas essas condições forem verdadeiras
        return (editWeight.text.toString().isNotEmpty()
                && editHeight.text.toString().isNotEmpty()
                && !editWeight.text.toString().startsWith("0")
                && !editHeight.text.toString().startsWith("0"))
    }

}