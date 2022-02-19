package sanchez.alexa.clicker

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    var cuenta: Int =0
    var texto: String?=""
    lateinit var numero: TextView
    lateinit var campo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_suma: ImageButton= findViewById(R.id.btnMas)
        val btn_resta: ImageButton= findViewById(R.id.btnMenos)
        val btn_inicio: ImageButton=findViewById(R.id.btnInicio)
        campo=findViewById(R.id.editTextCampo)
        numero=findViewById(R.id.txtNumero)


        btn_suma.setOnClickListener{
            cuenta++
            numero.setText("$cuenta")
        }
        btn_resta.setOnClickListener{
            cuenta--
            numero.setText("$cuenta")
        }
        btn_inicio.setOnClickListener {

            val alertDialog: AlertDialog? = this?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("Borrar",
                        DialogInterface.OnClickListener { dialog, id ->
                            cuenta=0
                            numero.setText("$cuenta")
                            campo.setText("")
                        })
                    setNegativeButton("Cancelar",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User cancelled the dialog
                        })
                }
                // Set other dialog properties
                builder?.setMessage("Seguro que desea borrar la cuenta?").setTitle("Confirmación")
                // Create the AlertDialog
                builder.create()
            }
            alertDialog?.show()

        }

    }
    override fun onPause(){
        super.onPause()

        val sharedPref2 = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        var editor2 = sharedPref2.edit()
        texto = campo.text.toString()

        editor2.putString("text",texto)
        editor2.putInt("contador", cuenta)
        editor2.commit()

    }

    override fun onResume() {
        super.onResume()
        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
        texto =sharedPref.getString("texto","")
        cuenta =sharedPref.getInt("contador",0)
        numero.setText("$cuenta")
        campo.setText("$texto")
    }
}