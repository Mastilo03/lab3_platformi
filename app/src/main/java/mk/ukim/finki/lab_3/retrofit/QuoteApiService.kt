package mk.ukim.finki.lab_3.retrofit

import retrofit2.Response
import retrofit2.http.GET

data class Quote(val id: Int, val quote: String, val author: String)
data class QuoteResponse(val quotes: List<Quote>)


interface QuoteApiService {
    @GET("quotes")
    fun getQuotes(): Response<QuoteResponse>
}
