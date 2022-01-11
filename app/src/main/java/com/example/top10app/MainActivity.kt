package com.example.top10app


import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var rvTop10: RecyclerView
    lateinit var Top10App: MutableList<Top10>
    lateinit var getFeed: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvTop10 = findViewById(R.id.rvTop)
        getFeed = findViewById(R.id.btnFeed)
        getFeed.setOnClickListener {
            BringTop().execute()
        }

    }

    private inner class BringTop : AsyncTask<Void, Void, MutableList<Top10>>() {
        val parser = XmlParser()
        val progressD = ProgressDialog(this@MainActivity)

        override fun onPreExecute() {
            super.onPreExecute()
            progressD.setMessage("Please Wait")
            progressD.show()
        }

        override fun doInBackground(vararg params: Void?): MutableList<Top10> {
            val url = URL("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml")
            val urlConnection = url.openConnection() as HttpURLConnection
            Top10App =
                urlConnection.inputStream?.let { parser.parse(it) } as MutableList<Top10>
            return Top10App
        }

        override fun onPostExecute(result: MutableList<Top10>?) {
            super.onPostExecute(result)
            progressD.dismiss()
            rvTop10.adapter = RVAdapter(result)
            rvTop10.layoutManager = LinearLayoutManager(applicationContext)
        }
    }
}