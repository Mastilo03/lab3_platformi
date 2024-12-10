package mk.ukim.finki.lab_3.retrofit


import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class QuoteDbApiProvider  {

    companion object {
        @Volatile //kreirame statictki singleton objekt od MovieDbApi
        private var INSTANCE: QuoteApiService? = null

        @JvmStatic //ovaa oznaka znaci deka ako koristime java kod ovoj metod ke bide staticki
        fun getQuoteDbApi(): QuoteApiService {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = createQuoteDbApi()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        //interseptorot e objekt koj se koristi za manipulacija na request-ot i sekogas se koristi koga postojano se prakjaat nekoi parametri
        private fun createQuoteDbApi(): QuoteApiService { //ovoj kod vo globala sekogas e ist
            class QueryParamInterceptor : Interceptor { //koga se definira interseptor
                @Throws(IOException::class) //interseptorot mora da ima override na intersept metodot
                override fun intercept(chain: Interceptor.Chain): Response {
                    var request: Request = chain.request() //precekaj go request-ot
                    val htt = request.url.newBuilder()
                        .build()
                    request = request.newBuilder().url(htt).build()
                    return chain.proceed(request)
                }
            }

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY //sekoja info sto doagja vo body mozeme da ja izlogirame

            val okhttpClient = OkHttpClient.Builder()
                .addInterceptor(QueryParamInterceptor())
                .addInterceptor(httpLoggingInterceptor)//dodavame 2 interseptori, eden za logiranje dr za parametri
                .build() // fiksni se pri sekoj request
            val gson = GsonBuilder() //dodavame gson konverter za da mozeme da parsirame json
                .setLenient()
                .create()

            val gsonConverterFactory = GsonConverterFactory.create(gson)

            val retrofit = Retrofit.Builder()//kreirame retrofit objekt so base url i gson konverter
                .baseUrl("https://dummyjson.com/")
                .client(okhttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
            return retrofit.create(QuoteApiService::class.java)
        }
    }

}
