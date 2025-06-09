package valdez.wilber.asignacion4_calculadoraimc_valdezwilber

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Declarar las variables que representan a los elementos de la interfaz gráfica
    private lateinit var weightEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var imcTextView: TextView
    private lateinit var rangeTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        weightEditText = findViewById(R.id.weight) //
        heightEditText = findViewById(R.id.height) //
        calculateButton = findViewById(R.id.btnCalcular) //
        imcTextView = findViewById(R.id.imc) //
        rangeTextView = findViewById(R.id.range) //

        calculateButton.setOnClickListener { //
            calculateAndDisplayIMC() //
        }

    }

    private fun calculateAndDisplayIMC() {
        // obtiene los datos
        val weightString = weightEditText.text.toString()
        val heightString = heightEditText.text.toString()

        if (weightString.isNullOrEmpty() || heightString.isNullOrEmpty()) {
            imcTextView.text = "Ingresa peso y altura"
            rangeTextView.text = ""
            rangeTextView.setBackgroundResource(android.R.color.transparent) // Limpiar el color de fondo
            return
        }

        // convierte a numeros float pa que no haya errores
        val weight = weightString.toFloat()
        val height = heightString.toFloat() / 100 // Convertir altura de cm a metros

        // aqui calcula el IMC
        val imc = weight / (height * height) //
        imcTextView.text = String.format("%.5f", imc) // Desplegar el IMC con 5 decimales

        var category = ""
        var colorResId: Int = android.R.color.transparent

        when {
            imc < 18.5 -> {
                category = "Bajo peso" //
                colorResId = R.color.colorBrown //
            }
            imc >= 18.5 && imc <= 24.9 -> {
                category = "Normal" //
                colorResId = R.color.colorGreen //
            }
            imc >= 25 && imc <= 29.9 -> {
                category = "Sobrepeso" //
                colorResId = R.color.colorYellow //
            }
            imc >= 30 && imc <= 34.9 -> {
                category = "Obesidad grado 1" //
                colorResId = R.color.colorOrange //
            }
            imc >= 35 && imc <= 39.9 -> {
                category = "Obesidad grado 2" //
                colorResId = R.color.colorRed //
            }
            imc >= 40 -> {
                category = "Obesidad grado 3" //
                colorResId = R.color.colorRed //
            }
        }
        rangeTextView.text = category //
        rangeTextView.setBackgroundResource(colorResId) // Cambiar el color de fondo de la etiqueta de rango
    }
}
