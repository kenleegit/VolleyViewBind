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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //TODO: Make myVolley in application context so it does not trigger when screen rotates.
    //See: https://developer.android.com/training/volley/requestqueue
    private val myVolley = VolleySingleton(this)
    //GSON Reference: https://medium.com/@givemepass/gson-%E5%9F%BA%E7%A4%8E%E6%95%99%E5%AD%B8-f367ee74e65d
    val gson = Gson()

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
        val txtSchedulerTime = findViewById<TextView>(R.id.txtSchedulerTime)
        val txtCurrentShowName = findViewById<TextView>(R.id.txtCurrentShowName)
        val txtStartTime= findViewById<TextView>(R.id.txtStartTime)
        val txtEndTime= findViewById<TextView>(R.id.txtEndTime)
        val txtImagePath= findViewById<TextView>(R.id.txtImagePath)
        // ...

// Instantiate the RequestQueue. (only when "simple" method)
//        val queue = Volley.newRequestQueue(this)
        val url = Constants.liveInfoUrl

// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                val jsonStringSanitized = response.trim('(', ')')
                // Display the first 500 characters of the response string.
                //textView.text = "Response is: ${jsonStringSanitized.substring(0, 500)}"

                //Reference: https://medium.com/@givemepass/gson-%E5%9F%BA%E7%A4%8E%E6%95%99%E5%AD%B8-f367ee74e65d
                val myInfoType = object : TypeToken<LiveInfo>(){}.type
                val jsonObj = Gson().fromJson<LiveInfo>(jsonStringSanitized, myInfoType)
                txtSchedulerTime.text = jsonObj.schedulerTime
                txtCurrentShowName.text = jsonObj.currentShow!![0].name
                txtStartTime.text = jsonObj.currentShow!![0].start_timestamp
                txtEndTime.text = jsonObj.currentShow!![0].end_timestamp
                txtImagePath.text = jsonObj.currentShow!![0].image_path
            },
            Response.ErrorListener { txtSchedulerTime.text = "That didn't work!" })

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

