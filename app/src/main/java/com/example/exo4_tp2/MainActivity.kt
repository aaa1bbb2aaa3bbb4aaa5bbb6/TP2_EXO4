package com.example.exo4_tp2


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val jsonPlaceHolderApi = retrofit.create(
            JsonPlaceHolderApi::class.java
        )
        val call: Call<List<Post>> = jsonPlaceHolderApi.posts

        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(
                call: Call<List<Post>>,
                response: Response<List<Post>>
            ) {
                if (!response.isSuccessful()) {
                    text_view_result.setText("Code: " + response.code())
                    return
                }
                val posts: List<Post> = response.body()
                for (post in posts) {
                    var content = ""
                    content += """
                        Identifiant_User: ${post.id}
                        
                        """.trimIndent()
                    content += """
                        Identifiant_Post: ${post.userId}
                        
                        """.trimIndent()
                    content += """
                        Titre: ${post.title}
                        
                        """.trimIndent()
                    content += """
                        Texte: ${post.text}
                        
                        
                        """.trimIndent()
                    text_view_result.append(content)
                }
            }

            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
                text_view_result.setText(t?.message)
            }
        })
    }
}
