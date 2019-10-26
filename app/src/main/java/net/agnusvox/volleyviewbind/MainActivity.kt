package net.agnusvox.volleyviewbind

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_main.*
import net.agnusvox.volleyviewbind.Constants

class MainActivity : AppCompatActivity() {
    //TODO: Make myVolley in application context so it does not trigger when screen rotates.
    //See: https://developer.android.com/training/volley/requestqueue
    private val myVolley = VolleySingleton(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        getMyText()
    }

    //Reference: https://developer.android.com/training/volley/simple
    private fun getMyText() {
        val textView = findViewById<TextView>(R.id.myText)
// ...

// Instantiate the RequestQueue. (only when "simple" method)
//        val queue = Volley.newRequestQueue(this)
        val url = Constants.liveInfoUrl

// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                textView.text = "Response is: ${response.substring(0, 500)}"
            },
            Response.ErrorListener { textView.text = "That didn't work!" })

// Add the request to the RequestQueue. (only when "simple" method)
//        queue.add(stringRequest)

        myVolley.addToRequestQueue(stringRequest)
        //Reference: https://developer.android.com/training/volley/requestqueue
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

