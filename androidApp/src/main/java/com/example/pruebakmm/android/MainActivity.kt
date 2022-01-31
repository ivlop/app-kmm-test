package com.example.pruebakmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.pruebakmm.Greeting
import android.widget.TextView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private val greeting = Greeting()
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        val user: EditText = findViewById(R.id.username)
        val pass: EditText = findViewById(R.id.password)
        val login: Button = findViewById(R.id.loginButton)
        tv.text = "Loading..."

        login.setOnClickListener{
            mainScope.launch {
                kotlin.runCatching {
                    val passBase64 =
                        Base64.encodeToString(pass.text.toString().toByteArray(Charset.forName("UTF-8")), Base64.DEFAULT).replace("\n", "")

                    val usernamePassBase64Bytes: String = "${user.text.toString()}:$passBase64"
                    val authorization: String = Base64.encodeToString(
                        usernamePassBase64Bytes.toByteArray(Charset.forName("UTF-8")),
                        Base64.DEFAULT
                    ).replace("\n", "")
                    greeting.login(authorization)
//                greeting.greeting()
                }.onSuccess {
                    tv.text = it
                    user.visibility = View.GONE
                    pass.visibility = View.GONE
                    login.visibility = View.GONE
                }.onFailure {
                    tv.text = "Error> ${it.localizedMessage}"
                }
            }
        }


    }
}
